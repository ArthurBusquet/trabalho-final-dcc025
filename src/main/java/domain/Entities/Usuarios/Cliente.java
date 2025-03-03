package domain.Entities.Usuarios;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;

public class Cliente extends Usuario {
    
    public Cliente(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException {
        super(cpf, senha, tipoUsuario, valorEmConta);
    }
}
