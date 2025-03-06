package application.Cases.Cliente;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.SolicitarTransferencia;
import domain.Entities.Usuarios.Usuario;
import infrastructure.GerenciadorTransferencias;
import java.util.UUID;

public class SolicitarTransferenciaUseCase 
{
    private final GerenciadorTransferencias gerenciadorTransferencias;

    public SolicitarTransferenciaUseCase(GerenciadorTransferencias gerenciadorTransferencias) 
    {
        this.gerenciadorTransferencias = gerenciadorTransferencias;
    }

    public boolean solicitarTransferencia(Usuario usuarioOrigem, UUID idContaDestino, double valorParaTransferir) throws DadoInseridoInvalidoException 
    {
        if (usuarioOrigem.getValorEmConta() < valorParaTransferir) 
        {
            throw new DadoInseridoInvalidoException("Saldo insuficiente.");
        }
        
        SolicitarTransferencia solicitacao = new SolicitarTransferencia(usuarioOrigem.getIdConta(), idContaDestino, valorParaTransferir);
        gerenciadorTransferencias.adicionarSolicitacao(solicitacao);
        return true;
    }  
}
