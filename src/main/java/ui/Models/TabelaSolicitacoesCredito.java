package ui.Models;

import domain.Entities.SolicitacaoCredito;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaSolicitacoesCredito extends AbstractTableModel {

    private final String[] colunas = {"Cliente", "Tipo de Crédito", "Valor Solicitado"};
    private List<SolicitacaoCredito> solicitacoes;

    public TabelaSolicitacoesCredito(List<SolicitacaoCredito> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    @Override
    public int getRowCount() {
        return solicitacoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SolicitacaoCredito solicitacao = solicitacoes.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return solicitacao.getUsuario().getCpf();
            case 1:
                return solicitacao.getTipoCredito();
            case 2:
                return String.format("R$ %.2f", solicitacao.getValor());
            default:
                return null;
        }
    }
}
