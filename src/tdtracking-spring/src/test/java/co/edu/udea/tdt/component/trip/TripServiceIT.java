package co.edu.udea.tdt.component.trip;

import co.edu.udea.tdt.component.trip.model.TripLevel;
import co.edu.udea.tdt.component.trip.service.TripGateway;
import co.edu.udea.tdt.component.trip.service.TripService;
import co.edu.udea.tdt.component.trip.service.model.TripSaveCmd;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TripServiceIT {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripGateway tripGateway;

    @Test
    void whenCreateATripLackingReleaseDate_thenActiveIsFalse() throws Exception{
        TripSaveCmd tripToCreate = TripSaveCmd.builder()
                .name("Angular").description("Description about Angular trip")
                .level(TripLevel.ADVANCED).build();

        var tripCreated = tripService.create(tripToCreate);

        assertThat(tripCreated.getActive())
                .isFalse();
    }

    @Test
    void whenCreateATripWithReleaseDate_thenActiveIsTrue() throws Exception{
        TripSaveCmd tripToCreate = TripSaveCmd.builder()
                .name("Angular").description("Description about Angular trip")
                .releaseDate(LocalDateTime.now().plusDays(5))
                .level(TripLevel.ADVANCED).build();

        var tripCreated = tripService.create(tripToCreate);

        assertThat(tripCreated.getActive())
                .isTrue();
    }
}
