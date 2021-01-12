package co.edu.udea.tdt.component.trip.io.web.v1;

import co.edu.udea.tdt.component.trip.io.web.v1.model.TripListResponse;
import co.edu.udea.tdt.component.trip.io.web.v1.model.TripQuerySearchRequest;
import co.edu.udea.tdt.component.trip.io.web.v1.model.TripSaveRequest;
import co.edu.udea.tdt.component.trip.io.web.v1.model.TripSaveResponse;
import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.TripService;
import co.edu.udea.tdt.component.trip.service.model.TripQuerySearchCmd;
import co.edu.udea.tdt.component.trip.service.model.TripSaveCmd;
import co.edu.udea.tdt.component.shared.model.ErrorMessage;
import co.edu.udea.tdt.component.shared.model.ResponsePagination;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/trips", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TripController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TripService tripService;

    @PostMapping
    @ApiOperation(value = "Create a trip", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody TripSaveRequest tripToCreate){
        logger.debug("Begin create: tripToCreate = {}", tripToCreate);

        TripSaveCmd tripToCreateCmd = TripSaveRequest.toModel(tripToCreate);

        Trip tripCreated = tripService.create(tripToCreateCmd);

        URI location = fromUriString("/api/v1/trips").path("/{id}")
                .buildAndExpand(tripCreated.getId()).toUri();

        logger.debug("End create: tripCreated = {}", tripCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a trip by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = TripSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    public ResponseEntity<TripSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        Trip tripFound = tripService.findById(id);

        logger.debug("End findById: tripFound = {}", tripFound);
        return ResponseEntity.ok(TripSaveResponse.fromModel(tripFound));
    }

    @GetMapping
    @ApiOperation(value = "Find trips by parameters.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = TripListResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponsePagination<TripListResponse> findByParameters(@Valid @NotNull TripQuerySearchRequest queryCriteria,
                                                                 @PageableDefault(page = 0, size = 15,
                                                                   direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        TripQuerySearchCmd queryCriteriaCmd = TripQuerySearchRequest.toModel(queryCriteria);

        Page<Trip> tripsFound = tripService.findByParameters(queryCriteriaCmd, pageable);

        List<TripListResponse> tripsFoundList = tripsFound.stream().map(TripListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: tripsFound = {}", tripsFound);
        return ResponsePagination.fromObject(tripsFoundList, tripsFound.getTotalElements(), tripsFound.getNumber(),
                tripsFoundList.size());
    }

}
