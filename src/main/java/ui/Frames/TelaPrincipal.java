package ui.Frames;

import javax.swing.*;
import java.awt.*;
import domain.Enum.TipoUsuarioEnum;
import ui.Controllers.GerenciadorTela;
import ui.Controllers.GerenciadorPainel;
import ui.Panels.MenuLateralCaixa;
import ui.Panels.MenuLateralCliente;
import ui.Panels.PainelRendaFixa;
import ui.Panels.PainelRendaVariavel;
import ui.Panels.PainelSaldoExtrato;
import ui.Panels.PainelSolicitacaoCredito;
import ui.Panels.PainelTransferencia;

public class TelaPrincipal extends JFrame {

    private final GerenciadorTela controlador;
    private final JPanel painelMenusLaterais;
    private final JPanel painelAcoes;
    private final CardLayout cardLayoutMenus;
    private final CardLayout cardLayoutPaineisAcoes;
    private final GerenciadorPainel gerenciadorPainel;

    public TelaPrincipal(GerenciadorTela controlador, TipoUsuarioEnum tipoUsuario) {
        this.controlador = controlador;
        setTitle("Sistema de Usuários");
        setSize(800, 600);
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

        gerenciadorPainel.adicionarPainel("Transferencia", new PainelTransferencia());
        gerenciadorPainel.adicionarPainel("SaldoExtrato", new PainelSaldoExtrato());
        gerenciadorPainel.adicionarPainel("RendaFixa", new PainelRendaFixa());
        gerenciadorPainel.adicionarPainel("RendaVariavel", new PainelRendaVariavel());
        gerenciadorPainel.adicionarPainel("SolicitacaoCredito", new PainelSolicitacaoCredito());
        
        gerenciadorPainel.adicionarPainel("atendimentoSaque", new PainelSolicitacaoCredito());

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
