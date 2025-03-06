package infrastructure;

import domain.Entities.SolicitarTransferencia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.List;

public class GerenciadorTransferencias 
{
    private final String arquivoTransferencias = "transferencias.json";
    private List<SolicitarTransferencia> transferenciasPendentes;

    public GerenciadorTransferencias() 
    {
        this.transferenciasPendentes = carregarTransferencias();
    }

    public List<SolicitarTransferencia> getTransferenciasPendentes() 
    {
        return transferenciasPendentes;
    }

    public void adicionarSolicitacao(SolicitarTransferencia solicitacao) 
    {
        transferenciasPendentes.add(solicitacao);
        salvarTransferencias();
    }

    public void salvarTransferencias() 
    {
        try (Writer writer = new FileWriter(arquivoTransferencias)) 
        {
            Gson gson = new Gson();
            gson.toJson(transferenciasPendentes, writer);
        } 
        catch (IOException e)
        {
        }
    }

    private List<SolicitarTransferencia> carregarTransferencias() 
    {
        try (Reader reader = new FileReader(arquivoTransferencias)) 
        {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<SolicitarTransferencia>>() {}.getType());
        } 
        catch (IOException e) 
        {
            return null;
        }
    }
}
