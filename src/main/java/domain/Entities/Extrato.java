/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

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

    public Extrato(Date dataTransacao, String tipoTransacao, double valorTransacao, double saldoAposTransacao, UUID contaOrigem, UUID contaDestino) {
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.saldoAposTransacao = saldoAposTransacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public double getSaldoAposTransacao() {
        return saldoAposTransacao;
    }

    public UUID getContaOrigem() {
        return contaOrigem;
    }

    public UUID getContaDestino() {
        return contaDestino;
    }

    @Override
    public String toString() {
        return dataTransacao + " | " + tipoTransacao + " | R$" + valorTransacao + " | Saldo: R$" + saldoAposTransacao
                + (contaDestino != null ? " | Destino: " + contaDestino : "");
    }
}
