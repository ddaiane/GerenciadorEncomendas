package model.dao;

import model.Destinatario;
import model.Movimento;

import java.util.Date;
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

    public List<Movimento> pesquisar(Destinatario destinatario) {
        // TODO -> implementar
        return null;
    }

    public List<Movimento> pesquisar(Date data) {
        // TODO -> implementar
        return null;
    }


}
