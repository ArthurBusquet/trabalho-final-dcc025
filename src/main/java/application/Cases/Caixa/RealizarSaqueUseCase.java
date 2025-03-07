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

public class RealizarSaqueUseCase 
{
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public RealizarSaqueUseCase(GerenciadorUsuarios gerenciadorUsuarios) 
    {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public void realizarSaque(Usuario usuario, double valorSaque, String senha) 
            throws OperacaoInvalidaException, DadoInseridoInvalidoException 
    {
        if (!usuario.getSenha().equals(senha)) 
        {
            throw new OperacaoInvalidaException("Senha incorreta.");
        }

        if (usuario.getValorEmConta() >= valorSaque) 
        {
            usuario.setValorEmConta(usuario.getValorEmConta() - valorSaque);

            Extrato extrato = new Extrato(new Date(), "Saque", valorSaque, usuario.getValorEmConta(), usuario.getIdConta(), null);
            usuario.adicionarExtrato(extrato);

            gerenciadorUsuarios.salvarUsuarios();
        } else {
            throw new OperacaoInvalidaException("Saldo insuficiente.");
        }
    }
}
