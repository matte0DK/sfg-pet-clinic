package matteo.springframework.sfgpetclinic.controllers;

import lombok.AllArgsConstructor;
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

@AllArgsConstructor

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    private static final String VIEW_PET_CREATE_OR_UPDATE_FROM = "pets/createOrUpdatePetForm";

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


    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        /*dataBinder.setValidator(new PetValidator());*/
    }

    // CREATE NEW PET FORM
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

        /*
        ModelMap:
         * Implementation of {@link java.util.Map} for use when building model data for use
         * with UI tools. Supports chained calls and generation of model attribute names.
         *
         * <p>This class serves as generic model holder for Servlet MVC but is not tied to it.
         * Check out the {@link Model} interface for an interface variant.
        */
    }

    /* private methods for processCreatePetForm()*/
    private void validatePetForm(Owner owner, @Valid Pet pet, BindingResult result) {
        if (isEmpty(pet) && pet.isNew() && hasPet(owner, pet)) {
            result.rejectValue("name", "duplicate", "already exists");
        }
    }

    private boolean isEmpty(@Valid Pet pet) {
        /* Check that the given {@code String -> in this case pet.getName()} is neither {@code null} nor of length 0.
         * <p>Note: this method returns {@code true} for a {@code String} that
         * purely consists of whitespace.
         * @param str the {@code String} to check (may be {@code null})
         * @return {@code true} if the {@code String} is not {@code null} and has length.*/
        return StringUtils.hasLength(pet.getName());
    }

    private boolean hasPet(Owner owner, @Valid Pet pet) {
        return owner.getPet(pet.getName(), true) != null;
    }

    // UPDATING AN EXISTING PET FORM
    @GetMapping("/pets/{petId}/edit")
    public String initUpdatePetForm(@PathVariable("petId") Long petId, ModelMap modelMap) {
        modelMap.put("pet", petService.findById(petId));
        return VIEW_PET_CREATE_OR_UPDATE_FROM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdatePetForm(@Valid Pet pet, Owner owner, BindingResult result, ModelMap modelMap) {
        if (!result.hasErrors()) {
            owner.getPets().add(pet);
            this.petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }

        pet.setOwner(owner);
        modelMap.put("pet", pet);

        return VIEW_PET_CREATE_OR_UPDATE_FROM;
    }
}
