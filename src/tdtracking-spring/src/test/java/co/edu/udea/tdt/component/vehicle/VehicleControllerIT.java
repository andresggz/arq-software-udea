package co.edu.udea.tdt.component.vehicle;

import co.edu.udea.tdt.component.vehicle.io.web.v1.model.VehicleSaveRequest;
import co.edu.udea.tdt.component.vehicle.service.VehicleService;
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
public class VehicleControllerIT {

    @Autowired
    private MockMvc client;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateAVehicle_thenReturns201() throws Exception{

        VehicleSaveRequest vehicleToCreate = VehicleSaveRequest.builder()
                .name("Back-end con Java").description("This is a description about our back-end with java vehicle")
                .licensePlate("This is our detail")
                .build();

        var result = client.perform(post("/api/v1/vehicles")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vehicleToCreate)));

        result.andExpect(status().isCreated());
    }

    @Test
    void whenCreateAVehicleLackingNameSize_thenReturns400() throws Exception{
        VehicleSaveRequest vehicleToCreate = VehicleSaveRequest.builder()
                .name("A").description("This is a description about our front-end vehicle")
                .licensePlate("This is our vehicle")
                .build();

        var result = client.perform(post("/api/v1/vehicles")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vehicleToCreate)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void whenFindAVehicleThatExists_thenReturns200() throws Exception{
        VehicleSaveRequest vehicleToCreate = VehicleSaveRequest.builder()
                .name("Front-end con React").licensePlate("This is a detail about").description("Description about Angular")
                .build();
        client.perform(post("/api/v1/vehicles")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(vehicleToCreate)));

        var result = client.perform(get("/api/v1/vehicles/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isOk());
    }

    @Test
    void whenFindAVehicleThatNotExist_thenReturns404() throws Exception{
        var result = client.perform(get("/api/v1/vehicles/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isNotFound());
    }
}
