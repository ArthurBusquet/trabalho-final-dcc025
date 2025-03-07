package ui.Panels.PaineisAcoes;

import application.Cases.Caixa.AprovarTransferenciaUseCase;
import application.Exceptions.DadoInseridoInvalidoException;
import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.SolicitarTransferencia;
import domain.Entities.Usuarios.Usuario;
import infrastructure.GerenciadorTransferencias;
import infrastructure.GerenciadorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import ui.Models.TabelaTransferencias;

public class PainelAprovarTransferencia extends PainelAcoes {

    private JTable tabelaTransferencias;
    private JPasswordField campoSenha;
    private JButton botaoAprovar;
    private GridBagConstraints gbc;

    private GerenciadorTransferencias gerenciadorTransferencias;
    private GerenciadorUsuarios gerenciadorUsuarios;
    private AprovarTransferenciaUseCase aprovarTransferenciaUseCase;

    public PainelAprovarTransferencia(GerenciadorTransferencias gerenciadorTransferencias, GerenciadorUsuarios gerenciadorUsuarios) {
        super();
        this.gerenciadorTransferencias = gerenciadorTransferencias;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.aprovarTransferenciaUseCase = new AprovarTransferenciaUseCase(gerenciadorTransferencias, gerenciadorUsuarios);
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        tabelaTransferencias = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaTransferencias);
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
        painelSenha.add(new JLabel("Senha do usuário:"), gbc);

        campoSenha = new JPasswordField(20);
        campoSenha.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        painelSenha.add(campoSenha, gbc);

        add(painelSenha, gbc);

        botaoAprovar = new JButton("Aprovar Transferência");
        botaoAprovar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aprovarTransferencia();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoAprovar, gbc);

        carregarTransferenciasPendentes();
    }

    private void carregarTransferenciasPendentes() {
        List<SolicitarTransferencia> transferencias = gerenciadorTransferencias.getTransferenciasPendentes();
        TabelaTransferencias tabela = new TabelaTransferencias(transferencias);
        tabelaTransferencias.setModel(tabela);
    }

    private void aprovarTransferencia() {
        int linhaSelecionada = tabelaTransferencias.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma transferência para aprovar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SolicitarTransferencia transferenciaSelecionada = gerenciadorTransferencias.getTransferenciasPendentes().get(linhaSelecionada);

        Usuario usuarioOrigem = gerenciadorUsuarios.carregarUsuarios().stream()
                .filter(u -> u.getIdConta().equals(transferenciaSelecionada.getIdContaOrigem()))
                .findFirst()
                .orElse(null);

        if (usuarioOrigem == null) {
            JOptionPane.showMessageDialog(this, "Usuário da conta de origem não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String senhaDigitada = new String(campoSenha.getPassword());
        if (!usuarioOrigem.getSenha().equals(senhaDigitada)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean aprovada = aprovarTransferenciaUseCase.aprovarTransferencia(transferenciaSelecionada);
            if (aprovada) {
                JOptionPane.showMessageDialog(this, "Transferência aprovada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                TabelaTransferencias tabela = (TabelaTransferencias) tabelaTransferencias.getModel();
                tabela.atualizarDados(gerenciadorTransferencias.getTransferenciasPendentes());
            }
        } catch (OperacaoInvalidaException | DadoInseridoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
