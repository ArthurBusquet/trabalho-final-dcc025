package application.Controllers;

import domain.Enum.TipoUsuarioEnum;
import ui.Frames.MenuOpcoesCliente;

public class MenuController {

    public static void abrirMenuPorTipo(TipoUsuarioEnum tipoUsuario) {
        switch (tipoUsuario) {
            case CLIENTE:
                new MenuOpcoesCliente().setVisible(true);
                break;
            case CAIXA:
                // new MenuCaixa().setVisible(true);
                break;
            case GERENTE:
                // new MenuGerente().setVisible(true);
                break;
        }
    }
}
