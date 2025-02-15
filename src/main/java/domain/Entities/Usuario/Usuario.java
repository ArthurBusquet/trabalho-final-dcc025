package domain.Entities.Usuario;

import domain.Enum.TipoUsuarioEnum;
import application.Exceptions.DadoInseridoInvalidoException;

public class Usuario 
{
    protected final String cpfUsuario;
    protected final String senhaUsuario;
    protected final TipoUsuarioEnum tipoUsuario;
    
    public Usuario(String cpf, String senha, TipoUsuarioEnum tipoUsuario) throws DadoInseridoInvalidoException 
    {
        if (!cpfEhValido(cpf))
            throw new DadoInseridoInvalidoException("CPF");
        
        if (!senhaEhValida(senha))
            throw new DadoInseridoInvalidoException("Senha");
        
        this.cpfUsuario = cpf;
        this.senhaUsuario = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String cpf, String senha) throws DadoInseridoInvalidoException 
    {
        this(cpf, senha, TipoUsuarioEnum.CLIENTE);
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
    
}
