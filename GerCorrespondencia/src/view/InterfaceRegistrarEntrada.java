package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import exceptions.TipoNaoIdentificadoException;
import model.*;
import model.dao.DestinatarioDAO;

import java.util.Objects;

public class InterfaceRegistrarEntrada implements Comando {

    public void executar() {
        String quemRegistra = null;
        String numeroImovel = null;
        String tipo = null;
        String sair = null;
        Destinatario destinatario = null;
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
                destinatario = pesquisaDestinatario(numeroImovel);
                if(destinatario != null) {teste = false;}
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



        if(tipo.equals("1")) { //instanciar e criar movimento caso seja carta
            Carta carta = null;
            try {
                carta = cadastraCarta(destinatario);
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            }
            Movimento registraMovimento = new Movimento(carta, quemRegistra);
         //todo envia movimento para o dao???

        } else if (tipo.equals("2")){
            //todo perguntar empresa
            try {
                Pacote pacote = cadastraPacote(destinatario);
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            }
            // Movimento registraMovimento = (pacote, quemRegistra);
            //todo envia movimento para o dao???
        }


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

    }

    private Carta cadastraCarta(Destinatario destinatario) throws CampoVazioException {
        String ar = leDados("A carta possuía registro de recebimento? \nDigite 0 para não e 1 para sim");
        int i = Integer.parseInt(ar);
        boolean recibo = false;
        if(i == 1) {recibo = true;}
        Carta carta = new Carta(destinatario, recibo);
        return carta;
    }

    private Pacote cadastraPacote(Destinatario destinatario) throws CampoVazioException {
        String empresa = leDados("Digite o nome da empresa que enviou o pacote");
        Pacote pacote = new Pacote(destinatario, empresa);
        return pacote;
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.length() == 0) {
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

}
