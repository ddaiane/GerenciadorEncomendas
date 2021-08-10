package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.*;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;


public class InterfaceRegistrarEntrada implements Comando {

    public void executar() {
        String quemRegistra = null;
        String numeroImovel;
        int tipo;
        Destinatario destinatario = null;
        boolean teste = true;


        Object[] opcao = {"Carta", "Pacote"};
        tipo = JOptionPane.showOptionDialog(null, "Selecione o tipo de correspondencia", null, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcao, opcao[0]);


        do {
            try {
                numeroImovel = leDados("Informe o número do imóvel do destinatário");
                destinatario = pesquisaDestinatario(numeroImovel);
                if (destinatario != null) {
                    teste = false;
                }
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


        teste = true;
        do {
            try {
                quemRegistra = leDados("Informe o nome de quem está recebendo essa correspondencia");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }
        } while (teste);


        if (tipo == 0) { //instanciar e criar movimento caso seja carta
            Carta carta = cadastraCarta(destinatario);
            registraCorrespondencia(carta);
            registraMovimento(carta, quemRegistra);

        } else if (tipo == 1) { //instanciar e criar movimento caso pacote
            Pacote pacote = null;
            teste = true;
            do {
                try {
                    pacote = cadastraPacote(destinatario);
                    teste = false;
                } catch (CampoVazioException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
                }
            } while (teste);
            registraCorrespondencia(pacote);
            registraMovimento(pacote, quemRegistra);
        }

        JOptionPane.showMessageDialog(null, "Correspondencia cadastrada com sucesso");
    }


    private Destinatario pesquisaDestinatario(String numero) {
        DestinatarioDAO dao = new DestinatarioDAO();
        Destinatario destinatario = null;
        try {
            destinatario = dao.pesquisarPorNumero(numero);
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return destinatario;
    }


    private void registraMovimento(Correspondencia correspondencia, String quemRegistra) {
            Movimento movimento = new Movimento(correspondencia, quemRegistra);
            MovimentoDAO dao = new MovimentoDAO();
            dao.inserir(movimento);
    }


    private void registraCorrespondencia(Correspondencia correspondencia) {
        CorrespondenciaDAO dao = new CorrespondenciaDAO();
        dao.inserir(correspondencia);
    }

    private Carta cadastraCarta(Destinatario destinatario) {
        boolean recibo = false;
        int opcao = JOptionPane.showConfirmDialog(null,"A carta possui aviso de recebimento?","Cadastra carta",JOptionPane.YES_NO_OPTION);
        if(opcao == 0) {recibo = true;}
        return new Carta(destinatario, recibo);
    }


    private Pacote cadastraPacote(Destinatario destinatario) throws CampoVazioException {
        String empresa = leDados("Digite o nome da empresa que enviou o pacote");
        return new Pacote(destinatario, empresa);
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