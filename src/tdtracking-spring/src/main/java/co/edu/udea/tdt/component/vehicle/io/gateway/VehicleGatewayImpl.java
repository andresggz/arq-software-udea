package co.edu.udea.tdt.component.vehicle.io.gateway;

import co.edu.udea.tdt.component.vehicle.io.repository.VehicleRepository;
import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.VehicleGateway;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import co.edu.udea.tdt.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class VehicleGatewayImpl implements VehicleGateway {

    private static final String RESOURCE_NOT_FOUND = "Vehicle not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(@NotNull Vehicle vehicleToCreate) {
        logger.debug("Begin save: vehicleToCreate = {}", vehicleToCreate);

        final Vehicle vehicleToBeCreated =
                vehicleToCreate.toBuilder().createDate(LocalDateTime.now())
                        .linkedToRoute(false)
                        .updateDate(LocalDateTime.now())
                        .build();

        final Vehicle vehicleCreated = vehicleRepository.save(vehicleToBeCreated);

        logger.debug("End save: vehicleCreated = {}", vehicleCreated);
        return vehicleCreated;
    }

    @Override
    public Vehicle findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        Vehicle vehicleFound = vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: vehicleFound = {}", vehicleFound);
        return vehicleFound;
    }

    @Override
    public Page<Vehicle> findByParameters(@NotNull VehicleQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}. pageable = {}", queryCriteria, pageable);

        Specification<Vehicle> specification = buildCriteria(queryCriteria);

        Page<Vehicle> vehiclesFound = vehicleRepository.findAll(specification, pageable);

        logger.debug("End findByParameters: vehiclesFound = {}", vehiclesFound);
        return vehiclesFound;
    }

    @Override
    public Vehicle addTrip(@NotNull Long tripId, @NotNull TripIdVehicle tripIdVehicleInDataBase) {
        logger.debug("Begin addTrip: vehicleId = {}, tripIdVehicleInDataBase = {}", tripId, tripIdVehicleInDataBase);

        Vehicle vehicleToBeUpdated = findById(tripId);

        vehicleToBeUpdated.getTripIds().add(tripIdVehicleInDataBase);

        Vehicle vehicleUpdated = vehicleRepository.save(vehicleToBeUpdated);

        logger.debug("End addTrip: vehicleUpdated = {}", vehicleUpdated);
        return vehicleUpdated;
    }

    @Override
    public Vehicle update(@NotNull Vehicle vehicleToUpdate) {
        logger.debug("Begin update: vehicleToUpdate = {}", vehicleToUpdate);

        final Vehicle vehicleToBeUpdated =
                vehicleToUpdate.toBuilder().updateDate(LocalDateTime.now())
                .build();

        final Vehicle vehicleUpdated = vehicleRepository.save(vehicleToBeUpdated);

        logger.debug("End update: vehicleUpdated = {}", vehicleUpdated);

        return vehicleUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete: id = {}", id);

        findById(id);
        vehicleRepository.deleteById(id);

        logger.debug("End delete: id = {}", id);
    }

    private Specification<Vehicle> buildCriteria(VehicleQuerySearchCmd queryCriteria) {
        logger.debug("Begin buildCriteria: queryCriteria = {}", queryCriteria);

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(nonNull(queryCriteria.getName())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.like(
                                        criteriaBuilder.upper(root.get("name")), "%" + queryCriteria.getName().toUpperCase() + "%")));
            }

            if(nonNull(queryCriteria.getActive())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("active"),  queryCriteria.getActive())));
            }

            if (nonNull(queryCriteria.getIsLinkedToRoute())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("linkedToRoute"), queryCriteria.getIsLinkedToRoute())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
