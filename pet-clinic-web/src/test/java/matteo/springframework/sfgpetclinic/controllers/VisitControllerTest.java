package matteo.springframework.sfgpetclinic.controllers;

import matteo.springframework.sfgpetclinic.model.Owner;
import matteo.springframework.sfgpetclinic.model.Pet;
import matteo.springframework.sfgpetclinic.model.Visit;
import matteo.springframework.sfgpetclinic.service.PetService;
import matteo.springframework.sfgpetclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({MockitoExtension.class})
class VisitControllerTest {
    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    Pet pet;

    Owner owner;

    Visit visit;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pet = Pet.builder().id(1L).build();

        owner = Owner.builder().id(3L).build();

        visit = Visit.builder().id(2L).build();

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);
        when(pet.addVisit(any(Visit.class))).thenReturn(pet.getVisits());
        /*when(pet.getOwner().getId()).thenReturn(owner.getId());*/


        mockMvc.perform(get("/owners/" + owner.getId() + "/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("visit"));

        verifyNoInteractions(visitService);
    }
}