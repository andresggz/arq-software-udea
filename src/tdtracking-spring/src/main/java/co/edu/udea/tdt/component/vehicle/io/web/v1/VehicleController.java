package co.edu.udea.tdt.component.vehicle.io.web.v1;

import co.edu.udea.tdt.component.shared.model.ErrorMessage;
import co.edu.udea.tdt.component.shared.model.ResponsePagination;
import co.edu.udea.tdt.component.vehicle.io.web.v1.model.*;
import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.VehicleService;
import co.edu.udea.tdt.component.vehicle.service.model.TripAddCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleSaveCmd;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VehicleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VehicleService vehicleService;

    @PostMapping
    @ApiOperation(value = "Create a vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody VehicleSaveRequest vehicleToCreate){
        logger.debug("Begin create: vehicleToCreate = {}", vehicleToCreate);

        VehicleSaveCmd vehicleToCreateCmd = VehicleSaveRequest.toModel(vehicleToCreate);

        Vehicle vehicleCreated = vehicleService.create(vehicleToCreateCmd);

        URI location = fromUriString("/api/v1/vehicles").path("/{id}")
                .buildAndExpand(vehicleCreated.getId()).toUri();

        logger.debug("End create: vehicleCreated = {}", vehicleCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a vehicle by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = VehicleSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    public ResponseEntity<VehicleSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        Vehicle vehicleFound = vehicleService.findById(id);

        VehicleSaveResponse vehicleToResponse = VehicleSaveResponse.fromModel(vehicleFound);

        List<String> tripLinksToResponse = vehicleFound.getTripIds()
                .stream()
                .map(tripIdVehicle -> String.format("/api/v1/trips/%s", tripIdVehicle.getId()))
                .collect(Collectors.toList());

        vehicleToResponse.setTrips(tripLinksToResponse);


        logger.debug("End findById: vehicleFound = {}", vehicleFound);
        return ResponseEntity.ok(vehicleToResponse);
    }

    @GetMapping
    @ApiOperation(value = "Find vehicles by parameters.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = VehicleSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponsePagination<VehicleListResponse> findByParameters(@Valid @NotNull VehicleQuerySearchRequest queryCriteria,
                                                                    @PageableDefault(page = 0, size = 15,
                                                                   direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        VehicleQuerySearchCmd queryCriteriaCmd = VehicleQuerySearchRequest.toModel(queryCriteria);

        Page<Vehicle> vehiclesFound = vehicleService.findByParameters(queryCriteriaCmd, pageable);

        List<VehicleListResponse> vehiclesFoundList = vehiclesFound.stream()
                .map(VehicleListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: vehiclesFound = {}", vehiclesFound);
        return ResponsePagination.fromObject(vehiclesFoundList, vehiclesFound.getTotalElements(), vehiclesFound.getNumber(),
                vehiclesFoundList.size());
    }

    @PatchMapping(path = "/{id}/trips")
    @ApiOperation(value = "Add trip to vehicle.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = VehicleSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponseEntity<VehicleSaveResponse> addTrip(@Valid @PathVariable("id") @NotNull Long id,
                                                         @Valid @RequestBody @NotNull TripAddRequest tripToAdd){
        logger.debug("Begin addTrip: id = {}, tripToAdd = {}", id, tripToAdd);

        TripAddCmd tripToAddCmd = TripAddRequest.toModel(tripToAdd);

        Vehicle vehicleUpdated = vehicleService.addTrip(id, tripToAddCmd);

        VehicleSaveResponse vehicleToResponse = VehicleSaveResponse.fromModel(vehicleUpdated);

        List<String> tripLinksToResponse = vehicleUpdated.getTripIds()
                .stream()
                .map(tripIdvehicle -> String.format("/api/v1/trips/%s", tripIdvehicle.getId()))
                .collect(Collectors.toList());

        vehicleToResponse.setTrips(tripLinksToResponse);


        logger.debug("End addTrip: vehicleUpdated = {}", vehicleUpdated);
        return ResponseEntity.ok(vehicleToResponse);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Success."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin delete: id = {}", id);

        vehicleService.deleteById(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload-sheets")
    @ApiOperation(value = "Create vehicles from sheets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<URI>> createFromSheets(@RequestParam("file") @NotNull MultipartFile fleetsToCreateFromSheets){
        logger.debug("Begin createFromSheets: fleetsToCreate");

        List<Vehicle> vehiclesCreated = vehicleService.createFromSheets(fleetsToCreateFromSheets);

        List<URI> vehiclesUrisCreated = vehiclesCreated.stream()
                .map(vehicle -> fromUriString("/api/v1/vehicles").path("/{id}").buildAndExpand(vehicle.getId()).toUri())
                .collect(Collectors.toList());

        logger.debug("End createFromSheets");
        return ResponseEntity.ok(vehiclesUrisCreated);
    }
}
