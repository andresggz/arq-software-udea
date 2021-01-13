package co.edu.udea.tdt.component.trip.service;

import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
import co.edu.udea.tdt.component.trip.service.model.TripSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TripEventPublisher tripEventPublisher;

    private final TripGateway tripGateway;

    @Override
    public Trip create(@NotNull TripSaveCmd tripToCreateCmd) {
        logger.debug("Begin create: tripToCreateCmd");

        Trip tripToCreate = TripSaveCmd.toModel(tripToCreateCmd);

        activateOrNot(tripToCreate, tripToCreateCmd);

        Trip tripCreated = tripGateway.save(tripToCreate);

        tripEventPublisher.publishTripCreated(tripCreated);

        logger.debug("End create: tripCreated = {}", tripCreated);
        return tripCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Trip findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        Trip tripFound = tripGateway.findById(id);

        logger.debug("End findById: tripFound = {}", tripFound);
        return tripFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Trip> findByParameters(@NotNull TripQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Page<Trip> tripsFound = tripGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: tripsFound = {}", tripsFound);
        return tripsFound;
    }

    private void activateOrNot(@NotNull Trip tripToCreate,
                                     @NotNull TripSaveCmd tripToCreateCmd) {
        tripToCreate.setActive(
                nonNull(tripToCreateCmd.getReleaseDate()));
    }

}
