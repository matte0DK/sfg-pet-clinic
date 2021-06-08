package matteo.springframework.sfgpetclinic.controllers;

import lombok.AllArgsConstructor;
import matteo.springframework.sfgpetclinic.model.Vet;
import matteo.springframework.sfgpetclinic.model.Vets;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@AllArgsConstructor

@Controller
public class VetController {

    private final VetService vetService;

    @RequestMapping({"/vets" ,"/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @GetMapping("/vets.html")
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vetService.findAll());
        model.put("vets", vets);
        return "vets/vetList";
    }


    @GetMapping({ "/vets" })
    public @ResponseBody Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet
        // objects so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vetService.findAll());
        return vets;
    }
}
