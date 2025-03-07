/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package main;

import application.Cases.AdicionarUsuarioUseCase;
import application.Exceptions.DadoInseridoInvalidoException;
import domain.Enum.TipoUsuarioEnum;
import domain.Exceptions.UsuarioJaExisteException;
import infrastructure.GerenciadorUsuarios;
import ui.Controllers.GerenciadorTela;

public class Banco {

    public static void main(String[] args) throws DadoInseridoInvalidoException {

        try {
            GerenciadorTela controlador = new GerenciadorTela();

            // Inicia o sistema com a tela de login
            controlador.mostrarTelaLogin();

            GerenciadorUsuarios repositorio = new GerenciadorUsuarios();
            AdicionarUsuarioUseCase adicionar = new AdicionarUsuarioUseCase(repositorio);

            // Criar usuários para teste
            adicionar.executar("09090909091", "1234567Isa", TipoUsuarioEnum.CLIENTE, 1500);
            adicionar.executar("09090909090", "1234567Isa", TipoUsuarioEnum.CAIXA, 17.50);

        } catch (UsuarioJaExisteException | DadoInseridoInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
