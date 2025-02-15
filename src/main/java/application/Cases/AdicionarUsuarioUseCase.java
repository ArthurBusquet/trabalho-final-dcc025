package application.Cases;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuario.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Interfaces.UsuarioRepository;

public class AdicionarUsuarioUseCase 
{
    private final UsuarioRepository usuarioRepository;

    public AdicionarUsuarioUseCase(UsuarioRepository usuarioRepository) 
    {
        this.usuarioRepository = usuarioRepository;
    }

    public void executar(String cpf, String senha, TipoUsuarioEnum tipo) throws DadoInseridoInvalidoException 
    {
        usuarioRepository.adicionar(new Usuario(cpf, senha, tipo));
    }
}
