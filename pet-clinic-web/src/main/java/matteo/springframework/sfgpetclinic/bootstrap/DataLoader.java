package matteo.springframework.sfgpetclinic.bootstrap;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.PetType;
import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.PetTypeService;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        /*PET-TYPES*/
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        /*OWNERS*/
        Owner anotherOwner = new Owner();
        anotherOwner.setFirstName("Nino");
        anotherOwner.setLastName("De Kerpel");

        ownerService.save(anotherOwner);

        Owner owner = new Owner();
        owner.setFirstName("Matteo");
        owner.setLastName("De Kerpel");

        ownerService.save(owner);

        System.out.println("Loaded owners...");

        /*VETS*/
        Vet vet = new Vet();
        vet.setFirstName("Mokuba");
        vet.setLastName("Kaiba");

        vetService.save(vet);

        Vet anotherVet = new Vet();
        anotherVet.setFirstName("Yugi");
        anotherVet.setLastName("Moto");

        vetService.save(anotherVet);

        System.out.println("Loaded vets ...");
    }
}
