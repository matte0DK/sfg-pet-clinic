package matteo.springframework.sfgpetclinic.repositories;

import matteo.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findOwnersByLastName(String lastname);
}
