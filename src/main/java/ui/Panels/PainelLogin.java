package ui.Panels;

import domain.Enum.TipoUsuarioEnum;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ui.Controllers.GerenciadorTela;

import utils.ValidadorCPF;

public class PainelLogin extends PainelAutenticacao {

    private JTextField campoCpf;

    public PainelLogin(GerenciadorTela controlador) {
        super(controlador);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloCpf = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tituloCpf, gbc);

        campoCpf = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(campoCpf, gbc);

        campoCpf.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e
            ) {
                campoCpf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mensagemErro.setText("");
            }
        });
    }

    @Override
    protected void limparCampos() {
        campoCpf.setText("");
        campoSenha.setText("");
        mensagemErro.setText("");
        campoSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        campoCpf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    protected void autenticar() {
        String cpf = campoCpf.getText();
        String senha = new String(campoSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty()) {
            mensagemErro.setText("Preencha todos os campos!");
            if (cpf.isEmpty()) {
                campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            if (senha.isEmpty()) {
                campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            return;
        }

        if (!ValidadorCPF.cpfEhValido(cpf)) {
            mensagemErro.setText("CPF invalido");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        if (!cpf.equals("12345678900") || !senha.equals("senha123")) {
            mensagemErro.setText("CPF ou senha incorretos.");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        controlador.mostrarTelaPrincipal(TipoUsuarioEnum.CLIENTE);
    }
}
