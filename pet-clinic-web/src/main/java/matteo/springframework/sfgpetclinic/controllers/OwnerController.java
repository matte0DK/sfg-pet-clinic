package matteo.springframework.sfgpetclinic.controllers;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /*
    INITBINDER:
    Used to customize the request being sent to the controller.
    It is defined in the controller, helps in controlling and formatting each and every request the comes to it.
    This annotation is used with the methods which initializes WebDataBinder and works as a preprocessor for each
    request coming to the controller

    WEBDATABINDER:
    Special DataBinder for data binding from web request parameters to JavaBean objects.
    Designed for web environments, but not dependent on the Servlet API;
    serves as base class for more specific DataBinder variants, such as ServletRequestDataBinder.
    */
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        // allow Get request for /owners to return all records without having to specify the parameters
        if (owner.getLastName() == null) {
            owner.setLastName("");
            // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findByLastNameLike("%" + owner.getLastName() + "%"); // adding wildcard values
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "not found", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple found
            model.addAttribute("selections", results);
            return "owners/ownerList";
         }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }
}
