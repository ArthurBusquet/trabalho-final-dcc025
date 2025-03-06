package application.Cases.Cliente;

import domain.Entities.Usuarios.Usuario;
import domain.Entities.SolicitacaoCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;
import application.Exceptions.OperacaoInvalidaException;

import java.util.List;

public class SolicitarCreditoUseCase 
{
    private final SolicitacoesCreditoRepository repositorio;
    
    public SolicitarCreditoUseCase(SolicitacoesCreditoRepository repositorio) 
    {
        this.repositorio = repositorio;
    }

    public void solicitarCredito(Usuario usuario, double valorSolicitado) 
    {
        SolicitacaoCredito solicitacao = new SolicitacaoCredito(usuario, valorSolicitado);
        repositorio.salvarSolicitacao(solicitacao);
    }

    public List<SolicitacaoCredito> listarSolicitacoesPendentes() 
    {
        return repositorio.getSolicitacoesNaoAprovadas();
    }

    public void aprovarSolicitacao(SolicitacaoCredito solicitacao, String senhaGerente) throws OperacaoInvalidaException 
    {
        if (!solicitacao.getUsuario().getSenha().equals(senhaGerente))
        {
            throw new OperacaoInvalidaException("Senha incorreta");
        }
        
        solicitacao.aprovar();
        repositorio.atualizarSolicitacao(solicitacao);
    }
}
