package matteo.springframework.sfgpetclinic.bootstrap;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner anotherOwner = new Owner();
        anotherOwner.setFirstName("Nino");
        anotherOwner.setLastName("De Kerpel");

        ownerService.save(anotherOwner);

        Owner owner = new Owner();
        owner.setFirstName("Matteo");
        owner.setLastName("De Kerpel");

        ownerService.save(owner);

        System.out.println("Loaded owners...");

        Vet vet = new Vet();
        vet.setFirstName("Jeronimo");
        vet.setLastName("Stilton");

        vetService.save(vet);

        Vet anotherVet = new Vet();
        anotherVet.setFirstName("vet");
        anotherVet.setLastName("doctor");

        vetService.save(anotherVet);

        System.out.println("Loaded vets ...");
    }
}
