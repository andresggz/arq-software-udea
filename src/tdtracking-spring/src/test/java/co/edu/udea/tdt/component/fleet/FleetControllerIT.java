package co.edu.udea.tdt.component.fleet;

import co.edu.udea.tdt.component.fleet.io.web.v1.model.FleetSaveRequest;
import co.edu.udea.tdt.component.fleet.service.FleetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class FleetControllerIT {

    @Autowired
    private MockMvc client;

    @Autowired
    private FleetService fleetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateAFleet_thenReturns201() throws Exception{

        FleetSaveRequest fleetToCreate = FleetSaveRequest.builder()
                .name("Programming and development").description("Fleet about programming")
                .build();

        var result = client.perform(post("/api/v1/fleets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fleetToCreate)));

        result.andExpect(status().isCreated());
    }

    @Test
    void whenCreateAFleetLackingNameSize_thenReturns400() throws Exception{
        FleetSaveRequest fleetToCreate = FleetSaveRequest.builder()
                .name("A").description("This is a description about our fleet")
                .build();

        var result = client.perform(post("/api/v1/fleets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fleetToCreate)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void whenFindAFleetThatExists_thenReturns200() throws Exception{
        FleetSaveRequest fleetToCreate = FleetSaveRequest.builder()
                .name("Design and UX").description("Description about Design and UX fleet")
                .build();
        client.perform(post("/api/v1/fleets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fleetToCreate)));

        var result = client.perform(get("/api/v1/fleets/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isOk());
    }

    @Test
    void whenFindAFleetThatNotExist_thenReturns404() throws Exception{
        var result = client.perform(get("/api/v1/fleets/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isNotFound());
    }
}
