package model.dao;

import java.sql.SQLException;
import java.util.List;


public interface OperacoesDAO<T> {
    
    public void inserir (T obj);
    
    public void excluir (T obj);
    
    public void editar (T newObj);

    public List<T> pesquisar();
    
}
