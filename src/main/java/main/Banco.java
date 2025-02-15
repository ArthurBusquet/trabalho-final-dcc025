package main;

import application.Cases.AdicionarUsuarioUseCase;
import application.Cases.EditarUsuarioUseCase;
import application.Cases.ListarUsuariosUseCase;
import application.Cases.RemoverUsuarioUseCase;
import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioJaExisteException;
import infrastructure.GerenciadorUsuarios;

public class Banco
{
    public static void main(String[] args) throws DadoInseridoInvalidoException
    {
        try
        {
            GerenciadorUsuarios repositorio = new GerenciadorUsuarios();

            AdicionarUsuarioUseCase adicionar = new AdicionarUsuarioUseCase(repositorio);
            EditarUsuarioUseCase editar = new EditarUsuarioUseCase(repositorio);
            RemoverUsuarioUseCase remover = new RemoverUsuarioUseCase(repositorio);
            ListarUsuariosUseCase listar = new ListarUsuariosUseCase(repositorio);

            adicionar.executar("09090909091", "1234567Isa", TipoUsuarioEnum.CLIENTE);
            adicionar.executar("09090909090", "1234567Isa", TipoUsuarioEnum.CAIXA);

            System.out.println("Usuários cadastrados:");
            listar.executar().forEach(u -> System.out.println(u.getCpf() + " - " + u.getTipoUsuario()));

            editar.executar("09090909091", "1234567Is", TipoUsuarioEnum.CAIXA);
            remover.executar("09090909093");
            remover.executar("09090909091");
            System.out.println("Após edição e remoção:");
            listar.executar().forEach(u -> System.out.println(u.getCpf() + " - " + u.getTipoUsuario()));           
        }
        catch(UsuarioJaExisteException e)
        {
            System.out.println(e.getMessage());
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
