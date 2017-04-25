package ua.server.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T,ID extends Serializable> {

    private JpaRepository<T,ID> repository;

    public AbstractService(JpaRepository<T,ID> repository) {
        this.repository=repository;

    }

    public T save(T entity) {
        T result=repository.save(entity);
        return result;
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public T findOne(ID entityId) {
        T result=repository.findOne(entityId);
        return result;
    }

    public List<T> findAll() {
        List<T> result= repository.findAll();
        return result;
    }


}
