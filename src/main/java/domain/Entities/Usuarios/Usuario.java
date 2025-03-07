/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package domain.Entities.Usuarios;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Extrato;
import domain.Enum.TipoUsuarioEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario 
{
    protected String cpfUsuario;
    protected String senhaUsuario;
    protected TipoUsuarioEnum tipoUsuario;
    private UUID idConta;
    protected double valorEmConta;
    private List<Extrato> extratos;
    
    public Usuario(String cpf, String senha, TipoUsuarioEnum tipoUsuario, double valorEmConta) throws DadoInseridoInvalidoException 
    {
        setCpf(cpf);
        setSenha(senha);
        setTipoUsuario(tipoUsuario);
        setValorEmConta(valorEmConta);
        this.idConta = UUID.randomUUID();
        this.extratos = new ArrayList<>();
    }

    public Usuario(String cpf, String senha, double valorEmConta) throws DadoInseridoInvalidoException 
    {
        setCpf(cpf);
        setSenha(senha);
        this.tipoUsuario = TipoUsuarioEnum.CLIENTE;
        setValorEmConta(valorEmConta);
        this.idConta = UUID.randomUUID();
        this.extratos = new ArrayList<>();
    }

    private static boolean cpfEhValido(String cpf) 
    {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11)
            return false;
        return !cpf.matches("(\\d)\\1{10}"); // verificar se todos os dígitos são iguais (exemplo: 111.111.111-11)                 
    }
    
    private static boolean senhaEhValida(String senha) 
    {
        if (senha.length() < 6)       
            return false;
        
        return senha.matches(".*[A-Z].*") && // pelo menos 1 letra maiúscula
               senha.matches(".*[a-z].*") && // pelo menos 1 letra minúscula
               senha.matches(".*\\d.*");     // pelo menos 1 número
    }
    
    public String getCpf() 
    {
        return cpfUsuario;
    }

    public TipoUsuarioEnum getTipoUsuario() 
    {
        return tipoUsuario;
    }
    
    public void setCpf(String cpf) throws DadoInseridoInvalidoException
    {
        if (!cpfEhValido(cpf))
            throw new DadoInseridoInvalidoException("CPF");

        this.cpfUsuario = cpf;
    }
    
    public String getSenha()
    {
        return senhaUsuario;
    }
    
    public void setSenha(String senha) throws DadoInseridoInvalidoException
    {
        if (!senhaEhValida(senha))
            throw new DadoInseridoInvalidoException("Senha");
        
        this.senhaUsuario = senha;
    }
    
    public void setTipoUsuario(TipoUsuarioEnum tipo)
    {
        this.tipoUsuario = tipo;
    }
    
    public UUID getIdConta() 
    {
        return idConta;
    }

    public double getValorEmConta() 
    {
        return valorEmConta;
    }

    public void setValorEmConta(double valor) throws DadoInseridoInvalidoException 
    {
        if (valor < 0)
            throw new DadoInseridoInvalidoException("Valor");
        
        this.valorEmConta = valor;
    }
    
    public void adicionarExtrato(Extrato extrato)
    {
        extratos.add(extrato);
    }
    
    public void consultarExtrato() 
    {
        for (Extrato e : extratos) 
        {
            System.out.println(e);
        }
    }
    
    public List<Extrato> getExtratos() 
    {
        return extratos;
    }
}
