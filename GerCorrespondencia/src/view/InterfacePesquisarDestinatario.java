package view;

import controle.Comando;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.Destinatario;
import model.dao.DestinatarioDAO;

import javax.swing.*;

public class InterfacePesquisarDestinatario implements Comando{

    @Override
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

        pesquisaDestinatario(nome);
    }

    private void pesquisaDestinatario(String nome) {
        DestinatarioDAO dao = new DestinatarioDAO();
        try {
            Destinatario destinatario = dao.pesquisarDestinatario(nome);
            JOptionPane.showMessageDialog(null, destinatario.toString());
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }
}
