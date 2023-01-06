package com.ibrahim.drone;


import com.ibrahim.drone.Controllers.DroneController;
import com.ibrahim.drone.Entities.Drone;
import com.ibrahim.drone.Entities.Medication;
import com.ibrahim.drone.Repositories.DroneRepository;
import com.ibrahim.drone.Repositories.MedicationRepository;
import com.ibrahim.drone.Services.DroneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroneApplication.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DroneService.class, DroneController.class,DroneRepository.class})
public class MedicationTest {
    @MockBean
    @Autowired
    private DroneRepository droneRepository;

    @MockBean
    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private WebApplicationContext context;


    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetLoadedMedication() throws Exception {

        Drone dummyDrone = new Drone();
        dummyDrone.setId(1L);
        // Create a new drone object
        Drone drone = new Drone();
        drone.setSerialNumber("123456");
        drone.setModel(Drone.Model.LIGHTWEIGHT);
        drone.setWeightLimit(500);
        drone.setBatteryCapacity(100);
        drone.setState(Drone.State.IDLE);



        Medication medication = new Medication();
        medication.setId(1L);
        medication.setName("paracetamal");
        medication.setCode("para123");
        medication.setImage("imageString");

        Medication medication2 = new Medication();
        medication.setId(1L);
        medication.setName("drug");
        medication.setCode("para123");
        medication.setImage("imageString");
        List<Medication> medicationList = new ArrayList<>();
        medicationList.add(medication);
        medicationList.add(medication2);
        drone.setLoadedMedications(medicationList);
        when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medication/drone/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}

