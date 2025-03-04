
package domain.Entities.Usuarios;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;

public class Caixa extends Cliente
{  
    public Caixa(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException {
        super(cpf, senha, tipoUsuario, valorEmConta);
    }
}
