package infrastructure;

import domain.Entities.SolicitarTransferencia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorTransferencias {

    private final String arquivoTransferencias = "transferencias.json";
    private final List<SolicitarTransferencia> transferenciasPendentes;

    public GerenciadorTransferencias() {
        this.transferenciasPendentes = carregarTransferenciasPendentes();
    }

    public List<SolicitarTransferencia> getTransferenciasPendentes() {
        return transferenciasPendentes;
    }

    public void adicionarSolicitacao(SolicitarTransferencia solicitacao) {
        transferenciasPendentes.add(solicitacao);
        salvarTransferencias();
    }

    public void salvarTransferencias() {
        try (Writer writer = new FileWriter(arquivoTransferencias)) {
            Gson gson = new Gson();
            gson.toJson(transferenciasPendentes, writer);
            transferenciasPendentes = carregarTransferencias();
        } catch (IOException e) {
        }
    }

    private List<SolicitarTransferencia> carregarTransferencias() {
        try (Reader reader = new FileReader(arquivoTransferencias)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<SolicitarTransferencia>>() {
            }.getType());

        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<SolicitarTransferencia> carregarTransferenciasPendentes() {
        try (Reader reader = new FileReader(arquivoTransferencias)) {
            Gson gson = new Gson();
            List<SolicitarTransferencia> todasTransferencias = gson.fromJson(reader, new TypeToken<List<SolicitarTransferencia>>() {
            }.getType());

            return todasTransferencias.stream()
                    .filter(transferencia -> !transferencia.isAprovado())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
