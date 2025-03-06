package ui.Panels.PaineisAcoes;

import ui.Panels.PaineisAcoes.PainelAcoes;
import application.Controllers.SessaoUsuario;
import domain.Entities.Usuarios.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSolicitacaoCredito extends PainelAcoes {

    private JTextField campoValor;
    private JTextField campoParcelas;
    private JLabel labelTaxaJuros;
    private JLabel labelValorParcela;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar;

    private GridBagConstraints gbc;

    public PainelSolicitacaoCredito() {
        super();
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titulo = new JLabel("Solicitação de Crédito");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Valor solicitado (R$):"), gbc);

        campoValor = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(campoValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Número de parcelas:"), gbc);

        campoParcelas = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(campoParcelas, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Taxa de juros (%):"), gbc);

        labelTaxaJuros = new JLabel("2,5"); // Exemplo de taxa de juros
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(labelTaxaJuros, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Valor da parcela (R$):"), gbc);

        labelValorParcela = new JLabel("0,00");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(labelValorParcela, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Senha:"), gbc);

        campoSenha = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridy = 5;
        add(campoSenha, gbc);

        botaoConfirmar = new JButton("Confirmar Solicitação");
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarSolicitacao();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoConfirmar, gbc);
    }

    private void confirmarSolicitacao() {
        String valorStr = campoValor.getText();
        String parcelasStr = campoParcelas.getText();
        String senha = new String(campoSenha.getPassword());

        if (valorStr.isEmpty() || parcelasStr.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double valor = Double.parseDouble(valorStr);
        int parcelas = Integer.parseInt(parcelasStr);

        Usuario usuario = SessaoUsuario.getInstancia().getUsuarioLogado();
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double taxaJuros = Double.parseDouble(labelTaxaJuros.getText());
        double valorParcela = calcularValorParcela(valor, parcelas, taxaJuros);
        labelValorParcela.setText(String.format("%.2f", valorParcela));

        JOptionPane.showMessageDialog(this, "Solicitação confirmada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    }

    private double calcularValorParcela(double valor, int parcelas, double taxaJuros) {
        double juros = valor * (taxaJuros / 100);
        return (valor + juros) / parcelas;
    }
}
