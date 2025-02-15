package domain.Exceptions;

public class UsuarioJaExisteException extends RuntimeException 
{
    public UsuarioJaExisteException(String cpf)
    {
        super("Usuário com o CPF " + cpf + " já existe.");
    }
}
