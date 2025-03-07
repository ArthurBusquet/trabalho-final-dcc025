/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Exceptions;

public class DadoInseridoInvalidoException extends Exception
{
    public DadoInseridoInvalidoException(String dado)
    {
        super("O Dado '" + dado + "' inserido está inválido!");
    }
}
