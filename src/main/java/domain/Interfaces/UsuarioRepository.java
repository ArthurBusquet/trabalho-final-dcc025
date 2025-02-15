package domain.Interfaces;

import domain.Entities.Usuario.Usuario;
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
