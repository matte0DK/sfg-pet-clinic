package matteo.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id") // foreign key constraint
    @Column(name = "pet_type")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id") // foreign key constraint
    private Owner owner;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }
}
