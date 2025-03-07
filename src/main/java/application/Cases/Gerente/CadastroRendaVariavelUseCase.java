/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases.Gerente;

import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.RendaVariavel;
import domain.Entities.Usuarios.Gerente;
import infrastructure.Interfaces.InvestimentosRepository;
public class CadastroRendaVariavelUseCase {
    private final InvestimentosRepository investimentosRepository;
    
    public CadastroRendaVariavelUseCase(InvestimentosRepository investimentosRepository)
    {
        this.investimentosRepository = investimentosRepository;
    }

    public void cadastrarRendaVariavel(Gerente gerente, String senhaGerente, String nome, double percentualRisco, double rentabilidadeEsperada) 
            throws OperacaoInvalidaException 
    {
        if (!gerente.getSenha().equals(senhaGerente)) 
        {
            throw new OperacaoInvalidaException("Senha do gerente incorreta.");
        }      
        RendaVariavel rendaVariavel = new RendaVariavel(nome, percentualRisco, rentabilidadeEsperada);
        investimentosRepository.salvarRendaVariavel(rendaVariavel);
    }
}