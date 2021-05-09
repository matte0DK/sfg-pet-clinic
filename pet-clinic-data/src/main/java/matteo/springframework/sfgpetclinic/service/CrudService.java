package matteo.springframework.sfgpetclinic.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    boolean delete(T object);

    boolean deleteById(ID id);
}
