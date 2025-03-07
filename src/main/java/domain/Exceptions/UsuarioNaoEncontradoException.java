/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException
{
    public UsuarioNaoEncontradoException(String cpf)
    {
        super("Usuário com CPF " + cpf + " não encontrado.");
    }
}
