/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author ARCRODINPC-02
 */
public interface ICrudDao<T> {
    
    void create(T t) throws Exception;

    void update(T t) throws Exception;

    void delete(T t) throws Exception;

    T findForId(Object t) throws Exception;

    List<T> readAll() throws Exception;
    
}
