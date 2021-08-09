package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.TipoNaoIdentificadoException;
import model.Carta;

public class InterfaceRegistrarEntrada implements Comando {

    public void executar() {
        String quemRecebe = null;
        String numeroImovel = null;
        String tipo = null;
        String sair = null;
        boolean teste = true;

        do {
            try {
                tipo = leTipo("Digite o tipo: \n1 - Carta \n2 -  Pacote");
                teste = false;
            } catch (CampoVazioException | TipoNaoIdentificadoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } while (teste);

        teste = true;
        do {
            try {
                numeroImovel = leDados("Informe o número do imóvel do destinatário");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);

        teste = true;
        do {
            try {
                quemRecebe = leDados("Informe o nome de quem está recebendo essa correspondencia");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);

            System.out.println("Esse é o resultado da funcionalidade 2");

        teste = true;
        do {
            try {
                sair = leDados("Digite 0 para voltar");
                int i = Integer.parseInt(sair);
                if (i != 0) continue;
                if (i == 0) {
                    Processador.direcionar("0");
                }
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            }
        } while (teste);

        if(tipo.equals("1")) {
    //   todo         Carta carta = new Carta();
        } else if (tipo.equals("2")){
            //todo Pacote pacote = new Pacote
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

    public String leTipo(String mensagem) throws CampoVazioException, TipoNaoIdentificadoException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.contains(" ") || opcao.length() == 0) {
            throw new CampoVazioException("Digite 1 ou 2.");
        } else if(!opcao.equals("1") && !opcao.equals("2")) {
            throw new TipoNaoIdentificadoException();
        }

        return opcao;
    }

}
