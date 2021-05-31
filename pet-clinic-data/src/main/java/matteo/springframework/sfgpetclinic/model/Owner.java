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
    public Owner(String address, String city, String phoneNr, Set<Pet> pets, Long id, String lastName) {
        this.address = address;
        this.city = city;
        this.phoneNr = phoneNr;
        this.pets = pets;
        this.id = id;
        super.setLastName(lastName);
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
}
