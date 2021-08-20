package model.dao;

import model.Correspondencia;
import model.Destinatario;
import model.Movimento;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovimentoDAO implements OperacoesDAO<Movimento> {

    private final BancoDeDados bancoDeDados = BancoDeDados.getInstance();

    @Override
    public void inserir(Movimento obj) {
        bancoDeDados.getMovimentos().add(obj);
    }

    @Override
    public void excluir(Movimento obj) {
        bancoDeDados.getMovimentos().remove(obj);
    }

    @Override
    public void editar(Movimento newObj) {    }

    @Override
    public List<Movimento> pesquisar() {
        return bancoDeDados.getMovimentos();
    }


    //pesquisa e retorna uma lista com todas movimentaçoes de um determinado morador
    public List<Movimento> pesquisaDestinatario(Destinatario destinatario) {
        List<Movimento> movimentos = new ArrayList<>();
        if (destinatario == null) {
            return movimentos;
        }
        for(Movimento movimento : bancoDeDados.getMovimentos()) {
            Destinatario destinatarioMovimento = movimento.getCorrespondencia().getDestino();
            if (destinatarioMovimento.equals(destinatario)) {
                movimentos.add(movimento);
            }
        }
        return movimentos;
    }


    private String normalizaTexto(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .replaceAll("\\s{2,}", " ")
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    //pesquisa as movimentacoes por data
    public List<Movimento> pesquisaData(String data) {
        List<Movimento> movimentos = new ArrayList<>();
        for(Movimento movimento : bancoDeDados.getMovimentos()) {
            if (movimento.getDataFormatada().equals(data)) {
                movimentos.add(movimento);
            }
        }
        return movimentos;
    }

}
