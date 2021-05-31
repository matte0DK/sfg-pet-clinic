package matteo.springframework.sfgpetclinic.service.map;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.PetService;
import matteo.springframework.sfgpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner == null) {
            return null;
        }
        savePets(owner);

        return super.save(owner);
    }

    private void savePets(Owner owner) {
        if (owner.getPets() != null) {
            owner.getPets().forEach(pet -> savePet(pet));
        }
    }

    private void savePet(Pet pet) {
        try {
            petTypeService.save(pet.getPetType());
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
        }

        if (pet.getId() == null) {
            Pet savedPet =  petService.save(pet);
            pet.setId(savedPet.getId());
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
