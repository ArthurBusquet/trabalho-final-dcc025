package domain.Entities;

public class RendaVariavel
{
    private final String nome;
    private final double percentualRisco;
    private final double rentabilidadeEsperada;
    
    public RendaVariavel(String nome, double percentualRisco, double rentabilidadeEsperada) 
    {
        this.nome = nome;
        this.percentualRisco = percentualRisco;
        this.rentabilidadeEsperada = rentabilidadeEsperada;
    }
}
