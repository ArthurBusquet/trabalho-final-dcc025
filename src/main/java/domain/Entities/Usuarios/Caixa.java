
package domain.Entities.Usuarios;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;

public class Caixa extends Cliente
{  
    public Caixa(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException 
    {
        super(cpf, senha, tipoUsuario, valorEmConta);
    }
    
    public class Conta
    {
        private String numeroConta;
        private double saldo;
        private String senha;

        public Conta(String numeroConta, double saldo, String senha)
        {
            this.numeroConta = numeroConta;
            this.saldo = saldo;
            this.senha = senha;
        }

        public String getNumeroConta() 
        {
            return numeroConta;
        }

        public double getSaldo()
        {
            return saldo;
        }

        public void creditar(double valor)
        {
            this.saldo += valor;
        }

        public boolean debitar(double valor)
        {
            if (saldo >= valor) 
            {
                this.saldo -= valor;
                return true;
            }
            return false;
        }

        public String getSenha() 
        {
            return senha;
        }
    }   
}
