package matteo.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id") // foreign key constraint
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id") // foreign key constraint
    private Owner owner;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    // whe apply builder pattern here (constructor), so we can given id value to Pet object we build in the test(s).
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDay, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDay = birthDay;
        this.visits = visits;
    }
}
