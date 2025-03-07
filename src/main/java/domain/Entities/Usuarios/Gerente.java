/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Entities.Usuarios;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;

public class Gerente extends Caixa
{
    public Gerente(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException {
        super(cpf, senha, tipoUsuario, valorEmConta);
    } 
}
