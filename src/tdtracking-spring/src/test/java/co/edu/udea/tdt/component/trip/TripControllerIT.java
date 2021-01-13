package co.edu.udea.tdt.component.trip;

import co.edu.udea.tdt.component.trip.io.web.v1.model.TripSaveRequest;
import co.edu.udea.tdt.component.trip.model.TripLevel;
import co.edu.udea.tdt.component.trip.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TripControllerIT {

    @Autowired
    private MockMvc client;

    @Autowired
    private TripService tripService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenCreateATrip_thenReturns201() throws Exception{

        TripSaveRequest tripToCreate = TripSaveRequest.builder()
                .name("Angular").description("This is a description about our Angular trip")
                .level(TripLevel.BEGINNER).releaseDate(LocalDateTime.now().plusDays(5))
                .build();

        var result = client.perform(post("/api/v1/trips")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tripToCreate)));

        result.andExpect(status().isCreated());
    }

    @Test
    void whenCreateATripLackingLevel_thenReturns400() throws Exception{
        TripSaveRequest tripToCreate = TripSaveRequest.builder()
                .name("Angular").description("This is a description about our Angular trip")
                .build();

        var result = client.perform(post("/api/v1/trips")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tripToCreate)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateATripLackingCorrectReleaseDate_thenReturns400() throws Exception{
        TripSaveRequest tripToCreate = TripSaveRequest.builder()
                .name("Angular").description("This is a description about our Angular trip")
                .releaseDate(LocalDateTime.now().minusDays(5))
                .build();

        var result = client.perform(post("/api/v1/trips")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tripToCreate)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void whenCreateATripLackingNameSize_thenReturns400() throws Exception{
        TripSaveRequest tripToCreate = TripSaveRequest.builder()
                .name("A").description("This is a description about our Angular trip")
                .build();

        var result = client.perform(post("/api/v1/trips")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tripToCreate)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    void whenFindATripThatExists_thenReturns200() throws Exception{
        TripSaveRequest tripToCreate = TripSaveRequest.builder()
                .name("Angular").level(TripLevel.INTERMEDIATE).description("Description about Angular trip")
                .releaseDate(LocalDateTime.now().plusDays(5))
                .build();
        client.perform(post("/api/v1/trips")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(tripToCreate)));

        var result = client.perform(get("/api/v1/trips/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isOk());
    }

    @Test
    void whenFindATripThatNotExist_thenReturns404() throws Exception{
        var result = client.perform(get("/api/v1/trips/{id}", 1)
                .contentType("application/json"));

        result.andExpect(status().isNotFound());
    }
}
