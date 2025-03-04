package ui.Frames;

import javax.swing.*;

public class MenuOpcoesCliente extends MenuOpcoes {

    public MenuOpcoesCliente() {
        super("Menu Cliente");
    }

    @Override
    protected void configurarMenu() {
        JButton btnTransferencia = new JButton("Transferência");
        JButton btnConsultaSaldo = new JButton("Consulta de Saldo e Extrato");
        JButton btnInvestimentoFixa = new JButton("Investimento em Renda Fixa");
        JButton btnInvestimentoVariavel = new JButton("Investimento em Renda Variável");
        JButton btnSolicitarCredito = new JButton("Solicitação de Crédito");
        
        btnTransferencia.addActionListener(e -> abrirTransferencia());
        btnConsultaSaldo.addActionListener(e -> abrirConsultaSaldo());
        btnInvestimentoFixa.addActionListener(e -> abrirInvestimentoFixa());
        btnInvestimentoVariavel.addActionListener(e -> abrirInvestimentoVariavel());
        btnSolicitarCredito.addActionListener(e -> abrirSolicitacaoCredito());

        add(btnTransferencia);
        add(btnConsultaSaldo);
        add(btnInvestimentoFixa);
        add(btnInvestimentoVariavel);
        add(btnSolicitarCredito);
    }

    private void abrirTransferencia() {
        JOptionPane.showMessageDialog(this, "Tela de Transferência");
    }

    private void abrirConsultaSaldo() {
        JOptionPane.showMessageDialog(this, "Tela de Consulta de Saldo e Extrato");
    }

    private void abrirInvestimentoFixa() {
        JOptionPane.showMessageDialog(this, "Tela de Investimento em Renda Fixa");
    }

    private void abrirInvestimentoVariavel() {
        JOptionPane.showMessageDialog(this, "Tela de Investimento em Renda Variável");
    }

    private void abrirSolicitacaoCredito() {
        JOptionPane.showMessageDialog(this, "Tela de Solicitação de Crédito");
    }
}
