package matteo.springframework.sfgpetclinic.service.map;

import matteo.springframework.sfgpetclinic.model.Visit;
import matteo.springframework.sfgpetclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        validateVisit(visit);
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    private void validateVisit(Visit visit) {
        if (visit.getPet() == null) {
            throw new RuntimeException("Pet is Not valid");
        }
        if (visit.getPet().getOwner() == null) {
            throw new RuntimeException("Owner is Not valid");
        }
        if (visit.getPet().getId() == null) {
            throw new RuntimeException("Pet ID is Not valid");
        }
        if (visit.getPet().getOwner().getId() == null) {
            throw new RuntimeException("Owner ID is Not valid");
        }
    }
}
