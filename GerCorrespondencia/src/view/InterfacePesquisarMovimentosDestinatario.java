package view;

import controle.*;
import exceptions.CampoVazioException;
import model.Movimento;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.List;


public class InterfacePesquisarMovimentosDestinatario implements Comando{
    public void executar() {
        String nome = null;
        boolean teste = true;

        do {
            try {
                nome = leDados("Informe o nome completo do destinatário a ser pesquisado");
                teste = false;
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        MovimentoDAO dao = new MovimentoDAO();
        List<Movimento> movimentos = dao.pesquisaDestinatario(nome);
        StringBuilder todasMovimentacoes = new StringBuilder();

        if(movimentos.isEmpty()) {
            todasMovimentacoes.append("Não há movimentações registradas para ").append(nome);
        } else {
            for(Movimento movimento : movimentos) {
                todasMovimentacoes.append(movimento.toString()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, todasMovimentacoes.toString(), "Todos Movimentos de " + nome, JOptionPane.INFORMATION_MESSAGE);
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.contains(" ") || opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }
}
