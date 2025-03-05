package infrastructure.Interfaces;

import domain.Entities.SolicitacaoCredito;
import java.util.List;

public interface SolicitacoesCreditoRepository 
{
    void salvarSolicitacao(SolicitacaoCredito solicitacao);
    List<SolicitacaoCredito> getSolicitacoesNaoAprovadas();
    void atualizarSolicitacao(SolicitacaoCredito solicitacao);
}
