package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import exceptions.TipoNaoIdentificadoException;
import model.*;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;
import model.dao.CorrespondenciaDAO;

import java.util.Objects;

import javax.swing.*;

public class InterfaceRegistrarSaida implements Comando {

    public void executar() {
        String quemRetira = null;
        String sair = null;
        Destinatario destinatario = null;
        Calendar data = null;
        status status = null;
        boolean teste = true;

        //Colocar exceção?
        if (status == true) {
            System.out.println("Essa correspondencia ja foi retirada.");
        }

        teste = true;
        do {
            try {
                quemRetira = leDados("Informe o nome de quem está retirando a correspondencia: ");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);

        teste = true;
        do {
            try {
                destinatario = leDados("Informe o destinatario da correspondencia: ");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


        teste = true;
        do {
            try {
                data = leDados("Informe a data (AAAA/MM/DD) em que esta sendo retirada: ");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


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
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            }
        } while (teste);


        public String leDados (String mensagem) throws CampoVazioException {
            String opcao = JOptionPane.showInputDialog(null, mensagem);
            if (opcao.contains(" ") || opcao.length() == 0) {
                throw new CampoVazioException(mensagem);
            } else {
                return opcao;
            }
        }
    }

}
