package ui.Frames;

import java.awt.*;
import javax.swing.*;

import domain.Enum.TipoUsuarioEnum;

import ui.Controllers.GerenciadorPainel;
import ui.Controllers.GerenciadorTela;
import ui.Panels.MenuLateral;
import ui.Panels.MenuLateralCliente;
import ui.Panels.PainelRendaFixa;
import ui.Panels.PainelRendaVariavel;
import ui.Panels.PainelSaldoExtrato;
import ui.Panels.PainelSolicitacaoCredito;
import ui.Panels.PainelTransferencia;

public class TelaPrincipal extends JFrame {

    private final GerenciadorTela controlador;

    public TelaPrincipal(GerenciadorTela controlador, TipoUsuarioEnum tipoUsuario) {
        this.controlador = controlador;
        setSize(1366, 768);
        setTitle("Sistema de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelAcoes = new JPanel();
        CardLayout cardLayout = new CardLayout();
        painelAcoes.setLayout(cardLayout);

        GerenciadorPainel gerenciadorPainel = new GerenciadorPainel(painelAcoes);

        MenuLateral menuLateral = new MenuLateralCliente(controlador, gerenciadorPainel);

        gerenciadorPainel.adicionarPainel("SaldoExtrato", new PainelSaldoExtrato());
        gerenciadorPainel.adicionarPainel("Transferencia", new PainelTransferencia());
        gerenciadorPainel.adicionarPainel("RendaFixa", new PainelRendaFixa());
        gerenciadorPainel.adicionarPainel("RendaVariavel", new PainelRendaVariavel());
        gerenciadorPainel.adicionarPainel("SolicitacaoCredito", new PainelSolicitacaoCredito());

        add(menuLateral, BorderLayout.WEST);
        add(painelAcoes, BorderLayout.CENTER);

    }
}
