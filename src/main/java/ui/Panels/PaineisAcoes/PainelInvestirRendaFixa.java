/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Panels.PaineisAcoes;

import java.awt.*;
import javax.swing.*;

public class PainelInvestirRendaFixa extends PainelAcoes {

    public PainelInvestirRendaFixa() {
        super();
        inicializar();
    }

    private void inicializar() {
        setLayout(new GridBagLayout());
        add(new JLabel("Painel Renda Fixa"));
    }
}
