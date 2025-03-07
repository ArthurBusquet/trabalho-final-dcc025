/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package ui.Models;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import domain.Entities.Extrato;

public class TabelaExtratos extends DefaultTableModel {

    private final String[] colunas = {"Data", "Tipo", "Valor (R$)", "Saldo Após", "Conta Origem", "Conta Destino"};

    public TabelaExtratos(List<Extrato> extratos) {
        for (String coluna : colunas) {
            addColumn(coluna);
        }

        for (Extrato extrato : extratos) {
            Object[] linha = {
                extrato.getDataTransacao(),
                extrato.getTipoTransacao(),
                String.format("%.2f", extrato.getValorTransacao()),
                String.format("%.2f", extrato.getSaldoAposTransacao()),
                extrato.getContaOrigem(),
                extrato.getContaDestino()
            };
            addRow(linha);
        }
    }
}
