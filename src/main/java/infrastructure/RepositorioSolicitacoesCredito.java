package infrastructure;

import domain.Entities.SolicitacaoCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class RepositorioSolicitacoesCredito implements SolicitacoesCreditoRepository 
{
    private final List<SolicitacaoCredito> solicitacoes;

    public RepositorioSolicitacoesCredito() 
    {        
        this.solicitacoes = new ArrayList<>();
    }

    @Override
    public void salvarSolicitacao(SolicitacaoCredito solicitacao)
    {
        solicitacoes.add(solicitacao); // adiciona a solicitação à lista
    }

    @Override
    public List<SolicitacaoCredito> getSolicitacoesNaoAprovadas() 
    {
        // retorna todas as solicitações que ainda não foram aprovadas
        return solicitacoes.stream()
                           .filter(solicitacao -> !solicitacao.isAprovada())
                           .collect(Collectors.toList());
    }

    @Override
    public void atualizarSolicitacao(SolicitacaoCredito solicitacao) 
    {
        // atualiza a solicitação na lista (substitui a solicitação existente)
        for (int i = 0; i < solicitacoes.size(); i++) {
            if (solicitacoes.get(i).getUsuario().equals(solicitacao.getUsuario()) &&
                solicitacoes.get(i).getValor() == solicitacao.getValor()) {
                solicitacoes.set(i, solicitacao); // substitui a solicitação na lista
                return;
            }
        }
    }
}
