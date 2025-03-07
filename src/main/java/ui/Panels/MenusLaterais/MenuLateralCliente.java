/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Panels.MenusLaterais;

import ui.Panels.MenusLaterais.MenuLateral;
import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;

public class MenuLateralCliente extends MenuLateral {

    public MenuLateralCliente(GerenciadorTela controlador, GerenciadorPainel gerenciadorPainel) {
        super(controlador, gerenciadorPainel);

        textoSaudacao.setText("Olá, cliente!");

        adicionarBotao("Solicitar Transferência", "SolicitacaoTransferencia");
        adicionarBotao("Consultar saldo e extrato", "SaldoExtrato");
        adicionarBotao("Investir em renda fixa", "InvestirRendaFixa");
        adicionarBotao("Investir em renda variável", "InvestirRendaVariavel");
        adicionarBotao("Solicitar crédito", "SolicitacaoCredito");
    }
}
