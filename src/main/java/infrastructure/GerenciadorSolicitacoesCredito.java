package infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Entities.SolicitacaoCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

class GerenciadorSolicitacoesCredito implements SolicitacoesCreditoRepository 
{
    private static final String FILE_PATH = "solicitacoes_credito.json";
    private final Gson gson = new Gson();
    
    @Override
    public void salvarSolicitacao(SolicitacaoCredito solicitacao) 
    {
        List<SolicitacaoCredito> solicitacoes = getSolicitacoesNaoAprovadas();
        solicitacoes.add(solicitacao);
        salvarSolicitacoesNoArquivo(solicitacoes);
    }
    
    @Override
    public List<SolicitacaoCredito> getSolicitacoesNaoAprovadas() 
    {
        try (FileReader reader = new FileReader(FILE_PATH)) 
        {
            Type listType = new TypeToken<List<SolicitacaoCredito>>() {}.getType();
            List<SolicitacaoCredito> solicitacoes = gson.fromJson(reader, listType);
            return solicitacoes != null ? solicitacoes : new ArrayList<>();
        } 
        catch (IOException e) 
        {
            return new ArrayList<>();
        }
    }
    
    @Override
    public void atualizarSolicitacao(SolicitacaoCredito solicitacao)
    {
        List<SolicitacaoCredito> solicitacoes = getSolicitacoesNaoAprovadas();
        for (int i = 0; i < solicitacoes.size(); i++) 
        {
            if (solicitacoes.get(i).getUsuario().getIdConta().equals(solicitacao.getUsuario().getIdConta()))
            {
                solicitacoes.set(i, solicitacao);
                break;
            }
        }
        salvarSolicitacoesNoArquivo(solicitacoes);
    }
    
    @Override
    public void removerSolicitacao(SolicitacaoCredito solicitacao)
    {
        List<SolicitacaoCredito> solicitacoes = getSolicitacoesNaoAprovadas();
        solicitacoes.removeIf(s -> s.getUsuario().getIdConta().equals(solicitacao.getUsuario().getIdConta()));
        salvarSolicitacoesNoArquivo(solicitacoes);
    }
    
    private void salvarSolicitacoesNoArquivo(List<SolicitacaoCredito> solicitacoes)
    {
        try (FileWriter writer = new FileWriter(FILE_PATH)) 
        {
            gson.toJson(solicitacoes, writer);
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
