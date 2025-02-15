package application.Exceptions;

public class DadoInseridoInvalidoException extends Exception
{
    public DadoInseridoInvalidoException(String dado)
    {
        super("O Dado '" + dado + "' inserido está inválido!");
    }
}
