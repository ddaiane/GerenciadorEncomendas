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
        String quemRegistra = null;
        Destinatario destinatario = null;
        Correspondencia correspondencia = null;
        boolean teste = true;

        do {
            try {
                quemRegistra = leDados("Informe o nome de quem está registrando a retirada ");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " corretamente");
            }
        } while (teste);


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
                String nomeDestinatario = leDados("Informe o nome do destinatario da correspondencia");
                destinatario = pesquisaNome(nomeDestinatario);
                if(destinatario != null) {teste = false;}
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


        List<Correspondencia> aRetirar = naoRetiradas(destinatario);
        correspondencia = selecionaCorrespondencia(aRetirar);
        registraMovimento(correspondencia, quemRetira, quemRegistra);



    }

    private void registraMovimento(Correspondencia correspondencia, String quemRetira, String quemRegistra) {
        Movimento entrega = new Movimento(correspondencia, quemRetira, quemRegistra);
        MovimentoDAO dao = new MovimentoDAO();
        dao.inserir(entrega);
    }


    private Correspondencia selecionaCorrespondencia (List<Correspondencia> aRetirar) {
        int opcao = 0;
        boolean teste = true;
        Correspondencia correspondenciaRetirada = null;

        do {
            try {
                opcao = Integer.parseInt(leDados("Digite a ID da correspondencia que está sendo retirada." +
                        "\nIds de correspondências disponíveis para retirada:" +
                        imprimeNaoRetiradas(aRetirar) + "\nPara sair digite 0."));
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, "Digite um código válido!");
            }

            //retorna para interface principal se 0
            if(opcao==0) { Processador.direcionar("0"); teste = false;}

            //verifica se o código é de um pacote nao retirado do destinatario informado
            for (Correspondencia correspondencia : aRetirar) {
                if (opcao == correspondencia.getId()) {
                    correspondenciaRetirada = correspondencia;
                    teste = false;
                }
            }
            //se o código nao for de um pacote nao retirado do destinatario, informa o erro
            if (teste) {JOptionPane.showMessageDialog(null,
                    "Digite a ID de uma das correspondencias não retiradas do destinatário, por favor");}
        } while (teste);

        return correspondenciaRetirada;
    }

    private List<Correspondencia> naoRetiradas(Destinatario destinatario) {
        CorrespondenciaDAO dao = new CorrespondenciaDAO();
        List<Correspondencia> naoRetiradas = dao.pesquisarNaoRetiradas(destinatario);
        return naoRetiradas;
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
