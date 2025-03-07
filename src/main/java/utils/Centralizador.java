/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package utils;

import javax.swing.*;
import java.awt.*;

public class Centralizador extends JPanel {

    public Centralizador(JComponent componente) {
        setLayout(new GridLayout(1, 1));
        add(componente);
    }
}
