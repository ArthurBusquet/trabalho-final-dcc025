/*
Arthur Busquet Nunes Abreu | Matricula: 202135018
Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases.Caixa;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuarios.Usuario;
import domain.Entities.Extrato;
import application.Exceptions.OperacaoInvalidaException;
import infrastructure.GerenciadorUsuarios;

import java.util.Date;

public class RealizarDepositoUseCase 
{
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public RealizarDepositoUseCase(GerenciadorUsuarios gerenciadorUsuarios) 
    {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public void realizarDeposito(Usuario usuario, double valorDeposito) 
            throws OperacaoInvalidaException, DadoInseridoInvalidoException 
    {
        if (valorDeposito <= 0) 
        {
            throw new OperacaoInvalidaException("Valor inválido para depósito.");
        }

        usuario.setValorEmConta(usuario.getValorEmConta() + valorDeposito);

        Extrato extrato = new Extrato(new Date(), "Depósito", valorDeposito, usuario.getValorEmConta(), usuario.getIdConta(), null);
        usuario.adicionarExtrato(extrato);

        gerenciadorUsuarios.salvarUsuarios();
    }
}
