/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

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

    private JTextField campoCpf;
    private JTextField campoValorDeposito;
    private JButton botaoConfirmar;
    private final RealizarDepositoUseCase realizarDepositoUseCase;
    private GridBagConstraints gbc;
    private GerenciadorUsuarios gerenciadorUsuarios;

    public PainelProcessamentoDeposito(RealizarDepositoUseCase realizarDepositoUseCase, GerenciadorUsuarios gerenciadorUsuarios) {
        super();
        this.gerenciadorUsuarios = gerenciadorUsuarios;
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
        gbc.gridwidth = 1;

        add(new JLabel("CPF do cliente:"), gbc);

        campoCpf = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        campoCpf.setPreferredSize(new Dimension(400, 30));
        gbc.gridwidth = 2;
        add(campoCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Valor do Depósito (R$):"), gbc);

        campoValorDeposito = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        campoValorDeposito.setPreferredSize(new Dimension(400, 30));
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
            String cpf = campoCpf.getText();
            String valorStr = campoValorDeposito.getText();

            if (cpf.isEmpty() || valorStr.isEmpty()) {
                throw new IllegalArgumentException("Preencha todos os campos!");
            }

            double valorDeposito = Double.parseDouble(valorStr);

            Usuario usuario = gerenciadorUsuarios.buscarUsuarioPorCpf(cpf);
            if (usuario == null || usuario.getTipoUsuario() != TipoUsuarioEnum.CLIENTE) {
                throw new OperacaoInvalidaException("Conta inválida");
            }

            realizarDepositoUseCase.realizarDeposito(usuario, valorDeposito);

            JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            campoCpf.setText("");
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
