package matteo.springframework.sfgpetclinic.controllers;

import lombok.AllArgsConstructor;
import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.model.Visit;
import matteo.springframework.sfgpetclinic.service.PetService;
import matteo.springframework.sfgpetclinic.service.VisitService;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor

@Controller
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        webDataBinder.registerCustomEditor(LocalDate.class, new EditorSupport());

    }
    // nested property editor class.
    private static class EditorSupport extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) {
            setValue(LocalDate.parse(text));
        }
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@Valid @PathVariable("petId") Long petId, Map<String, Object> model) {
        Pet pet = this.petService.findById(petId);
        Visit visit = new Visit();

        model.put("pet", pet);
        pet.getVisits().add(visit);
        visit.setPet(pet);

        return visit;
    }

    /* Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called */
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit,
                                      BindingResult result,
                                      @PathVariable("petId") Long petId,
                                      @PathVariable String ownerId) {

        if (!result.hasErrors()) {
            visitService.save(visit);
            return "pets/createOrUpdateVisitForm";
        }
        return "redirect:/owners/" + visit.getPet().getOwner().getId();
    }
}
