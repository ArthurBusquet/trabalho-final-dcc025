package ui.Panels;

import application.Controllers.SessaoUsuario;
import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioNaoEncontradoException;
import infrastructure.GerenciadorUsuarios;
import ui.Controllers.GerenciadorTela;
import utils.ValidadorCPF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import ui.Controllers.GerenciadorAutenticacao;

public class PainelCadastro extends PainelAutenticacao {

    private JTextField campoCpf;
    private JPasswordField campoConfirmarSenha;
    private JComboBox<TipoUsuarioEnum> comboTipoUsuario;
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public PainelCadastro(GerenciadorUsuarios gerenciadorUsuarios, GerenciadorTela controladorTela, GerenciadorAutenticacao gerenciadorAutenticacao) {
        super(controladorTela);
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel tituloCpf = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(tituloSenha, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(campoSenha, gbc);

        JLabel tituloConfirmarSenha = new JLabel("Confirmar Senha:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(tituloConfirmarSenha, gbc);

        campoConfirmarSenha = new JPasswordField(20);
        campoConfirmarSenha.setPreferredSize(new Dimension(400, 30));
        campoConfirmarSenha.setForeground(Color.GRAY);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(campoConfirmarSenha, gbc);

        JLabel tituloTipoUsuario = new JLabel("Tipo de Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(tituloTipoUsuario, gbc);

        comboTipoUsuario = new JComboBox<>(new TipoUsuarioEnum[]{TipoUsuarioEnum.CLIENTE, TipoUsuarioEnum.CAIXA, TipoUsuarioEnum.GERENTE});
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(comboTipoUsuario, gbc);

        JLabel linkLogin = new JLabel("<html><u>Já tem uma conta? Faça login</u></html>");
        linkLogin.setForeground(Color.BLUE);
        linkLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gerenciadorAutenticacao.mostrarTelaLogin();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(linkLogin, gbc);

        botaoAplicar.setText("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        add(botaoAplicar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(botaoLimpar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(mensagemErro, gbc);
    }

    @Override
    protected void limparCampos() {
        campoCpf.setText("");
        campoSenha.setText("");
        campoConfirmarSenha.setText("");
        mensagemErro.setText("");
        campoCpf.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        campoSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        campoConfirmarSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    protected void autenticar() {
        String cpf = campoCpf.getText();
        String senha = new String(campoSenha.getPassword());
        String confirmarSenha = new String(campoConfirmarSenha.getPassword());

        if (cpf.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            mensagemErro.setText("Preencha todos os campos!");
            if (cpf.isEmpty()) {
                campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            if (senha.isEmpty()) {
                campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            if (confirmarSenha.isEmpty()) {
                campoConfirmarSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
            return;
        }

        if (!ValidadorCPF.cpfEhValido(cpf)) {
            mensagemErro.setText("CPF inválido");
            campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            mensagemErro.setText("As senhas não coincidem.");
            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            campoConfirmarSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            return;
        }

        try {
            if (gerenciadorUsuarios.existeUsuario(cpf)) {
                mensagemErro.setText("Usuário já cadastrado.");
                campoCpf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                return;
            }

            Usuario novoUsuario = new Usuario(cpf, senha, TipoUsuarioEnum.valueOf(comboTipoUsuario.getSelectedItem().toString()), 0.0);
            gerenciadorUsuarios.adicionar(novoUsuario);

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            limparCampos();
            controlador.mostrarTelaLogin();

        } catch (Exception e) {
            mensagemErro.setText("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}
