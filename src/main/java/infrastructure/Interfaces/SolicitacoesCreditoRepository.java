/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package infrastructure.Interfaces;

import domain.Entities.SolicitacaoCredito;
import java.util.List;

public interface SolicitacoesCreditoRepository 
{
    void salvarSolicitacao(SolicitacaoCredito solicitacao);
    List<SolicitacaoCredito> getSolicitacoesNaoAprovadas();
    void atualizarSolicitacao(SolicitacaoCredito solicitacao);
    void removerSolicitacao(SolicitacaoCredito solicitacao);
}
