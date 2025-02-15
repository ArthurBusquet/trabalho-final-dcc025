package application.Cases;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuario.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioJaExisteException;
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
        if (cpf == null || cpf.isBlank())         
            throw new DadoInseridoInvalidoException("CPF");
        
        if (senha  == null || senha.isBlank())       
            throw new DadoInseridoInvalidoException("Senha");
        
        if (tipo == null)        
            throw new DadoInseridoInvalidoException("Tipo usuário");
        
        if (usuarioRepository.existeUsuario(cpf))       
            throw new UsuarioJaExisteException(cpf);
        
        Usuario novoUsuario = new Usuario(cpf, senha, tipo);
        usuarioRepository.adicionar(novoUsuario);
    }
}
