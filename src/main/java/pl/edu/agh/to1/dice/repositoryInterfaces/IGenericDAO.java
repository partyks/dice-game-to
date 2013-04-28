package pl.edu.agh.to1.dice.repositoryInterfaces;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Michal Partyka
 */
public interface IGenericDAO<T> {
    @Transactional
    T update(T toUpdate);

    @Transactional
    List<T> getList();

    @Transactional
    void add(T add);

    @Transactional
    void remove(T remove);

    @Transactional
    T getByPK(Object PK);

    @Transactional
    void removeByPK(Object PK);
}
