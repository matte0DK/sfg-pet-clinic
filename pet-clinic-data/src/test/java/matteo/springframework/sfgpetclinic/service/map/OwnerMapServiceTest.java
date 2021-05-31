package matteo.springframework.sfgpetclinic.service.map;

import matteo.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {
    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String ownerLastName = "Matteo";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().build()).setLastName(ownerLastName);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(1, owner.getId());
    }

    @Test
    void saveById() {
        Long id = 2L;

        Owner ownerToPersist = Owner.builder().build();
        Owner ownerPersisted = ownerMapService.save(ownerToPersist);

        assertEquals(id, ownerPersisted.getId());
    }

    @Test
    void saveWithoutId() {
        Owner ownerPersisted = ownerMapService.save(Owner.builder().build());

        assertNotNull(ownerPersisted);
        assertNotNull(ownerPersisted.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner matteo = ownerMapService.findByLastName(ownerLastName);

        assertNotNull(matteo);
        assertEquals(ownerId, matteo.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner matteo = ownerMapService.findByLastName("foo");

        assertNull(matteo);
    }
}