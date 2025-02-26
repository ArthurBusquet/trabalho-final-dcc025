package ui.Panels;

import java.awt.Color;
import javax.swing.*;

public class PainelConfirmacaoSenha extends PainelAutenticacao {

    public PainelConfirmacaoSenha() {
        super();
    }

    @Override
    protected void limparCampos() {
        campoSenha.setText("");
        campoSenha.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mensagemErro.setText("");
    }

    @Override
    protected void autenticar() {
        String senha = new String(campoSenha.getPassword());

        if (senha.isEmpty()) {
            mensagemErro.setText("Digite a senha!");
            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
        if (!senha.equals("senha123")) {
            mensagemErro.setText("Senha incorreta.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Senha confirmada com sucesso!");
    }
}
