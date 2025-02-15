package application.Cases;

import domain.Entities.Usuario.Usuario;
import domain.Interfaces.UsuarioRepository;
import java.util.List;

public class ListarUsuariosUseCase 
{
    private final UsuarioRepository usuarioRepository;

    public ListarUsuariosUseCase(UsuarioRepository usuarioRepository) 
    {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> executar() 
    {
        return usuarioRepository.listarUsuarios();
    }
}
