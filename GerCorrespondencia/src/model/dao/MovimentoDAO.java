package model.dao;

import model.Correspondencia;
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
    public void editar(Movimento newObj) {

    }

    @Override
    public List<Movimento> pesquisar() {
        return bancoDeDados.getMovimentos();
    }

    public List<Movimento> pesquisaQuemRetira(String destinatario) {
        List<Movimento> movimentos = new ArrayList<>();
        String destinatarioNormalizado = normalizaTexto(destinatario);

        for(Movimento movimento : bancoDeDados.getMovimentos()) {
            for(Correspondencia correspondencia : bancoDeDados.getCorrespondencias()) {
                String destinatarioNome = correspondencia.getDestino().getNome();
                String nomeNormalizado = normalizaTexto(destinatarioNome);
                if (nomeNormalizado.equals(destinatarioNormalizado)) {
                    movimentos.add(movimento);
                }
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
