package model.dao;

import model.Correspondencia;
import model.Destinatario;

import java.util.ArrayList;
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
    public void editar(Correspondencia newObj) {}

    @Override
    public List<Correspondencia> pesquisar() {
        return null;
    }



    public List<Correspondencia> pesquisarNaoRetiradas(Destinatario destinatario) {
        List<Correspondencia> correspondenciasNaoRetiradas = new ArrayList<>();

        for(Correspondencia correspondencia : bancoDeDados.getCorrespondencias()) {
            boolean doDestinatario = correspondencia.getDestino().equals(destinatario);
            if(doDestinatario && !correspondencia.getStatus()) {
                correspondenciasNaoRetiradas.add(correspondencia);
            }
        }
        return correspondenciasNaoRetiradas;
    }

}
