package domain.Exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException
{
    public UsuarioNaoEncontradoException(String cpf)
    {
        super("Usuário com CPF " + cpf + " não encontrado.");
    }
}
