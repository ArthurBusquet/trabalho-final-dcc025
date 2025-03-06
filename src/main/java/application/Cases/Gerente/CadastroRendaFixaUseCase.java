package application.Cases.Gerente;

import application.Exceptions.OperacaoInvalidaException;
import domain.Entities.RendaFixa;
import domain.Entities.Usuarios.Gerente;
import infrastructure.Interfaces.InvestimentosRepository;

public class CadastroRendaFixaUseCase {
    private final InvestimentosRepository investimentosRepository;
    
    public CadastroRendaFixaUseCase(InvestimentosRepository investimentosRepository) {
        this.investimentosRepository = investimentosRepository;
    }

    public void cadastrarRendaFixa(Gerente gerente, String senhaGerente, String nome, double taxaRendimento, int prazoMinimo, int prazoMaximo) throws OperacaoInvalidaException {
        if (!gerente.getSenha().equals(senhaGerente)) {
            throw new OperacaoInvalidaException("Senha do gerente incorreta.");
        }
        
        RendaFixa rendaFixa = new RendaFixa(nome, taxaRendimento, prazoMinimo, prazoMaximo);
        investimentosRepository.salvarRendaFixa(rendaFixa);
    }
}
