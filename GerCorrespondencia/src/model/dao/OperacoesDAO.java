package model.dao;

import java.sql.SQLException;
import java.util.List;
/*
 * OperacoesDAO.java
 *
 * Created on 16 de Outubro de 2006, 21:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author kborges
 */
public interface OperacoesDAO<T> {
    
    public void inserir (T obj);
    
    public void excluir (T obj);
    
    public void editar (T newObj);

    public List<T> pesquisar();
    
}
