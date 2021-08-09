package model.dao;

import model.Movimento;

import java.util.List;

public class MovimentoDAO implements OperacoesDAO<Movimento> {

    private final BancoDeDados bancoDeDados = BancoDeDados.getInstance();

    @Override
    public void inserir(Movimento obj) {

    }

    @Override
    public void excluir(Movimento obj) {

    }

    @Override
    public void editar(Movimento newObj) {

    }

    @Override
    public List<Movimento> pesquisar() {
        return bancoDeDados.getMovimentos();
    }
}
