package model.dao;

import model.Correspondencia;

import java.util.List;

public class CorrespondenciaDAO implements OperacoesDAO<Correspondencia> {

    private final BancoDeDados bancoDeDados = BancoDeDados.getInstance();

    @Override
    public void inserir(Correspondencia obj) {
        bancoDeDados.getCorrespondencias().add(obj);
    }

    @Override
    public void excluir(Correspondencia obj) {
        bancoDeDados.getCorrespondencias().remove(obj);
    }

    @Override
    public void editar(Correspondencia newObj) {

    }

    @Override
    public List<Correspondencia> pesquisar() {
        return null;
    }
}
