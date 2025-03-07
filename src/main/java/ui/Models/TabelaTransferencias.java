package ui.Models;

import domain.Entities.SolicitarTransferencia;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaTransferencias extends AbstractTableModel {

    private final String[] colunas = {"Conta Origem", "Conta Destino", "Valor"};
    private List<SolicitarTransferencia> transferencias;

    public TabelaTransferencias(List<SolicitarTransferencia> transferencias) {
        this.transferencias = transferencias;
    }

    @Override
    public int getRowCount() {
        return transferencias.size();
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
        SolicitarTransferencia transferencia = transferencias.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return transferencia.getIdContaOrigem();
            case 1:
                return transferencia.getIdContaDestino();
            case 2:
                return String.format("R$ %.2f", transferencia.getValorTransferir());
            default:
                return null;
        }
    }

    public void atualizarDados(List<SolicitarTransferencia> novasTransferencias) {
        this.transferencias = novasTransferencias;
        fireTableDataChanged();
    }
}
