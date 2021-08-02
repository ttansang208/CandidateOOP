package daos;

import entities.Candidate;

import java.util.List;

public interface CrudDAO<T, ID> {

    void save(T entity);

    T findById(ID id);

    List<T> findAll(List<ID> ids);

    boolean deleteById(ID id);

    boolean deleteByEmail(String email);

    boolean update(T entity);

    T findByEmail(String email);

}
