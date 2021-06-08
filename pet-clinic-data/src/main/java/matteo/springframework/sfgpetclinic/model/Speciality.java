package matteo.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Speciality extends BaseEntity {
    private String description;
    private String name;
}
