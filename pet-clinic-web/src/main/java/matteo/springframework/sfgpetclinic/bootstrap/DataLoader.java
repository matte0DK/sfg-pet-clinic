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

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Matteo");
        owner.setLastName("De Kerpel");

        ownerService.save(owner);

        Owner anotherOwner = new Owner();
        owner.setId(2L);
        owner.setFirstName("Nino");
        owner.setLastName("De Kerpel");

        ownerService.save(anotherOwner);

        System.out.println("Loaded owners...");

        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("dokter");
        vet.setLastName("vet");

        vetService.save(vet);

        Vet anotherVet = new Vet();
        vet.setId(1L);
        vet.setFirstName("vet");
        vet.setLastName("dokter");

        vetService.save(anotherVet);

        System.out.println("Loaded vets ...");
    }
}
