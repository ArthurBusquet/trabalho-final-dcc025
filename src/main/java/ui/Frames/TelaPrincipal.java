package ui.Frames;

import application.Cases.Caixa.AprovarTransferenciaUseCase;
import application.Cases.Caixa.RealizarDepositoUseCase;
import application.Cases.Cliente.SolicitarCreditoUseCase;
import application.Cases.Cliente.SolicitarTransferenciaUseCase;
import application.Controllers.SessaoUsuario;
import javax.swing.*;
import java.awt.*;
import domain.Enum.TipoUsuarioEnum;
import infrastructure.GerenciadorSolicitacoesCredito;
import infrastructure.GerenciadorTransferencias;
import infrastructure.GerenciadorUsuarios;
import ui.Controllers.GerenciadorTela;
import ui.Controllers.GerenciadorPainel;
import ui.Panels.MenusLaterais.MenuLateralCaixa;
import ui.Panels.MenusLaterais.MenuLateralCliente;
import ui.Panels.MenusLaterais.MenuLateralGerente;
import ui.Panels.PaineisAcoes.PainelProcessamentoDeposito;
import ui.Panels.PaineisAcoes.PainelInvestirRendaFixa;
import ui.Panels.PaineisAcoes.PainelInvestirRendaVariavel;
import ui.Panels.PaineisAcoes.PainelAprovarTransferencia;
import ui.Panels.PaineisAcoes.PainelAvaliacaoCredito;
import ui.Panels.PaineisAcoes.PainelRealizarSaque;
import ui.Panels.PaineisAcoes.PainelSaldoExtrato;
import ui.Panels.PaineisAcoes.PainelSolicitacaoCredito;
import ui.Panels.PaineisAcoes.PainelSolicitacaoTransferencia;

public class TelaPrincipal extends JFrame {

    private final GerenciadorTela controlador;
    private final JPanel painelMenusLaterais;
    private final JPanel painelAcoes;
    private final CardLayout cardLayoutMenus;
    private final CardLayout cardLayoutPaineisAcoes;
    private final GerenciadorPainel gerenciadorPainel;

    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorTransferencias gerenciadorTransferencias;
    private GerenciadorSolicitacoesCredito gerenciadorSolicitacoesCredito;

    public TelaPrincipal(GerenciadorTela controladorTela, TipoUsuarioEnum tipoUsuario) {
        this.controlador = controladorTela;

        gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorTransferencias = new GerenciadorTransferencias();
        gerenciadorSolicitacoesCredito = new GerenciadorSolicitacoesCredito();

        setTitle("Sistema de Banco");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        painelMenusLaterais = new JPanel();
        cardLayoutMenus = new CardLayout();
        painelMenusLaterais.setLayout(cardLayoutMenus);

        painelAcoes = new JPanel();
        cardLayoutPaineisAcoes = new CardLayout();
        painelAcoes.setLayout(cardLayoutPaineisAcoes);

        gerenciadorPainel = new GerenciadorPainel(painelAcoes);

        painelMenusLaterais.add(new MenuLateralCliente(controladorTela, gerenciadorPainel), "MenuCliente");
        painelMenusLaterais.add(new MenuLateralCaixa(controladorTela, gerenciadorPainel), "MenuCaixa");
        painelMenusLaterais.add(new MenuLateralGerente(controladorTela, gerenciadorPainel), "MenuGerente");

        SolicitarTransferenciaUseCase solicitarTransferenciaUseCase = new SolicitarTransferenciaUseCase(gerenciadorTransferencias);
        SolicitarCreditoUseCase solicitarCreditoUseCase = new SolicitarCreditoUseCase(gerenciadorSolicitacoesCredito);
        RealizarDepositoUseCase realizarDepositoUseCase = new RealizarDepositoUseCase(gerenciadorUsuarios);

        gerenciadorPainel.adicionarPainel("SolicitacaoTransferencia", new PainelSolicitacaoTransferencia(solicitarTransferenciaUseCase));
        gerenciadorPainel.adicionarPainel("SaldoExtrato", new PainelSaldoExtrato());
        gerenciadorPainel.adicionarPainel("InvestirRendaFixa", new PainelInvestirRendaFixa());
        gerenciadorPainel.adicionarPainel("InvestirRendaVariavel", new PainelInvestirRendaVariavel());

        gerenciadorPainel.adicionarPainel("SolicitacaoCredito", new PainelSolicitacaoCredito(solicitarCreditoUseCase));
        gerenciadorPainel.adicionarPainel("AtendimentoSaque", new PainelRealizarSaque(gerenciadorUsuarios));
        gerenciadorPainel.adicionarPainel("ProcessamentoDeposito", new PainelProcessamentoDeposito(realizarDepositoUseCase, gerenciadorUsuarios));
        gerenciadorPainel.adicionarPainel("AprovacaoTransferencia", new PainelAprovarTransferencia(gerenciadorTransferencias, gerenciadorUsuarios));

        gerenciadorPainel.adicionarPainel("AvaliacaoCredito", new PainelAvaliacaoCredito(gerenciadorSolicitacoesCredito));

        setUsuarioLogado(tipoUsuario);

        add(painelMenusLaterais, BorderLayout.WEST);
        add(painelAcoes, BorderLayout.CENTER);

    }

    public void setUsuarioLogado(TipoUsuarioEnum tipoUsuario) {
        switch (tipoUsuario) {
            case CLIENTE:
                cardLayoutMenus.show(painelMenusLaterais, "MenuCliente");
                break;
            case CAIXA:
                cardLayoutMenus.show(painelMenusLaterais, "MenuCaixa");
                break;
            case GERENTE:
                cardLayoutMenus.show(painelMenusLaterais, "MenuGerente");
                break;
        }
    }
}
