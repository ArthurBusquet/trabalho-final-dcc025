package domain.Entities;

import java.util.Date;
import java.util.UUID;

public class Extrato {
    private Date dataTransacao;
    private String tipoTransacao; // "DEPÓSITO", "SAQUE", "TRANSFERÊNCIA"
    private double valorTransacao;
    private double saldoAposTransacao;
    private UUID contaOrigem;
    private UUID contaDestino;

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
