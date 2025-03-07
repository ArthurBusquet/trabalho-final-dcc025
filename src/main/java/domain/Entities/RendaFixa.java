/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Entities;

public class RendaFixa {
    private final String nome;
    private final double taxaRendimento;
    private final int prazoMinimo;
    private final int prazoMaximo;
    
    public RendaFixa(String nome, double taxaRendimento, int prazoMinimo, int prazoMaximo) 
    {
        this.nome = nome;
        this.taxaRendimento = taxaRendimento;
        this.prazoMinimo = prazoMinimo;
        this.prazoMaximo = prazoMaximo;
    }
}
