package application.Cases.Gerente;

import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.SolicitacaoCredito;
import domain.Entities.Usuarios.Gerente;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;

public class AvaliacaoCreditoUseCase 
{
    private final SolicitacoesCreditoRepository solicitacoesRepository;
    
    public AvaliacaoCreditoUseCase(SolicitacoesCreditoRepository solicitacoesRepository)
    {
        this.solicitacoesRepository = solicitacoesRepository;
    }

    public void avaliarCredito(Gerente gerente, String senhaGerente, SolicitacaoCredito solicitacao, boolean aprovar) 
            throws OperacaoInvalidaException
    {
        if (!gerente.getSenha().equals(senhaGerente)) 
        {
            throw new OperacaoInvalidaException("Senha do gerente incorreta.");
        }
        if (aprovar) 
        {
            solicitacao.aprovar();
            solicitacoesRepository.atualizarSolicitacao(solicitacao);
        } 
        else 
        {
            solicitacoesRepository.removerSolicitacao(solicitacao);
        }
    }
}