package domain.Entities;

import java.util.UUID;

public class SolicitarTransferencia 
{

    private final UUID idContaOrigem;
    private final UUID idContaDestino;
    private final double valorTransferir;
    private boolean aprovado;

    public SolicitarTransferencia(UUID idContaOrigem, UUID idContaDestino, double valorTransferir) 
    {
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
        this.valorTransferir = valorTransferir;
        this.aprovado = false; 
    }

    public UUID getIdContaOrigem() {
        return idContaOrigem;
    }

    public UUID getIdContaDestino() {
        return idContaDestino;
    }

    public double getValorTransferir() {
        return valorTransferir;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void aprovar() {
        this.aprovado = true;
    }
}
