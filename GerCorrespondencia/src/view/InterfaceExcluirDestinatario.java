package view;

import controle.Comando;
import exceptions.CampoVazioException;
import exceptions.DestinatarioInexistenteException;
import model.Destinatario;
import model.Movimento;
import model.dao.DestinatarioDAO;
import model.dao.MovimentoDAO;

import javax.swing.*;
import java.util.List;

public class InterfaceExcluirDestinatario implements Comando {

    @Override
    public void executar() {
        String nome = null;
        String numero = null;
        boolean teste = true;
        Destinatario destinatario = null;

        do {
            try {
                nome = leDados("Informe o Nome do destinatário a ser excluido");
                destinatario = pesquisaNome(nome); //testa se o destinatario existe no cadastro
                if (destinatario != null) {teste = false;}
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " novamente");
            }
        } while (teste);

        teste = true;
        do {
            try {
                numero = leDados("Informe o Numero do imóvel do destinatário a ser excluido");
                Destinatario confereDestinatario = pesquisaNumero(numero); //testar se o numero pertence ao nome informado
                if (confereDestinatario.getNumeroImovel().equals(destinatario.getNumeroImovel())) {teste = false;}
                else {JOptionPane.showMessageDialog(null, "O numero do imóvel não pertence ao destinatário informado.");}
            } catch (CampoVazioException | DestinatarioInexistenteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (teste);

        excluiMovimentacoes(destinatario);
        excluiDestinatario(destinatario);
        JOptionPane.showMessageDialog(null, "Destinatario excluído com sucesso!");
    }

    private void excluiMovimentacoes(Destinatario destinatario) {
        MovimentoDAO dao = new MovimentoDAO();
        List<Movimento> lista = dao.pesquisaDestinatario(destinatario.getNome());

        for(Movimento movimento : lista) {
            dao.excluir(movimento);
        }
    }

    private void excluiDestinatario(Destinatario destinatario) {
        DestinatarioDAO dao = new DestinatarioDAO();
        dao.excluir(destinatario);
    }

    public String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem).replaceAll("\\s{2,}", " ").trim();
        if (opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
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

    private Destinatario pesquisaNumero(String numero) throws DestinatarioInexistenteException {
        DestinatarioDAO dao = new DestinatarioDAO();
        Destinatario destinatario = null;
        destinatario = dao.pesquisarPorNumero(numero);
        return destinatario;
    }
}
