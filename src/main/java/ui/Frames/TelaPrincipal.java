package ui.Frames;

import application.Cases.Caixa.RealizarDepositoUseCase;
import application.Cases.Cliente.SolicitarTransferenciaUseCase;
import javax.swing.*;
import java.awt.*;
import domain.Enum.TipoUsuarioEnum;
import infrastructure.GerenciadorTransferencias;
import infrastructure.GerenciadorUsuarios;
import ui.Controllers.GerenciadorTela;
import ui.Controllers.GerenciadorPainel;
import ui.Panels.MenusLaterais.MenuLateralCaixa;
import ui.Panels.MenusLaterais.MenuLateralCliente;
import ui.Panels.PaineisAcoes.PainelProcessamentoDeposito;
import ui.Panels.PaineisAcoes.PainelInvestirRendaFixa;
import ui.Panels.PaineisAcoes.PainelInvestirRendaVariavel;
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

    protected GerenciadorUsuarios gerenciadorUsuarios;
    protected GerenciadorTransferencias gerenciadorTransferencias;

    public TelaPrincipal(GerenciadorTela controlador, TipoUsuarioEnum tipoUsuario) {
        this.controlador = controlador;

        gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorTransferencias = new GerenciadorTransferencias();

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

        painelMenusLaterais.add(new MenuLateralCliente(controlador, gerenciadorPainel), "MenuCliente");
        painelMenusLaterais.add(new MenuLateralCaixa(controlador, gerenciadorPainel), "MenuCaixa");

        SolicitarTransferenciaUseCase solicitarTransferenciaUseCase = new SolicitarTransferenciaUseCase(gerenciadorTransferencias);

        gerenciadorPainel.adicionarPainel("SolicitacaoTransferencia", new PainelSolicitacaoTransferencia(solicitarTransferenciaUseCase));

        gerenciadorPainel.adicionarPainel("SaldoExtrato", new PainelSaldoExtrato());
        gerenciadorPainel.adicionarPainel("InvestirRendaFixa", new PainelInvestirRendaFixa());
        gerenciadorPainel.adicionarPainel("InvestirRendaVariavel", new PainelInvestirRendaVariavel());
        gerenciadorPainel.adicionarPainel("SolicitacaoCredito", new PainelSolicitacaoCredito());

        RealizarDepositoUseCase realizarDepositoUseCase = new RealizarDepositoUseCase(gerenciadorUsuarios);

        gerenciadorPainel.adicionarPainel("AtendimentoSaque", new PainelSolicitacaoCredito());
        gerenciadorPainel.adicionarPainel("ProcessamentoDeposito", new PainelProcessamentoDeposito(realizarDepositoUseCase));

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
