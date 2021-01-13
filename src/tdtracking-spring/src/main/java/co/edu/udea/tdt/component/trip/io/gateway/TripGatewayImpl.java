package co.edu.udea.tdt.component.trip.io.gateway;

import co.edu.udea.tdt.component.trip.io.repository.TripRepository;
import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.TripGateway;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
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
public class TripGatewayImpl implements TripGateway {

    private static final String RESOURCE_NOT_FOUND = "Trip not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TripRepository tripRepository;

    @Override
    public Trip save(@NotNull Trip tripToCreate) {
        logger.debug("Begin save: tripToCreate = {}", tripToCreate);

        final Trip tripToBeCreated =
                tripToCreate.toBuilder().createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final Trip tripCreated = tripRepository.save(tripToBeCreated);

        logger.debug("End save: tripCreated = {}", tripCreated);
        return tripCreated;
    }

    @Override
    public Trip findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        Trip tripFound = tripRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: tripFound = {}", tripFound);
        return tripFound;
    }

    @Override
    public Page<Trip> findByParameters(@NotNull TripQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}. pageable = {}", queryCriteria, pageable);

        Specification<Trip> specification = buildCriteria(queryCriteria);

        Page<Trip> tripsFound = tripRepository.findAll(specification, pageable);

        logger.debug("End findByParameters: tripsFound = {}", tripsFound);
        return tripsFound;
    }

    private Specification<Trip> buildCriteria(TripQuerySearchCmd queryCriteria) {
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

            if(nonNull(queryCriteria.getLevel())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.equal(root.get("level"), queryCriteria.getLevel())));
            }

            if(nonNull(queryCriteria.getReleaseDateGreaterThan())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.greaterThan(root.get("releaseDate"), queryCriteria.getReleaseDateGreaterThan())));
            }

            if(nonNull(queryCriteria.getReleaseDateLessThan())){
                predicates
                        .add(criteriaBuilder.and(
                                criteriaBuilder.lessThan(root.get("releaseDate"), queryCriteria.getReleaseDateLessThan())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };

    }
}
