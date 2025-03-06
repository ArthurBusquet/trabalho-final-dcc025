package ui.Panels.PaineisAcoes;

import application.Cases.Cliente.SolicitarTransferenciaUseCase;
import application.Controllers.SessaoUsuario;
import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuarios.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class PainelSolicitacaoTransferencia extends PainelAcoes {

    private JTextField campoContaOrigem;
    private JTextField campoContaDestino;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar;
    private GridBagConstraints gbc;

    private final SolicitarTransferenciaUseCase transferenciaUseCase;

    public PainelSolicitacaoTransferencia(SolicitarTransferenciaUseCase transferenciaUseCase) {
        super();
        this.transferenciaUseCase = transferenciaUseCase;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        inicializar();
    }

    private void inicializar() {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Conta de Origem:"), gbc);

        gbc.gridx = 1;
        campoContaOrigem = new JTextField(20);
        campoContaOrigem.setText(SessaoUsuario.getInstancia().getUsuarioLogado().getIdConta().toString());
        campoContaOrigem.setEditable(false);
        campoContaOrigem.setPreferredSize(new Dimension(400, 40));
        campoContaOrigem.setEnabled(false);
        add(campoContaOrigem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Conta de Destino:"), gbc);

        gbc.gridx = 1;
        campoContaDestino = new JTextField(20);
        campoContaDestino.setPreferredSize(new Dimension(400, 40));
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
        botaoConfirmar.setPreferredSize(new Dimension(400, 40));
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.setBackground(new Color(11, 220, 5));
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarTransferencia();
            }
        });
        add(botaoConfirmar, gbc);
    }

    private void solicitarTransferencia() {
        try {

            String contaDestinoStr = campoContaDestino.getText();
            String valorStr = campoValor.getText();
            String senha = new String(campoSenha.getPassword());

            if (contaDestinoStr.isEmpty() || valorStr.isEmpty() || senha.isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos!");
            }

            double valor = Double.parseDouble(valorStr);
            if (valor <= 0) {
                throw new IllegalArgumentException("O valor deve ser maior que zero!");
            }

            Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();
            if (usuario == null || !usuario.getSenha().equals(senha)) {
                throw new SecurityException("Senha incorreta!");
            }

            UUID idContaDestino = UUID.fromString(contaDestinoStr);

            boolean sucesso = transferenciaUseCase.solicitarTransferencia(usuario, idContaDestino, valor);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao realizar a transferência.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (DadoInseridoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoContaDestino.setText("");
        campoValor.setText("");
        campoSenha.setText("");
    }
}
