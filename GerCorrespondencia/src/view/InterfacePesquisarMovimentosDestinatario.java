package view;

import controle.*;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.Destinatario;
import model.Movimento;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.List;


public class InterfacePesquisarMovimentosDestinatario implements Comando{
    public void executar() {
        String nome = null;
        boolean teste = true;
        Destinatario destinatario = null;

        do {
            try {
                nome = leDados("Informe o nome completo do destinatário a ser pesquisado");
                destinatario = pesquisaNome(nome);
                if(destinatario != null) {teste = false;}
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        MovimentoDAO dao = new MovimentoDAO();
        List<Movimento> movimentos = dao.pesquisaDestinatario(destinatario);
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

    private Destinatario pesquisaNome(String nome) { //confere se existe destinatario com aquele nome
        DestinatarioDAO dao = new DestinatarioDAO();
        Destinatario destinatario = null;
        try {
            destinatario = dao.pesquisarDestinatario(nome);
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return destinatario;
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao == null) { //trata a saida se usuario pressionar cancela
            Processador.direcionar("0");
            return null;
        }
        opcao = opcao.replaceAll("\\s{2,}", " ").trim();
        if (opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }
}
