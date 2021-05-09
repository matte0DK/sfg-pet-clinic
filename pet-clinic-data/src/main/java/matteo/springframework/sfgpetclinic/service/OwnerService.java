package matteo.springframework.sfgpetclinic.service;

import matteo.springframework.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
