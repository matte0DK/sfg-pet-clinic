package matteo.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@XmlRootElement
public class Vets {
    private List<Vet> vetList;

    @Builder
    public Vets(List<Vet> vetList) {
        this.vetList = vetList;
    }

    @XmlElement
    public List<Vet> getVetList() {
        if (vetList == null) {
            vetList = new ArrayList<>();
        }
        return vetList;
    }
}
