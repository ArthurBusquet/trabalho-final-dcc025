/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Controllers;

import domain.Entities.Usuarios.Usuario;

public class SessaoUsuario {

    private static SessaoUsuario instancia;
    private Usuario usuarioLogado;

    private SessaoUsuario() {

    }

    public static SessaoUsuario getInstancia() {
        if (instancia == null) {
            instancia = new SessaoUsuario();
        }
        return instancia;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void logout() {
        this.usuarioLogado = null;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }
}
