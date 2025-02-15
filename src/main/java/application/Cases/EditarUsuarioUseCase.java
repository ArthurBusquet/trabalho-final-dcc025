package application.Cases;

import domain.Enum.TipoUsuarioEnum;
import domain.Interfaces.UsuarioRepository;

public class EditarUsuarioUseCase 
{
    private final UsuarioRepository usuarioRepository;

    public EditarUsuarioUseCase(UsuarioRepository usuarioRepository) 
    {
        this.usuarioRepository = usuarioRepository;
    }

    public void executar(String cpf, String novaSenha, TipoUsuarioEnum tipo) 
    {
        usuarioRepository.editar(cpf, novaSenha, tipo);
    }
}
