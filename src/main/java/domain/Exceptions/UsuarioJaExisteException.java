/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Exceptions;

public class UsuarioJaExisteException extends RuntimeException 
{
    public UsuarioJaExisteException(String cpf)
    {
        super("Usuário com o CPF " + cpf + " já existe.");
    }
}
