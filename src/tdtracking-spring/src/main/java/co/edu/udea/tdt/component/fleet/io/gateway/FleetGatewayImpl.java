package co.edu.udea.tdt.component.fleet.io.gateway;

import co.edu.udea.tdt.component.fleet.io.repository.FleetRepository;
import co.edu.udea.tdt.component.fleet.model.Fleet;
import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.FleetGateway;
import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
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
public class FleetGatewayImpl implements FleetGateway {

    private static final String RESOURCE_NOT_FOUND = "Fleet not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FleetRepository fleetRepository;

    @Override
    public Fleet save(@NotNull Fleet fleetToCreate) {
        logger.debug("Begin save: fleetToCreate = {}", fleetToCreate);

        final Fleet fleetToBeCreated =
                fleetToCreate.toBuilder().createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final Fleet fleetCreated = fleetRepository.save(fleetToBeCreated);

        logger.debug("End save: fleetCreated = {}", fleetCreated);
        return fleetCreated;
    }

    @Override
    public Fleet findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        Fleet fleetFound = fleetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: fleetFound = {}", fleetFound);
        return fleetFound;
    }

    @Override
    public Page<Fleet> findByParameters(@NotNull FleetQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}. pageable = {}", queryCriteria, pageable);

        Specification<Fleet> specification = buildCriteria(queryCriteria);

        Page<Fleet> vehiclesFound = fleetRepository.findAll(specification, pageable);

        logger.debug("End findByParameters: vehiclesFound = {}", vehiclesFound);
        return vehiclesFound;
    }

    @Override
    public Fleet addVehicle(@NotNull Long fleetId, @NotNull VehicleIdFleet vehicleIdInDataBase) {
        logger.debug("Begin addVehicle: fleetId = {}, vehicleIdInDataBase = {}", fleetId, vehicleIdInDataBase);

        Fleet fleetToBeUpdated = findById(fleetId);

        fleetToBeUpdated.getVehicleIds().add(vehicleIdInDataBase);

        Fleet fleetUpdated = fleetRepository.save(fleetToBeUpdated);

        logger.debug("End addVehicle: fleetUpdated = {}", fleetUpdated);
        return fleetUpdated;
    }

    @Override
    public Fleet update(@NotNull Fleet fleetToUpdate) {
        logger.debug("Begin update: fleetToUpdate = {}", fleetToUpdate);

        final Fleet fleetToBeUpdated =
                fleetToUpdate.toBuilder().updateDate(LocalDateTime.now()).build();

        final Fleet fleetUpdated = fleetRepository.save(fleetToBeUpdated);

        logger.debug("End update: fleetUpdated = {}", fleetUpdated);
        return fleetUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin deleteById: id = {}", id);

        findById(id);
        fleetRepository.deleteById(id);

        logger.debug("End deleteById");
    }

    private Specification<Fleet> buildCriteria(FleetQuerySearchCmd queryCriteria) {
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

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
