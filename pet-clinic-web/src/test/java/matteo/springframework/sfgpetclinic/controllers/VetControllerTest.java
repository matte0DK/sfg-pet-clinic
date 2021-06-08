package matteo.springframework.sfgpetclinic.controllers;

import matteo.springframework.sfgpetclinic.model.Vets;
import matteo.springframework.sfgpetclinic.service.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;

    VetController vetController;

    MockMvc mockMvc;

    Vets vets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vetController = new VetController(vetService);

        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();

        vets = Vets.builder().vetList(new ArrayList<>(2)).build();
    }

    @Test
    void testFindVets() throws Exception {

    }

    @Test
    void showVetList() {
    }

    @Test
    void showResourcesVetList() {
    }
}