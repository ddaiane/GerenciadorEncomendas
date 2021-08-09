package view;

import controle.*;
import exceptions.CampoVazioException;

import javax.swing.*;


public class InterfacePesquisarMovimentosDestinatario implements Comando{
    public void executar() {
        //System.out.println("Esse é o resultado da funcionalidade 5");

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
