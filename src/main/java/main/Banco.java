package main;

import javax.swing.*;

import application.Cases.AdicionarUsuarioUseCase;
import application.Cases.EditarUsuarioUseCase;
import application.Cases.ListarUsuariosUseCase;
import application.Cases.RemoverUsuarioUseCase;
import application.Controllers.MenuController;
import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuarios.Usuario;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioJaExisteException;
import infrastructure.GerenciadorUsuarios;

import ui.Frames.TelaLogin;
import ui.Enum.TipoTelaAutenticacaoEnum;

public class Banco 
{
    public static void main(String[] args) 
    {
        try 
        {
            GerenciadorUsuarios repositorio = new GerenciadorUsuarios();
            AdicionarUsuarioUseCase adicionar = new AdicionarUsuarioUseCase(repositorio);
            
            TelaLogin tela = new TelaLogin(new GerenciadorUsuarios(), TipoTelaAutenticacaoEnum.LOGIN, usuario -> {
                System.out.println("Usuário autenticado: " + usuario.getCpf());
                MenuController.abrirMenuPorTipo(usuario.getTipoUsuario());
            });
            tela.setVisible(true);
            
            // Criar usuários para teste
            adicionar.executar("09090909091", "1234567Isa", TipoUsuarioEnum.CLIENTE, 1500);
            adicionar.executar("09090909090", "1234567Isa", TipoUsuarioEnum.CAIXA, 17.50);

            // Chamar tela de login com callback para abrir o menu após autenticação
            SwingUtilities.invokeLater(() -> {
                new TelaLogin(repositorio, TipoTelaAutenticacaoEnum.LOGIN, usuario -> {
                    System.out.println("Usuário autenticado: " + usuario.getCpf());
                    MenuController.abrirMenuPorTipo(usuario.getTipoUsuario());
                });
            });
            
        } catch (UsuarioJaExisteException | DadoInseridoInvalidoException e) 
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

