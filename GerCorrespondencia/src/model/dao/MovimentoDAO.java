package model.dao;

import model.Destinatario;
import model.Movimento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Movimento> pesquisar(Destinatario destinatario) {
        // TODO -> implementar
        return null;
    }

    public List<Movimento> pesquisar(String data) {
        List<Movimento> movimentos = new ArrayList<>();
        for(Movimento movimento : bancoDeDados.getMovimentos()) {
            if (movimento.getDataFormatada().equals(data)) {
                movimentos.add(movimento);
            }
        }

        return movimentos;
    }


}
