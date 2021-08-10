package view;

import controle.Comando;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.Carta;
import model.Correspondencia;
import model.Destinatario;
import model.Pacote;
import model.dao.CorrespondenciaDAO;
import model.dao.DestinatarioDAO;

import javax.swing.*;
import java.util.List;

public class InterfacePesquisarCorrespondencia implements Comando{

    @Override
    public void executar() {
        String nome;
        boolean teste = true;
        Destinatario destinatario = null;

        do {
            try {
                nome = leDados("Informe o nome completo do destinatário a ser pesquisado");
                destinatario = pesquisaDestinatario(nome);
                if (destinatario != null) {teste = false;}
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);


        informaCorrespondenciasNaoRetiradas(destinatario);
    }

    private void informaCorrespondenciasNaoRetiradas(Destinatario destinatario) {
        CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();

        if(destinatario != null) {
            List<Correspondencia> naoRetiradas = correspondenciaDAO.pesquisarNaoRetiradas(destinatario);
            JOptionPane.showMessageDialog(null, pegaStringCorrespondencias(naoRetiradas));
        }
    }

    private String pegaStringCorrespondencias(List<Correspondencia> naoRetiradas) {
        StringBuilder correspondencias = new StringBuilder();

        if(naoRetiradas.isEmpty()) {
            correspondencias.append("Esse destinatario nao possui correspondencia a ser retirada.");
            return correspondencias.toString();
        }

        for(Correspondencia correspondencia : naoRetiradas) {

            if(correspondencia instanceof Carta carta) {
                correspondencias.append(carta).append("\n");
            } else if (correspondencia instanceof Pacote pacote) {
                correspondencias.append(pacote).append("\n");
            }

        }
        return correspondencias.toString();
    }

    private Destinatario pesquisaDestinatario(String nome) {
        DestinatarioDAO dao = new DestinatarioDAO();
        try {
            return dao.pesquisarDestinatario(nome);
        } catch (DestinatarioInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
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
