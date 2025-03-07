/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Interfaces;

import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import java.util.List;

public interface UsuarioRepository 
{
    void adicionar(Usuario usuario);
    void remover(String cpf);
    void editar(String cpf, String novaSenha, TipoUsuarioEnum tipo);
    List<Usuario> listarUsuarios();
    boolean existeUsuario(String cpf);
}
