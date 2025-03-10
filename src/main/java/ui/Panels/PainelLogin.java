/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Panels;

import application.Controllers.SessaoUsuario;
import domain.Entities.Usuarios.Usuario;
import domain.Exceptions.UsuarioNaoEncontradoException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ui.Controllers.GerenciadorTela;
import infrastructure.GerenciadorUsuarios;
import ui.Controllers.GerenciadorAutenticacao;

import utils.ValidadorCPF;

public class PainelLogin extends PainelAutenticacao {

    private JTextField campoCpf;
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public PainelLogin(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorTela controladorTelas, GerenciadorAutenticacao gerenciadorAutenticacao) {
        super(controladorTelas);
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloCpf = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(tituloCpf, gbc);

        campoCpf = new JTextField(20);
        campoCpf.setPreferredSize(new Dimension(400, 30));
        campoCpf.setForeground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(campoCpf, gbc);

        campoCpf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                campoCpf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                mensagemErro.setText("");
            }
        });

        JLabel linkCadastro = new JLabel("<html><u>Não tem uma conta? Cadastre-se</u></html>");
        linkCadastro.setForeground(Color.BLUE);
        linkCadastro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkCadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gerenciadorAutenticacao.mostrarTelaCadastro();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(linkCadastro, gbc);
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
            mensagemErro.setText("CPF inválido");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        if (!gerenciadorUsuarios.validarLogin(cpf, senha)) {
            mensagemErro.setText("CPF ou senha incorretos.");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        try {
            Usuario usuario = gerenciadorUsuarios.buscarUsuarioPorCpf(cpf);

            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            SwingUtilities.getWindowAncestor(this).dispose();
            SessaoUsuario.getInstancia().setUsuarioLogado(usuario);
            controlador.mostrarTelaPrincipal();

        } catch (UsuarioNaoEncontradoException e) {
            mensagemErro.setText("Usuário não encontrado.");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
    }
}
