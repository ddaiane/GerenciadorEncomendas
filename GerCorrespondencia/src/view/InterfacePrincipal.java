/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;

import javax.swing.*;

/**
 *
 * @author Karen
 */
public class InterfacePrincipal implements Comando{

    public void executar() {
        String opcao = null;
        do {
            try{
                opcao = leDados("Escolha a opcao:"
                        + "\n1 - Sair"
                        + "\n2 - Registrar Movimento de Entrada de Correspondencia"
                        + "\n3 - Registrar Movimento de Saida de Correspondencia"
                        + "\n4 - Pesquisar Se Existem Correspondencias para Serem Entregues para um Determinado Destinatario"
                        + "\n5 - Listar todos os Movimentos de um Determinado Destinatario"
                        + "\n6 - Listar todos os Movimentos de uma Determinada Data"
                        + "\n7 - Listar todos os Movimentos Registrados no Sistema"
                        + "\n8 - Cadastrar um Destinatario"
                        + "\n9 - Pesquisar Dados de um Determinado Destinatario"
                        + "\n10 - Excluir um Determinado Destinatario");

                Processador.direcionar(opcao);
            } catch(CampoVazioException cve){
                JOptionPane.showMessageDialog(null,cve.getMessage() + " Digite novamente");
            }
        } while (opcao==null || !opcao.equals("0"));
    }
    
    public static String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null,mensagem);
        if (opcao.contains(" ") || opcao.length()==0) {
            throw new CampoVazioException("Campo Vazio!");
        }
        else {
            return opcao;
        }
    }
    
}
