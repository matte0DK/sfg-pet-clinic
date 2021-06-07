package matteo.springframework.sfgpetclinic.controllers;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.PetType;
import matteo.springframework.sfgpetclinic.service.OwnerService;
import matteo.springframework.sfgpetclinic.service.PetService;
import matteo.springframework.sfgpetclinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        /*MockitoAnnotations.openMocks(this);*/

        owner = Owner.builder().id(1L).build();

        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Cat").build());
        petTypes.add(PetType.builder().id(2L).name("Dog").build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void testInitCreatePetForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("/pets/createOrUpdatePetForm"));
    }

    @Test
    void testProcessCreateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService).save(any());
    }

    @Test
    void testInitUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("/pets/createOrUpdatePetForm"));
    }

    @Test
    void testProcessUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }
}