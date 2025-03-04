package ui.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.Controllers.GerenciadorTela;

public abstract class PainelAutenticacao extends JPanel implements ActionListener {

    protected JPasswordField campoSenha;
    protected JLabel tituloSenha, mensagemErro;
    protected JButton botaoAplicar, botaoLimpar;
    protected JLabel messageLabel;
    protected GridBagConstraints gbc;
    protected GerenciadorTela controlador;

    public PainelAutenticacao(GerenciadorTela controlador) {
        this.controlador = controlador;
        
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        tituloSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(tituloSenha, gbc);

        campoSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(campoSenha, gbc);

        campoSenha.addKeyListener(
                new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e
            ) {
                campoSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        });

        botaoAplicar = new JButton("Aplicar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(botaoAplicar, gbc);
        botaoAplicar.addActionListener(this);

        botaoLimpar = new JButton("Limpar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(botaoLimpar, gbc);
        botaoLimpar.addActionListener(this);

        mensagemErro = new JLabel();
        mensagemErro.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(mensagemErro, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoAplicar) {
            autenticar();
            return;
        }

        if (e.getSource() == botaoLimpar) {
            limparCampos();
        }
    }

    protected abstract void autenticar();

    protected abstract void limparCampos();

}
