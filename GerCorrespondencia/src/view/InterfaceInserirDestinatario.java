package view;

import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.DestinatarioJaCadastradoException;
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



        boolean salvo = salvaDestinatario(nome, numero);
        if (salvo) {JOptionPane.showMessageDialog(null, "Destinatario cadastrado com sucesso!");
        }
    }

    private boolean salvaDestinatario(String nome, String numero) {
        boolean disponivel = false;
        Destinatario destinatario = new Destinatario(nome, numero);

        DestinatarioDAO dao = new DestinatarioDAO();
        try {
            disponivel = dao.pesquisarCadastro(destinatario);
        } catch (DestinatarioJaCadastradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        if(disponivel) {dao.inserir(destinatario);}
        return disponivel;
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
