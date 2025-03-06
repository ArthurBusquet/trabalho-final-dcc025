package ui.Panels;

import ui.Models.TabelaExtratos;
import application.Controllers.SessaoUsuario;
import domain.Entities.Usuarios.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSaldoExtrato extends PainelAcoes {

    private JPasswordField campoSenha;
    private JTable tabelaExtrato;
    private JLabel labelSaldo;

    private GridBagConstraints gbc;

    public PainelSaldoExtrato() {
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel painelSenha = new JPanel();
        painelSenha.setBackground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        painelSenha.add(new JLabel("Senha:"), gbc);

        campoSenha = new JPasswordField(20);
        campoSenha.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        painelSenha.add(campoSenha, gbc);

        add(painelSenha, gbc);

        JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarSaldoExtrato();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoConsultar, gbc);

        labelSaldo = new JLabel("Saldo atual: R$ XXXX,XX");
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(labelSaldo, gbc);

    }

    private void consultarSaldoExtrato() {
        String senha = new String(campoSenha.getPassword());

        Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        labelSaldo.setText("Saldo atual: R$ " + String.format("%.2f", usuario.getValorEmConta()));

        tabelaExtrato = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaExtrato);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;
        add(scrollPane, gbc);

        TabelaExtratos tabela = new TabelaExtratos(usuario.getExtratos());
        tabelaExtrato.setModel(tabela);
    }
}
