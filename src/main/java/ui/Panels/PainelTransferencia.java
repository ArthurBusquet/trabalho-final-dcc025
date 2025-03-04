package ui.Panels;

import application.Cases.TransferenciaUseCase;
import application.Controllers.SessaoUsuario;
import domain.Entities.Usuarios.Usuario;
import infrastructure.GerenciadorUsuarios;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PainelTransferencia extends PainelAcoes {

    private JTextField campoContaOrigem;
    private JTextField campoContaDestino;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar;

    private final TransferenciaUseCase transferenciaUseCase;

    public PainelTransferencia() {
        GerenciadorUsuarios gerenciador = new GerenciadorUsuarios();
        this.transferenciaUseCase = new TransferenciaUseCase(gerenciador);

        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        inicializar();
    }

    private void inicializar() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Conta de Origem:"), gbc);

        gbc.gridx = 1;
        campoContaOrigem = new JTextField(20);
        campoContaOrigem.setText(SessaoUsuario.getInstancia().getUsuarioLogado().getIdConta().toString());
        campoContaOrigem.setPreferredSize(new Dimension(400, 40));
        campoContaOrigem.setForeground(Color.GRAY);
        add(campoContaOrigem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Conta de Destino:"), gbc);

        gbc.gridx = 1;
        campoContaDestino = new JTextField(20);
        campoContaDestino.setPreferredSize(new Dimension(400, 40));
        campoContaDestino.setForeground(Color.GRAY);
        add(campoContaDestino, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Valor (R$):"), gbc);

        gbc.gridx = 1;
        campoValor = new JTextField(20);
        campoValor.setPreferredSize(new Dimension(400, 40));
        campoValor.setForeground(Color.GRAY);
        add(campoValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        campoSenha = new JPasswordField(20);
        campoSenha.setPreferredSize(new Dimension(400, 40));
        campoSenha.setForeground(Color.GRAY);
        add(campoSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        botaoConfirmar = new JButton("Confirmar Transferência");

        botaoConfirmar.setPreferredSize(new Dimension(220, 40));
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.setBackground(new Color(11, 220, 5));
        botaoConfirmar.setForeground(Color.WHITE);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarTransferencia();
            }
        });
        add(botaoConfirmar, gbc);
    }

    private void realizarTransferencia() {
        String contaOrigem = campoContaOrigem.getText();
        String contaDestino = campoContaDestino.getText();
        String valorStr = campoValor.getText();
        String senha = new String(campoSenha.getPassword());

        Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();

        if (contaOrigem.isEmpty() || contaDestino.isEmpty() || valorStr.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valor;
        try {
            valor = Double.parseDouble(valorStr);
            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(usuario.getSenha())) {
            JOptionPane.showMessageDialog(this, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UUID idContaDestino;
        try {
            idContaDestino = UUID.fromString(contaDestino);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "ID da conta de destino inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean sucesso = transferenciaUseCase.realizarTransferencia(usuario, idContaDestino, valor);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar a transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao realizar a transferência: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoContaOrigem.setText("");
        campoContaDestino.setText("");
        campoValor.setText("");
        campoSenha.setText("");
    }
}
