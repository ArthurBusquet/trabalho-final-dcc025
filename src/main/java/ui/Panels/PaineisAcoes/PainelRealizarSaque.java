package ui.Panels.PaineisAcoes;

import application.Cases.Caixa.RealizarSaqueUseCase;
import application.Exceptions.DadoInseridoInvalidoException;
import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.Usuarios.Usuario;
import infrastructure.GerenciadorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelRealizarSaque extends PainelAcoes {

    private JTextField campoCpf;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton botaoSacar;
    private RealizarSaqueUseCase realizarSaqueUseCase;
    private GerenciadorUsuarios gerenciadorUsuarios;

    public PainelRealizarSaque(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.realizarSaqueUseCase = new RealizarSaqueUseCase(gerenciadorUsuarios);
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("CPF do Cliente:"), gbc);

        campoCpf = new JTextField(20);
        campoCpf.setPreferredSize(new Dimension(400, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(campoCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Valor do Saque:"), gbc);

        campoValor = new JTextField(20);
        campoValor.setPreferredSize(new Dimension(400, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Senha do Cliente:"), gbc);

        campoSenha = new JPasswordField(20);
        campoSenha.setPreferredSize(new Dimension(400, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoSenha, gbc);

        botaoSacar = new JButton("Realizar Saque");
        botaoSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarSaque();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoSacar, gbc);
    }

    private void realizarSaque() {
        String cpf = campoCpf.getText();
        String valorTexto = campoValor.getText();
        String senha = new String(campoSenha.getPassword());

        if (cpf.isEmpty() || valorTexto.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valorSaque;
        try {
            valorSaque = Double.parseDouble(valorTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor do saque inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = gerenciadorUsuarios.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            realizarSaqueUseCase.realizarSaque(usuario, valorSaque, senha);
            JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } catch (OperacaoInvalidaException | DadoInseridoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoCpf.setText("");
        campoValor.setText("");
        campoSenha.setText("");
    }
}
