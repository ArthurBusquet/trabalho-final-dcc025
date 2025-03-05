package domain.Entities;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuarios.Usuario;
import java.util.Date;
import infrastructure.GerenciadorUsuarios;

public class AgenciaDeInvestimento 
{
    private final GerenciadorUsuarios gerenciador;

    public AgenciaDeInvestimento(GerenciadorUsuarios gerenciador) 
    {
        this.gerenciador = gerenciador;
    }

    public boolean investirRendaFixa(Usuario usuario, double valorInvestido) throws DadoInseridoInvalidoException 
    {
        if (usuario.getValorEmConta() < valorInvestido) 
        {
            System.out.println("Saldo insuficiente para investimento.");
            return false;
        }
        
        usuario.setValorEmConta(usuario.getValorEmConta() - valorInvestido);
        Extrato extrato = new Extrato(new Date(), "INVESTIMENTO RENDA FIXA", valorInvestido, usuario.getValorEmConta(), usuario.getIdConta(), null);
        usuario.adicionarExtrato(extrato);
        gerenciador.salvarUsuarios();
        
        System.out.println("Investimento em renda fixa realizado com sucesso!");
        return true;
    }
    
    public boolean investirRendaVariavel(Usuario usuario, double valorInvestido) throws DadoInseridoInvalidoException 
    {
        if (usuario.getValorEmConta() < valorInvestido) 
        {
            System.out.println("Saldo insuficiente para investimento.");
            return false;
        }
        
        usuario.setValorEmConta(usuario.getValorEmConta() - valorInvestido);
        Extrato extrato = new Extrato(new Date(), "INVESTIMENTO RENDA VARIÁVEL", valorInvestido, usuario.getValorEmConta(), usuario.getIdConta(), null);
        usuario.adicionarExtrato(extrato);
        gerenciador.salvarUsuarios();
        
        System.out.println("Investimento em renda variável realizado com sucesso!");
        return true;
    }
}