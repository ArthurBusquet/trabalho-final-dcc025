package infrastructure;

import domain.Entities.SolicitacaoCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorSolicitacoesCredito implements SolicitacoesCreditoRepository 
{
    private static final String ARQUIVO_JSON = "solicitacoes_credito.json";
    private final List<SolicitacaoCredito> solicitacoes;

    public GerenciadorSolicitacoesCredito() 
    {
        this.solicitacoes = carregarSolicitacoes();
    }

    private List<SolicitacaoCredito> carregarSolicitacoes() 
    {
        try 
        {
            Path path = Paths.get(ARQUIVO_JSON);
            if (!Files.exists(path)) 
            {
                return new ArrayList<>();
            }
            
            // le o arquivo e converte para uma lista de solicitacoes
            String json = new String(Files.readAllBytes(path));
            Type listType = new TypeToken<ArrayList<SolicitacaoCredito>>() {}.getType();
            return new Gson().fromJson(json, listType);
        } 
        catch (IOException e) 
        {          
            return new ArrayList<>();
        }
    }

    private void salvarSolicitacoes()
    {
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) 
        {
            new Gson().toJson(solicitacoes, writer);
        } 
        catch (IOException e)
        {
        }
    }

    @Override
    public void salvarSolicitacao(SolicitacaoCredito solicitacao) {
        solicitacoes.add(solicitacao);
        salvarSolicitacoes();
    }

    @Override
    public List<SolicitacaoCredito> getSolicitacoesNaoAprovadas() {
        return solicitacoes.stream()
                           .filter(solicitacao -> !solicitacao.isAprovada())
                           .collect(Collectors.toList());
    }

    @Override
    public void atualizarSolicitacao(SolicitacaoCredito solicitacao) {
        for (int i = 0; i < solicitacoes.size(); i++) {
            if (solicitacoes.get(i).getUsuario().equals(solicitacao.getUsuario()) &&
                solicitacoes.get(i).getValor() == solicitacao.getValor()) {
                solicitacoes.set(i, solicitacao);
                salvarSolicitacoes();
                return;
            }
        }
    }
}
