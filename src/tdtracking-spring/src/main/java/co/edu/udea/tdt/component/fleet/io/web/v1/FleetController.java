package co.edu.udea.tdt.component.fleet.io.web.v1;

import co.edu.udea.tdt.component.fleet.io.web.v1.model.*;
import co.edu.udea.tdt.component.fleet.model.Fleet;
import co.edu.udea.tdt.component.fleet.service.FleetService;
import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
import co.edu.udea.tdt.component.fleet.service.model.FleetSaveCmd;
import co.edu.udea.tdt.component.fleet.service.model.VehicleAddCmd;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/fleets", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FleetController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FleetService fleetService;

    @PostMapping
    @ApiOperation(value = "Create a fleet", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody FleetSaveRequest fleetToCreate){
        logger.debug("Begin create: fleetToCreate = {}", fleetToCreate);

        FleetSaveCmd fleetToCreateCmd = FleetSaveRequest.toModel(fleetToCreate);

        Fleet fleetCreated = fleetService.create(fleetToCreateCmd);

        URI location = fromUriString("/api/v1/fleets").path("/{id}")
                .buildAndExpand(fleetCreated.getId()).toUri();

        logger.debug("End create: fleetCreated = {}", fleetCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a fleet by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = FleetSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    public ResponseEntity<FleetSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        Fleet fleetFound = fleetService.findById(id);

        FleetSaveResponse fleetToResponse = FleetSaveResponse.fromModel(fleetFound);

        List<String> vehicleLinksToResponse = fleetFound.getVehicleIds()
                .stream()
                .map( vehicleIdFleet -> String.format("/api/v1/vehicles/%s", vehicleIdFleet.getId()))
                .collect(Collectors.toList());

        fleetToResponse.setVehicles(vehicleLinksToResponse);

        logger.debug("End findById: fleetFound = {}", fleetFound);
        return ResponseEntity.ok(fleetToResponse);
    }

    @GetMapping
    @ApiOperation(value = "Find fleets by parameters.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = FleetListResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponsePagination<FleetListResponse> findByParameters(@Valid @NotNull FleetQuerySearchRequest queryCriteria,
                                                                  @PageableDefault(page = 0, size = 15,
                                                                   direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        FleetQuerySearchCmd queryCriteriaCmd = FleetQuerySearchRequest.toModel(queryCriteria);

        Page<Fleet> fleetsFound = fleetService.findByParameters(queryCriteriaCmd, pageable);

        List<FleetListResponse> fleetsFoundList = fleetsFound.stream()
                .map(FleetListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: fleetsFound = {}", fleetsFound);
        return ResponsePagination.fromObject(fleetsFoundList, fleetsFound.getTotalElements(), fleetsFound.getNumber(),
                fleetsFoundList.size());
    }

    @PatchMapping(path = "/{id}/vehicles")
    @ApiOperation(value = "Add vehicle to fleet.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = FleetSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponseEntity<FleetSaveResponse> addVehicle(@Valid @PathVariable("id") @NotNull Long id,
                                                        @Valid @RequestBody @NotNull VehicleAddRequest vehicleToAdd){
        logger.debug("Begin addVehicle: id = {}, vehicleToAdd = {}", id, vehicleToAdd);

        VehicleAddCmd vehicleToAddCmd = VehicleAddRequest.toModel(vehicleToAdd);

        Fleet fleetUpdated = fleetService.addVehicle(id, vehicleToAddCmd);

        FleetSaveResponse fleetUpdatedToResponse = FleetSaveResponse.fromModel(fleetUpdated);

        List<String> vehicleLinksToResponse = fleetUpdated.getVehicleIds()
                .stream()
                .map( vehicleIdFleet -> String.format("/api/v1/vehicles/%s", vehicleIdFleet.getId()))
                .collect(Collectors.toList());

        fleetUpdatedToResponse.setVehicles(vehicleLinksToResponse);

        logger.debug("End addVehicle: fleetUpdated = {}", fleetUpdated);
        return ResponseEntity.ok(fleetUpdatedToResponse);
    }

    @PostMapping("/upload-sheets")
    @ApiOperation(value = "Create fleets from sheets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> createFromSheets(@RequestParam("file") @NotNull MultipartFile fleetsToCreateFromSheets){
        logger.debug("Begin createFromSheets: fleetsToCreate");

        fleetService.createFromSheets(fleetsToCreateFromSheets);

        logger.debug("End createFromSheets");
       return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update a fleet", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = FleetSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    public ResponseEntity<FleetSaveResponse> update(@Valid @RequestBody @NotNull FleetSaveRequest fleetToUpdate,
                                                    @Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin update: fleetToUpdate = {}, id = {}", fleetToUpdate, id);

        FleetSaveCmd fleetToUpdateCmd = FleetSaveRequest.toModel(fleetToUpdate);

        Fleet fleetUpdated = fleetService.update(id, fleetToUpdateCmd);

        logger.debug("End update: fleetUpdated = {}", fleetUpdated);

        return ResponseEntity.ok(FleetSaveResponse.fromModel(fleetUpdated));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a fleet", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Success."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin delete: id = {}", id);

        fleetService.deleteById(id);

        logger.debug("End delete: id = {}", id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
