package application.Cases;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Extrato;
import domain.Entities.Usuarios.Usuario;
import domain.Exceptions.UsuarioNaoEncontradoException;
import infrastructure.GerenciadorUsuarios;
import java.util.Date;
import java.util.UUID;

public class TransferenciaUseCase 
{
    private final GerenciadorUsuarios gerenciador;

    public TransferenciaUseCase(GerenciadorUsuarios gerenciador) 
    {
        this.gerenciador = gerenciador;
    }

    public boolean realizarTransferencia(Usuario usuarioOrigem, UUID idContaDestino, double valorParaTransferir) throws DadoInseridoInvalidoException 
    {
        try
        {
            if (usuarioOrigem.getValorEmConta() < valorParaTransferir) 
            {
                System.out.println("Saldo insuficiente. Transferência não realizada.");
                return false;
            }
            
            Usuario usuarioDestino = gerenciador.listarUsuarios().stream()
                .filter(u -> u.getIdConta().equals(idContaDestino))
                .findFirst()
                .orElse(null);
            
            if (usuarioDestino == null) 
            {
                System.out.println("Conta de destino não encontrada. Transferência não realizada.");
                return false;
            }
            
            usuarioOrigem.setValorEmConta(usuarioOrigem.getValorEmConta() - valorParaTransferir);
            usuarioDestino.setValorEmConta(usuarioDestino.getValorEmConta() + valorParaTransferir);
            
            Extrato extratoOrigem = new Extrato(new Date(), "TRANSFERÊNCIA SAÍDA", valorParaTransferir, usuarioOrigem.getValorEmConta(), usuarioOrigem.getIdConta(), idContaDestino);
            Extrato extratoDestino = new Extrato(new Date(), "TRANSFERÊNCIA ENTRADA", valorParaTransferir, usuarioDestino.getValorEmConta(), usuarioOrigem.getIdConta(), idContaDestino);
            
            usuarioOrigem.adicionarExtrato(extratoOrigem);
            usuarioDestino.adicionarExtrato(extratoDestino);
            
            gerenciador.salvarUsuarios();
            
            System.out.println("Transferência realizada com sucesso!");
            return true;
            
        } 
        catch (UsuarioNaoEncontradoException e) 
        {
            System.out.println("Usuário não encontrado: " + e.getMessage());
            return false;
        }
    }
    
    public void consultarSaldoExtrato(Usuario usuario) 
    {
        System.out.println("Saldo atual: R$" + usuario.getValorEmConta());
        System.out.println("Extrato:");
        for (Extrato extrato : usuario.getExtratos())
        {
            System.out.println(extrato);
        }
    }
}
