package matteo.springframework.sfgpetclinic.service.map;

import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.service.CrudService;
import matteo.springframework.sfgpetclinic.service.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet,  Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public boolean delete(Pet object) {
        return super.delete(object);
    }

    @Override
    public boolean deleteById(Long id) {
        return super.deleteById(id);
    }
}
