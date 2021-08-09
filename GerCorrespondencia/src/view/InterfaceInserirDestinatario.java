package view;

import controle.Comando;
import exceptions.CampoVazioException;
import model.Destinatario;
import model.dao.DestinatarioDAO;

import javax.swing.*;

public class InterfaceInserirDestinatario implements Comando {
    @Override
    public void executar() {
        String nome = null;
        String numero = null;
        boolean teste = true;

        do {
            try {
                nome = leDados("Informe o Nome do novo destinatário");
                teste = false;
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        teste = true;
        do {
            try {
                numero = leDados("Informe o Numero do novo destinatário");
                teste = false;
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        salvaDestinatario(nome, numero);
    }

    private void salvaDestinatario(String nome, String numero) {
        Destinatario destinatario = new Destinatario(nome, numero);
        DestinatarioDAO dao = new DestinatarioDAO();
        dao.inserir(destinatario);
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem).replaceAll("\\s{2,}", " ").trim();
        if (opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }
}
