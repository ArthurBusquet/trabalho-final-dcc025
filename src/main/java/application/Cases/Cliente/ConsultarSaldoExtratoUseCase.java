/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases.Cliente;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Extrato;
import domain.Entities.Usuarios.Usuario;

public class ConsultarSaldoExtratoUseCase 
{
    public void consultarSaldoExtrato(Usuario usuario, String senhaInserida) throws DadoInseridoInvalidoException 
    {
        if (!usuario.getSenha().equals(senhaInserida))
        {
            throw new DadoInseridoInvalidoException("Senha do usuário para consultar Saldo e Extrato!");
        }
        System.out.println("Saldo atual: R$" + usuario.getValorEmConta());
        System.out.println("Extrato:");
        for (Extrato extrato : usuario.getExtratos())
        {
            System.out.println(extrato);
        }
    }
}
