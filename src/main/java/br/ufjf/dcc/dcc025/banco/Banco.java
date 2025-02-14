package br.ufjf.dcc.dcc025.banco;

import Entities.Usuario.Usuario;
import Exceptions.DadoInseridoInvalidoException;

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
            
        }
    }
}
