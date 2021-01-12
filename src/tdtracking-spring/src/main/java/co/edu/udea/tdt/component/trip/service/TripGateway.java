package co.edu.udea.tdt.component.trip.service;

import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface TripGateway {

    Trip save(@NotNull Trip tripToCreate);

    Trip findById(@NotNull Long id);

    Page<Trip> findByParameters(@NotNull TripQuerySearchCmd queryCriteria, @NotNull Pageable pageable);
}
