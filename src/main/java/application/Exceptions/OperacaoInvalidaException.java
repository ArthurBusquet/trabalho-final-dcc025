/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Exceptions;

public class OperacaoInvalidaException extends Exception {

    public OperacaoInvalidaException() {
    }

    public OperacaoInvalidaException(String msg) {
        super(msg);
    }
}
