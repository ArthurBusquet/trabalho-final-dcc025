/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases.Caixa;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.SolicitarTransferencia;
import domain.Entities.Usuarios.Usuario;
import domain.Entities.Extrato;
import application.Exceptions.OperacaoInvalidaException;
import infrastructure.GerenciadorTransferencias;
import infrastructure.GerenciadorUsuarios;

import java.util.Date;

public class AprovarTransferenciaUseCase {

    private final GerenciadorTransferencias gerenciadorTransferencias;
    private final GerenciadorUsuarios gerenciadorUsuarios;

    public AprovarTransferenciaUseCase(GerenciadorTransferencias gerenciadorTransferencias, GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorTransferencias = gerenciadorTransferencias;
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public boolean aprovarTransferencia(SolicitarTransferencia solicitacao)
            throws OperacaoInvalidaException, DadoInseridoInvalidoException {
        Usuario usuarioOrigem = gerenciadorUsuarios.carregarUsuarios().stream()
                .filter(u -> u.getIdConta().equals(solicitacao.getIdContaOrigem()))
                .findFirst()
                .orElseThrow(() -> new OperacaoInvalidaException("Usuário origem não encontrado"));

        Usuario usuarioDestino = gerenciadorUsuarios.carregarUsuarios().stream()
                .filter(u -> u.getIdConta().equals(solicitacao.getIdContaDestino()))
                .findFirst()
                .orElseThrow(() -> new OperacaoInvalidaException("Usuário destino não encontrado"));

        if (usuarioOrigem.getValorEmConta() >= solicitacao.getValorTransferir()) {
            usuarioOrigem.setValorEmConta(usuarioOrigem.getValorEmConta() - solicitacao.getValorTransferir());
            usuarioDestino.setValorEmConta(usuarioDestino.getValorEmConta() + solicitacao.getValorTransferir());

            Extrato extratoOrigem = new Extrato(new Date(), "Transferência Saída", solicitacao.getValorTransferir(), usuarioOrigem.getValorEmConta(), usuarioOrigem.getIdConta(), usuarioDestino.getIdConta());
            Extrato extratoDestino = new Extrato(new Date(), "Transferência Entrada", solicitacao.getValorTransferir(), usuarioDestino.getValorEmConta(), usuarioOrigem.getIdConta(), usuarioDestino.getIdConta());

            usuarioOrigem.adicionarExtrato(extratoOrigem);
            usuarioDestino.adicionarExtrato(extratoDestino);

            solicitacao.aprovar();

            gerenciadorTransferencias.salvarTransferencias();
            gerenciadorUsuarios.salvarUsuarios();

            return true;
        } else {
            throw new OperacaoInvalidaException("Saldo insuficiente para transferência.");
        }
    }
}
