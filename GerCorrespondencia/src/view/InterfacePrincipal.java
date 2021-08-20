/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;

import javax.swing.*;


public class InterfacePrincipal implements Comando{

    public void executar() {
        String opcao = null;
        int controle;
        do {
                opcao = leDados("Escolha a opcao:"
                        + "\n1 - Sair"
                        + "\n2 - Registrar Movimento de Entrada de Correspondencia"
                        + "\n3 - Registrar Movimento de Saida de Correspondencia"
                        + "\n4 - Pesquisar Se Existem Correspondencias a Serem Entregues para um Determinado Destinatario"
                        + "\n5 - Listar todos os Movimentos de um Determinado Destinatario"
                        + "\n6 - Listar todos os Movimentos de uma Determinada Data"
                        + "\n7 - Listar todos os Movimentos Registrados no Sistema"
                        + "\n8 - Cadastrar Destinatario"
                        + "\n9 - Pesquisar Dados de um Determinado Destinatario"
                        + "\n10 - Excluir um Determinado Destinatario");

                Processador.direcionar(opcao);

        } while (!opcao.equals("0"));
    }

    public String leDados(String mensagem) {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        String saida = processaSelecao(opcao);
        return saida;
    }


    //verificacoes para evitar erros
    private String processaSelecao(String opcao) {
        if (opcao == null) { //trata a saida se usuario pressionar cancela
            Processador.direcionar("1");
            return null;
        }
        opcao = opcao.replaceAll("\\s{2,}", " ").trim();

        if (opcao.length() == 0) { //trata se pressionar ok com a caixa vazia
            JOptionPane.showMessageDialog(null, "Escolha uma opção válida");
            Processador.direcionar("0");
            return null;
        }
        if (!opcao.matches("[0-9]*")) { //trata se digitar algo que nao for numero
            JOptionPane.showMessageDialog(null, "Escolha uma opção válida");
            Processador.direcionar("0");
            return null;
        }

        int controle = Integer.parseInt(opcao);
        if (controle < 1 || controle > 10) { //trata se digitar numeros diferentes das opcoes
            JOptionPane.showMessageDialog(null, "Escolha uma opção válida");
            Processador.direcionar("0");
            return null;
        } else  {
            return opcao;
        }
    }
    
}
