package co.edu.udea.tdt.component.trip.service;

import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
import co.edu.udea.tdt.component.trip.service.model.TripSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface TripService {

    Trip create(@NotNull TripSaveCmd tripToCreateCmd);

    Trip findById(@NotNull Long id);

    Page<Trip> findByParameters(@NotNull TripQuerySearchCmd queryCriteria, @NotNull Pageable pageable);
}
