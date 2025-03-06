package application.Cases.Cliente;

import domain.Entities.Usuarios.Usuario;
import domain.Entities.Extrato;
import java.util.List;
import java.util.Arrays;
import java.util.Date;
import application.Exceptions.DadoInseridoInvalidoException;
import application.Exceptions.OperacaoInvalidaException;
import infrastructure.GerenciadorUsuarios;

public class InvestirRendaVariavelUseCase 
{
    private final GerenciadorUsuarios gerenciador;
    
    public InvestirRendaVariavelUseCase(GerenciadorUsuarios gerenciador) 
    {
        this.gerenciador = gerenciador;
    }
    
    public List<String> consultarOpcoesRendaVariavel()
    {
        return Arrays.asList(
            "Fundos de Investimento",
            "Carteiras Diversificadas",
            "Ações",
            "ETFs",
            "REITs"
        );
    }
    
    public void investirRendaVariavel(Usuario usuario, String senhaInserida, String opcaoEscolhida, double valorInvestimento) throws DadoInseridoInvalidoException, OperacaoInvalidaException
    {
        if (!usuario.getSenha().equals(senhaInserida)) 
        {
            throw new OperacaoInvalidaException("Senha incorreta.");
        }
        
        if (valorInvestimento > usuario.getValorEmConta()) 
        {
            throw new OperacaoInvalidaException("Saldo insuficiente para investimento.");
        }
        
        usuario.setValorEmConta(usuario.getValorEmConta() - valorInvestimento);
        Extrato extrato = new Extrato(new Date(), "Investimento em " + opcaoEscolhida, valorInvestimento, usuario.getValorEmConta(), usuario.getIdConta(), null);
        usuario.adicionarExtrato(extrato);
        gerenciador.salvarUsuarios();
    }
}