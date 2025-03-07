package ui.Panels.PaineisAcoes;

import application.Cases.Gerente.AvaliacaoCreditoUseCase;
import application.Controllers.SessaoUsuario;
import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.SolicitacaoCredito;
import domain.Entities.Usuarios.Gerente;
import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import infrastructure.GerenciadorSolicitacoesCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import ui.Models.TabelaSolicitacoesCredito;

public class PainelAvaliacaoCredito extends PainelAcoes {

    private JTable tabelaSolicitacoes;
    private JPasswordField campoSenhaGerente;
    private JButton botaoAprovar;
    private JButton botaoReprovar;
    private AvaliacaoCreditoUseCase avaliacaoCreditoUseCase;
    private GerenciadorSolicitacoesCredito gerenciadorSolicitacoesCredito;

    public PainelAvaliacaoCredito(GerenciadorSolicitacoesCredito gerenciadorSolicitacoesCredito) {
        this.gerenciadorSolicitacoesCredito = gerenciadorSolicitacoesCredito;

        this.avaliacaoCreditoUseCase = new AvaliacaoCreditoUseCase(gerenciadorSolicitacoesCredito);
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        tabelaSolicitacoes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaSolicitacoes);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        JPanel painelSenha = new JPanel();
        painelSenha.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        painelSenha.add(new JLabel("Senha do Gerente:"), gbc);

        campoSenhaGerente = new JPasswordField(20);
        campoSenhaGerente.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelSenha.add(campoSenhaGerente, gbc);
        add(painelSenha, gbc);

        botaoAprovar = new JButton("Aprovar");
        botaoAprovar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliarCredito(true);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelSenha.add(botaoAprovar, gbc);

        botaoReprovar = new JButton("Reprovar");
        botaoReprovar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avaliarCredito(false);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        painelSenha.add(botaoReprovar, gbc);

        carregarSolicitacoesPendentes();
    }

    private void carregarSolicitacoesPendentes() {
        List<SolicitacaoCredito> solicitacoes = gerenciadorSolicitacoesCredito.getSolicitacoesNaoAprovadas();
        TabelaSolicitacoesCredito tabela = new TabelaSolicitacoesCredito(solicitacoes);
        tabelaSolicitacoes.setModel(tabela);
    }

    private void avaliarCredito(boolean aprovar) {
        int linhaSelecionada = tabelaSolicitacoes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma solicitação para avaliar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String senhaGerente = new String(campoSenhaGerente.getPassword());
        SolicitacaoCredito solicitacao = gerenciadorSolicitacoesCredito.getSolicitacoesNaoAprovadas().get(linhaSelecionada);

        try {

            Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();
            if (usuario.getTipoUsuario() != TipoUsuarioEnum.GERENTE) {
                JOptionPane.showMessageDialog(this, "Apenas gerentes podem aprovar creditos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;

            }
            avaliacaoCreditoUseCase.avaliarCredito(usuario, senhaGerente, solicitacao, aprovar);
            if (aprovar) {
                JOptionPane.showMessageDialog(this, "Crédito aprovado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Crédito reprovado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            carregarSolicitacoesPendentes();
        } catch (OperacaoInvalidaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
