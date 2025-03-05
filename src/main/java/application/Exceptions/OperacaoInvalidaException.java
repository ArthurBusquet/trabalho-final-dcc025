package application.Exceptions;

public class OperacaoInvalidaException extends Exception {

    public OperacaoInvalidaException() {
    }

    public OperacaoInvalidaException(String msg) {
        super(msg);
    }
}
