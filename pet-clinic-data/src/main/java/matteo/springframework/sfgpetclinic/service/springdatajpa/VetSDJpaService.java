package matteo.springframework.sfgpetclinic.service.springdatajpa;

import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.repositories.PetRepository;
import matteo.springframework.sfgpetclinic.repositories.VetRepository;
import matteo.springframework.sfgpetclinic.service.VetService;

import java.util.HashSet;
import java.util.Set;

public class VetSDJpaService implements VetService {
    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vet -> vets.add(vet));
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
