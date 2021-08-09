package model.dao;

import model.Destinatario;
import exceptions.DestinatarioInexistenteException;

import java.util.List;

public class DestinatarioDAO implements OperacoesDAO<Destinatario> {

    private final BancoDeDados bancoDeDados = BancoDeDados.getInstance();

    @Override
    public void inserir(Destinatario obj) {
       bancoDeDados.getDestinatarios().add(obj);
    }

    @Override
    public void excluir(Destinatario obj) {
        bancoDeDados.getDestinatarios().remove(obj);
    }

    @Override
    public void editar(Destinatario newObj) {

    }

    @Override
    public List<Destinatario> pesquisar() {
        return null;
    }

    public Destinatario pesquisarDestinatario(String numero) throws DestinatarioInexistenteException {
        for(Destinatario destinatario : bancoDeDados.getDestinatarios()) {
            if(destinatario.getNumeroImovel().equals(numero)) {
                return destinatario;
            }
        }
        throw new DestinatarioInexistenteException(numero);
    }

}
