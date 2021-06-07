package matteo.springframework.sfgpetclinic.controllers;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.model.PetType;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.PetService;
import matteo.springframework.sfgpetclinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    private static final String VIEW_PET_CREATE_OR_UPDATE_FROM = "/pets/createOrUpdatePetForm";

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return this.ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreatePetForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.addPet(pet);
        model.asMap().put("pet", pet);
        return VIEW_PET_CREATE_OR_UPDATE_FROM;
    }

    @PostMapping("/pets/new")
    public String processCreatePetForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        validatePetForm(owner, pet, result);
        owner.addPet(pet);

        if (!result.hasErrors()) {
            this.petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }

        model.put("pet", pet);
        return VIEW_PET_CREATE_OR_UPDATE_FROM;
    }

    private void validatePetForm(Owner owner, @Valid Pet pet, BindingResult result) {
        if (isEmpty(pet) && pet.isNew() && hasPet(owner, pet)) {
            result.rejectValue("name", "duplicate", "already exists");
        }
    }

    private boolean isEmpty(@Valid Pet pet) {
        return StringUtils.hasLength(pet.getName());
    }

    private boolean hasPet(Owner owner, @Valid Pet pet) {
        return owner.getPet(pet.getName(), true) != null;
    }
}
