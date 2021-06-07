package matteo.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Builder
    public Owner(String address, String city, String phoneNr, Set<Pet> pets, Long id, String firstName, String lastName) {
        this.address = address;
        this.city = city;
        this.phoneNr = phoneNr;
        this.id = id;
        super.setFirstName(firstName);
        super.setLastName(lastName);

        if (pets != null) {
            this.pets = pets;
        }
    }

    @Column(name = "owner_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_nr")
    private String phoneNr;

    @Column(name = "pets")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    protected Set<Pet> getPetsInternal() {
        if (this.pets == null) {
            this.pets = new HashSet<>();
        }
        return this.pets;
    }

    public void addPet(Pet pet) {
        if (pet.isNew()) {
            getPetsInternal().add(pet);
        }
        pet.setOwner(this);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        String compName;

        name = name.toLowerCase();

        for (Pet pet : getPetsInternal()) {

            if (ignoreNew || pet.isNew()) {
                return null;
            }

            compName = pet.getName().toLowerCase();

            if (compName.equals(name)) {
                return pet;
            }
        }
        return null;
    }
}
