package matteo.springframework.sfgpetclinic.repositories;

import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
