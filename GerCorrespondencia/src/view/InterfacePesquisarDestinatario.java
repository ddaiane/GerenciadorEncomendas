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
        String numero = null;
        boolean teste = true;

        do {
            try {
                numero = leDados("Informe o numero do apartamento a ser pesquisado");
                teste = false;
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        pesquisaDestinatario(numero);
    }

    private void pesquisaDestinatario(String numero) {
        DestinatarioDAO dao = new DestinatarioDAO();
        try {
            Destinatario destinatario = dao.pesquisarDestinatario(numero);
            JOptionPane.showMessageDialog(null, destinatario.toString());
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
