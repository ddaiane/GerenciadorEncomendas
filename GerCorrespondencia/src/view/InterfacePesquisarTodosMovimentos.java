package view;

import controle.Comando;
import exceptions.CampoVazioException;

import javax.swing.*;

public class InterfacePesquisarTodosMovimentos implements Comando {
    @Override
    public void executar() {
        System.out.println("Esse é o resultado da funcionalidade 7");
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