package matteo.springframework.sfgpetclinic.repositories;

import matteo.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastname);

    List<Owner> findByLastNameLike(String lastName);
}
