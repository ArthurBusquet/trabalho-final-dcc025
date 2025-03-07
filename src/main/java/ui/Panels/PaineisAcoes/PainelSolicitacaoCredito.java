package ui.Panels.PaineisAcoes;

import application.Cases.Caixa.SolicitarCreditoUseCase;
import application.Cases.Cliente.SolicitarCreditoUseCase;
import application.Controllers.SessaoUsuario;
import application.Exceptions.DadoInseridoInvalidoException;
import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.SolicitacaoCredito;
import domain.Entities.Usuarios.Usuario;
import infrastructure.GerenciadorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSolicitacaoCredito extends PainelAcoes {

    private JComboBox<String> comboTipoCredito;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton botaoSolicitar;
    private SolicitarCreditoUseCase solicitarCreditoUseCase;
    private final SessaoUsuario sessaoUsuario;

    public PainelSolicitacaoCredito(SessaoUsuario sessaoUsuario) {
        this.sessaoUsuario = sessaoUsuario;
        this.solicitarCreditoUseCase = new SolicitarCreditoUseCase(gerenciadorUsuarios);
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tipo de Crédito:"), gbc);

        String[] tiposCredito = {"Empréstimo Pessoal", "Financiamento Imobiliário", "Financiamento Veicular"};
        comboTipoCredito = new JComboBox<>(tiposCredito);
        comboTipoCredito.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(comboTipoCredito, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Valor Solicitado:"), gbc);

        campoValor = new JTextField(20);
        campoValor.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Senha do Cliente:"), gbc);

        campoSenha = new JPasswordField(20);
        campoSenha.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoSenha, gbc);

        botaoSolicitar = new JButton("Solicitar Crédito");
        botaoSolicitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarCredito();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoSolicitar, gbc);
    }

    private void solicitarCredito() {
        String tipoCredito = (String) comboTipoCredito.getSelectedItem();
        String valorTexto = campoValor.getText();
        String senha = new String(campoSenha.getPassword());

        if (tipoCredito.isEmpty() || valorTexto.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valorSolicitado;
        try {
            valorSolicitado = Double.parseDouble(valorTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = sessaoUsuario.getUsuarioLogado();

        if (!usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }



        try {
            solicitarCreditoUseCase.solicitarCredito(usuario, valorSolicitado, tipoCredito);
            JOptionPane.showMessageDialog(this, "Solicitação de crédito realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (DadoInseridoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        comboTipoCredito.setSelectedIndex(0);
        campoValor.setText("");
        campoSenha.setText("");
    }
}
