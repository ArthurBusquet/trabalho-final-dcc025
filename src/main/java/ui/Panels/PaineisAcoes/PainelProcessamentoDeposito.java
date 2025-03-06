package ui.Panels.PaineisAcoes;

import ui.Panels.PaineisAcoes.PainelAcoes;
import application.Cases.Caixa.RealizarDepositoUseCase;
import application.Controllers.SessaoUsuario;
import application.Exceptions.DadoInseridoInvalidoException;
import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import infrastructure.GerenciadorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelProcessamentoDeposito extends PainelAcoes {

    private JTextField campoNumeroConta;
    private JTextField campoValorDeposito;
    private JButton botaoConfirmar;
    private final RealizarDepositoUseCase realizarDepositoUseCase;
    private GridBagConstraints gbc;

    public PainelProcessamentoDeposito(RealizarDepositoUseCase realizarDepositoUseCase) {
        super();
        this.realizarDepositoUseCase = realizarDepositoUseCase;
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Processamento de Depósito");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Número da Conta:"), gbc);

        campoNumeroConta = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(campoNumeroConta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Valor do Depósito (R$):"), gbc);

        campoValorDeposito = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(campoValorDeposito, gbc);

        botaoConfirmar = new JButton("Confirmar Depósito");
        botaoConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoConfirmar.setBackground(new Color(50, 150, 50));
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.setFocusPainted(false);
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarDeposito();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(botaoConfirmar, gbc);
    }

    private void processarDeposito() {
        try {
            String numeroConta = campoNumeroConta.getText();
            String valorStr = campoValorDeposito.getText();

            if (numeroConta.isEmpty() || valorStr.isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos!");
            }

            double valorDeposito = Double.parseDouble(valorStr);

            Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();
            if (usuario == null || usuario.getTipoUsuario() != TipoUsuarioEnum.CAIXA) {
                throw new OperacaoInvalidaException("Conta inválida ou usuário não logado.");
            }

            realizarDepositoUseCase.realizarDeposito(usuario, valorDeposito);

            JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            campoNumeroConta.setText("");
            campoValorDeposito.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | OperacaoInvalidaException | DadoInseridoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
