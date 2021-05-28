package matteo.springframework.sfgpetclinic.repositories;

import matteo.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<Pet, Long> {
}
