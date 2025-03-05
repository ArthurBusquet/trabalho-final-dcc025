package domain.Entities;

import java.util.Date;
import java.util.UUID;

public class Extrato {
    private final Date dataTransacao;
    private final String tipoTransacao; // "DEPÓSITO", "SAQUE", "TRANSFERÊNCIA"
    private final double valorTransacao;
    private final double saldoAposTransacao;
    private final UUID contaOrigem;
    private final UUID contaDestino;

    public Extrato(Date dataTransacao, String tipoTransacao, double valorTransacao, double saldoAposTransacao, UUID contaOrigem, UUID contaDestino) 
    {
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.saldoAposTransacao = saldoAposTransacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    @Override
    public String toString() 
    {
        return dataTransacao + " | " + tipoTransacao + " | R$" + valorTransacao + " | Saldo: R$" + saldoAposTransacao +
               (contaDestino != null ? " | Destino: " + contaDestino : "");
    }
}
