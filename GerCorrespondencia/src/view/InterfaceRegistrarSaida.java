package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.*;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;
import model.dao.CorrespondenciaDAO;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

public class InterfaceRegistrarSaida implements Comando {

    public void executar() {
        String quemRetira = null;
        Destinatario destinatario = null;
        Calendar data = null;
        //status status = null;
        boolean teste = true;


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
                String nomeDestinatario = leDados("Informe o nome do destinatario da correspondencia");
                destinatario = pesquisaNome(nomeDestinatario);
                if(destinatario != null) {teste = false;}
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


        List<Correspondencia> aRetirar = naoRetiradas(destinatario);
        int idParaBaixa;
        do {
            idParaBaixa = selecionaCorrespondencia(aRetirar);
        } while(idParaBaixa != 0); //todo arrumar condições
        if (idParaBaixa == 0) {Processador.direcionar("0");}




    }

    private int selecionaCorrespondencia (List<Correspondencia> aRetirar) {
        int opcao = 0;
        boolean teste = true;
        do {
            try {
                opcao = Integer.parseInt(leDados("Digite a ID da correspondencia que está sendo retirada:" +
                        imprimeNaoRetiradas(aRetirar) + "\nPara sair digite 0."));
                teste = false;
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, "Digite um código!");
            }
           // if (opcao ...) {teste = false;} todo inventar uma condição que confirme se a opção digitada realmente é uma id valida, talvez adicionar todas id numa array e ver se a opção ta contida na array
        } while (teste);
        return opcao;
    }

    private String imprimeNaoRetiradas(List<Correspondencia> lista) {
        String saida = " ";
        if(lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Esse destinatario nao possui correspondencia a ser retirada.");
        }
        else {
            for(Correspondencia correspondencia : lista) {
                if(correspondencia instanceof Carta carta) {
                    saida += "\nCarta ID " + carta.getId();
                } else if (correspondencia instanceof Pacote pacote) {
                    saida += "\nPacote " + pacote.getEmpresa() + " ID " + pacote.getId();
                }
            }
            return saida;
        }
        return null;
    }

    private List<Correspondencia> naoRetiradas(Destinatario destinatario) {
        CorrespondenciaDAO dao = new CorrespondenciaDAO();
        List<Correspondencia> naoRetiradas = dao.pesquisarNaoRetiradas(destinatario);
        return naoRetiradas;
    }

    private Destinatario pesquisaNome(String nome) {
        DestinatarioDAO dao = new DestinatarioDAO();
        Destinatario destinatario = null;
        try {
            destinatario = dao.pesquisarDestinatario(nome);
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return destinatario;
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
