package ui.Panels;

import java.awt.*;
import javax.swing.*;

public class PainelSaldoExtrato extends PainelAcoes {

    public PainelSaldoExtrato() {
        inicializar();
    }

    private void inicializar() {
        add(new JLabel("Painel Saldo e Extrato"), BorderLayout.CENTER);
    }
}
