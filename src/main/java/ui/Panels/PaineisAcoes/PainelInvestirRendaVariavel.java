package ui.Panels.PaineisAcoes;

import ui.Panels.PaineisAcoes.PainelAcoes;
import java.awt.*;
import javax.swing.*;

public class PainelInvestirRendaVariavel extends PainelAcoes {

    public PainelInvestirRendaVariavel() {
        super();
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        add(new JLabel("Painel Renda Variavel"));
    }
}
