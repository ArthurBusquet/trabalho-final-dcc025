/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Panels.MenusLaterais;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public abstract class MenuLateral extends JPanel {

    protected GerenciadorPainel gerenciadorPainel;
    protected GridBagConstraints gbc = new GridBagConstraints();
    protected JLabel textoSaudacao;
    protected JPanel menuAcoes;
    protected GerenciadorTela controlador;

    public MenuLateral(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {

        this.gerenciadorPainel = gerenciadorPainel;
        this.controlador = controlador;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(250, 1000));
        setBackground(new Color(50, 50, 50));

        gbc.insets = new Insets(40, 10, 10, 10);

        gbc.gridy = 0;
        gbc.gridx = 0;

        textoSaudacao = new JLabel("Olá, ...");
        textoSaudacao.setFont(new Font("Arial", Font.BOLD, 24));
        textoSaudacao.setForeground(Color.white);
        add(textoSaudacao, gbc);

        gbc.insets = new Insets(100, 10, 10, 10);

        menuAcoes = new JPanel();
        menuAcoes.setLayout(new GridBagLayout());
        menuAcoes.setOpaque(false);
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        add(menuAcoes, gbc);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(220, 40));
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(new Color(200, 50, 50));
        btnLogout.setForeground(Color.WHITE);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.mostrarTelaLogin();
            }
        });

        gbc.gridy = 2;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 20, 10);
        add(btnLogout, gbc);
    }

    protected void adicionarBotao(String texto, String nomePainel) {
        JButton botao = new JButton(texto);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(20, 0, 0, 0);

        gbc.gridy++;

        botao.setPreferredSize(new Dimension(220, 40));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(200, 50, 50));
        botao.setForeground(Color.WHITE);

        botao.addActionListener(e -> gerenciadorPainel.mostrarPainel(nomePainel));
        menuAcoes.add(botao, gbc);
    }

}
