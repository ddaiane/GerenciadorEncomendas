package model.dao;

import exceptions.DestinatarioJaCadastradoException;
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
    public void editar(Destinatario newObj) {}

    @Override
    public List<Destinatario> pesquisar() {return null;}

    //pesquisa se destinatario existe cadastrado
    public boolean pesquisarCadastro(Destinatario destinatario) throws DestinatarioJaCadastradoException {
        for(Destinatario cadastro : bancoDeDados.getDestinatarios()) {
            if(cadastro.equals(destinatario)) {
                throw new DestinatarioJaCadastradoException();
            }
    } return true;
    }

    //procura o destinatário no banco de dados pelo nome
    public Destinatario pesquisarDestinatario(String nome) throws DestinatarioInexistenteException {
        for(Destinatario destinatario : bancoDeDados.getDestinatarios()) {
            if(destinatario.getNome().equalsIgnoreCase(nome)) {
                return destinatario;
            }
        }
        throw new DestinatarioInexistenteException();
    }


    //procura o destinatário no banco de dados pelo numero do apartamento
    public Destinatario pesquisarPorNumero(String numero) throws DestinatarioInexistenteException {
        for(Destinatario destinatario : bancoDeDados.getDestinatarios()) {
            if(destinatario.getNumeroImovel().equalsIgnoreCase(numero)) {
                return destinatario;
            }
        }
        throw new DestinatarioInexistenteException();
    }
}
