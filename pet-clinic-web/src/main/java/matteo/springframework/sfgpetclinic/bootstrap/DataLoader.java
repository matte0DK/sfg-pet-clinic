package matteo.springframework.sfgpetclinic.bootstrap;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.model.PetType;
import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.PetTypeService;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        Owner owner1 = new Owner();
        owner1.setFirstName("Nino");
        owner1.setLastName("De Kerpel");
        owner1.setAddress("Affligemstraat");
        owner1.setCity("Affligem");
        owner1.setPhoneNr("0455729374");

        Owner owner2 = new Owner();
        owner2.setFirstName("Matteo");
        owner2.setLastName("De Kerpel");
        owner2.setAddress("Affligemstraat");
        owner2.setCity("Affligem");
        owner2.setPhoneNr("0455729376");

        /*PETS*/
        Pet vito = new Pet();
        vito.setPetType(savedCatPetType);
        vito.setBirthDay(LocalDate.of(2016,1, 17));
        vito.setOwner(owner2);
        vito.setName("Vito");
        owner2.getPets().add(vito);

        Pet nero = new Pet();
        nero.setPetType(savedCatPetType);
        nero.setBirthDay(LocalDate.of(2020, 4, 22));
        nero.setOwner(owner2);
        nero.setName("Nero");
        owner2.getPets().add(nero);

        /*SAVING OWNERS WITH THEIR PETS*/
        ownerService.save(owner1);
        ownerService.save(owner2);
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
