package ui.Panels;

import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public class MenuLateralCliente extends MenuLateral {

    public MenuLateralCliente(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {
        super(controlador, gerenciadorPainel);

        adicionarBotao("Realizar Transferência", "Transferencia");
        adicionarBotao("Consultar saldo e extrato", "SaldoExtrato");
        adicionarBotao("Investir em renda fixa", "RendaFixa");
        adicionarBotao("Investir em renda variável", "RendaVariavel");
        adicionarBotao("Solicitar crédito", "SolicitacaoCredito");
    }
}
