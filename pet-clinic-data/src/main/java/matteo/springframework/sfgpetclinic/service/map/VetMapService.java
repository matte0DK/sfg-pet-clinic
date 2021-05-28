package matteo.springframework.sfgpetclinic.service.map;

import matteo.springframework.sfgpetclinic.model.Speciality;
import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.service.SpecialityService;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetMapService extends AbstractMapService<Vet,  Long> implements VetService {
    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        saveSpecialities(vet);
        return super.save(vet);
    }

    private void saveSpecialities(Vet vet) {
        if (vet.getSpecialities().size() > 0) {
            vet.getSpecialities().forEach(speciality -> saveSpeciality(speciality));
        }
    }

    private void saveSpeciality(Speciality speciality) {
        if (speciality.getId() == null) {
            Speciality savedSpeciality = specialityService.save(speciality);
            speciality.setId(savedSpeciality.getId());
        }
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
