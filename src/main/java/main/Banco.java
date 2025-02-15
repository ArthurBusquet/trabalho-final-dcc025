package main;

import domain.Entities.Usuario.Usuario;
import application.Exceptions.DadoInseridoInvalidoException;

public class Banco
{
    public static void main(String[] args) throws DadoInseridoInvalidoException
    {
        try
        {
            Usuario usuario = new Usuario("1234", "123");

            System.out.println(usuario.getTipoUsuario());            
        }
        catch(DadoInseridoInvalidoException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
