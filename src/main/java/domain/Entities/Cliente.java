package domain.Entities;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;
import java.util.UUID;

public class Cliente extends Usuario 
{
    protected UUID idContaOrigem;
    protected UUID idContaDestino;
    
    public Cliente(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException 
    {
        super(cpf, senha, tipoUsuario, valorEmConta);
    }
    
    public void RealizarTransferencia(UUID idContaOrigem, UUID idContaDestino, double valorParaTransferir)
    {
        
    }
}
