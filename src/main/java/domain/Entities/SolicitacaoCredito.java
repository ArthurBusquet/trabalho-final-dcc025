package domain.Entities;

import domain.Entities.Usuarios.Usuario;
import java.util.Date;

public class SolicitacaoCredito {
    private final Usuario usuario;
    private final double valor;
    private final Date dataSolicitacao;
    private boolean aprovada;

    public SolicitacaoCredito(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
        this.dataSolicitacao = new Date();
        this.aprovada = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public void aprovar() {
        this.aprovada = true;
    }
}
