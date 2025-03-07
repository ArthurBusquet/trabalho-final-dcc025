/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Entities;

import domain.Entities.Usuarios.Usuario;
import java.util.Date;

public class SolicitacaoCredito {

    private final Usuario usuario;
    private final double valor;
    private final String tipoCredito;
    private final Date dataSolicitacao;
    private boolean aprovada;

    public SolicitacaoCredito(Usuario usuario, double valor, String tipoCredito) {
        this.usuario = usuario;
        this.valor = valor;
        this.tipoCredito = tipoCredito;
        this.dataSolicitacao = new Date();
        this.aprovada = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public double getValor() {
        return valor;
    }

    public String getTipoCredito() {
        return tipoCredito;
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
