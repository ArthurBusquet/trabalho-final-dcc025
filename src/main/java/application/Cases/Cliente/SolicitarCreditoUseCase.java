/*
    Arthur Busquet Nunes Abreu | Matricula: 202135018
    Isabella Mourão dos Santos Dias | Matricula: 202165066AC
*/

package application.Cases.Cliente;

import application.Exceptions.DadoInseridoInvalidoException;
import domain.Entities.Usuarios.Usuario;
import domain.Entities.SolicitacaoCredito;
import infrastructure.Interfaces.SolicitacoesCreditoRepository;
import application.Exceptions.OperacaoInvalidaException;

import java.util.List;

public class SolicitarCreditoUseCase {

    private final SolicitacoesCreditoRepository repositorio;

    public SolicitarCreditoUseCase(SolicitacoesCreditoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void solicitarCredito(Usuario usuario, double valorSolicitado, String tipoCredito) throws DadoInseridoInvalidoException {

        if (valorSolicitado < 0) {
            throw new DadoInseridoInvalidoException("Valor solicitado invalido");
        }

        if (!tipoCredito.equals("EMPRESTIMO") && !tipoCredito.equals("FINANCIAMENTO")) {
            throw new DadoInseridoInvalidoException("Tipo de crédito solicitado é invalido");
        }
        SolicitacaoCredito solicitacao = new SolicitacaoCredito(usuario, valorSolicitado, tipoCredito);
        repositorio.salvarSolicitacao(solicitacao);

    }

    public List<SolicitacaoCredito> listarSolicitacoesPendentes() {
        return repositorio.getSolicitacoesNaoAprovadas();
    }

    public void aprovarSolicitacao(SolicitacaoCredito solicitacao, String senhaInserida) throws OperacaoInvalidaException {
        if (!solicitacao.getUsuario().getSenha().equals(senhaInserida)) {
            throw new OperacaoInvalidaException("Senha incorreta");
        }

        solicitacao.aprovar();
        repositorio.atualizarSolicitacao(solicitacao);
    }
}
