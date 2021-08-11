package view;

import controle.Comando;
import exceptions.CampoVazioException;
import model.Movimento;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.List;

public class InterfacePesquisarTodosMovimentos implements Comando {
    @Override
    public void executar() {
        MovimentoDAO dao = new MovimentoDAO();
        List<Movimento> movimentos = dao.pesquisar();
        StringBuilder todasMovimentacoes = new StringBuilder();

        if(movimentos.isEmpty()) {
            todasMovimentacoes.append("Não há movimentações registradas.");
        } else {
            for(Movimento movimento : movimentos) {
                todasMovimentacoes.append(movimento.toString()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, todasMovimentacoes.toString(), "Todos Movimentos", JOptionPane.INFORMATION_MESSAGE);

    }

}
