package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.model.TripAddCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleAddedToFleetCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleSaveCmd;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VehicleEventPublisher vehicleEventPublisher;

    private final VehicleGateway vehicleGateway;

    private final TripIdVehicleService tripIdService;

    @Override
    public Vehicle create(@NotNull VehicleSaveCmd vehicleToCreateCmd) {
        logger.debug("Begin create: vehicleToCreateCmd");

        Vehicle vehicleToCreate = VehicleSaveCmd.toModel(vehicleToCreateCmd);

        activateOrNot(vehicleToCreate);

        Vehicle vehicleCreated = vehicleGateway.save(vehicleToCreate);

        vehicleEventPublisher.publishVehicleCreated(vehicleCreated);

        logger.debug("End create: vehicleCreated = {}", vehicleCreated);
        return vehicleCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        Vehicle vehicleFound = vehicleGateway.findById(id);

        logger.debug("End findById: vehicleFound = {}", vehicleFound);
        return vehicleFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vehicle> findByParameters(@NotNull VehicleQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Page<Vehicle> vehiclesFound = vehicleGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: vehiclesFound = {}", vehiclesFound);
        return vehiclesFound;
    }

    @Override
    public Vehicle addTrip(@NotNull Long vehicleId, @NotNull TripAddCmd tripToAddCmd) {
        logger.debug("Begin addTrip: vehicleId = {}, tripToAddCmd = {}", vehicleId, tripToAddCmd);

        final Long tripIdToAdd = tripToAddCmd.getTripId();

        TripIdVehicle tripIdVehicleInDataBase = tripIdService.findById(tripIdToAdd);

        Vehicle vehicleUpdated = vehicleGateway.addTrip(vehicleId, tripIdVehicleInDataBase);

        logger.debug("End addTrip: vehicleUpdated = {}", vehicleUpdated);
        return vehicleUpdated;
    }

    @Override
    public Vehicle update(@NotNull VehicleAddedToFleetCmd vehicleAddedToFleetCmd) {
        logger.debug("Begin update: vehicleAddedToFleetCmd = {}", vehicleAddedToFleetCmd);

        Long vehicleIdAddedToFleet = vehicleAddedToFleetCmd.getId();

        Vehicle vehicleInDataBase = findById(vehicleIdAddedToFleet);

        Vehicle vehicleToUpdate = vehicleInDataBase.toBuilder()
                .linkedToRoute(true)
                .build();

        Vehicle vehicleUpdated = vehicleGateway.update(vehicleToUpdate);

        logger.debug("End update: vehicleUpdated = {}", vehicleUpdated);

        return vehicleUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin delete: id = {}", id);

        vehicleGateway.deleteById(id);

        logger.debug("End delete");
    }

    @Override
    public List<Vehicle> createFromSheets(@NotNull MultipartFile vehiclesToCreateFromSheets) {
        logger.debug("Begin createFromSheets");
        try {
            List<Vehicle> vehiclesToCreate =
                    extractVehiclesFromSheets(new BufferedInputStream(vehiclesToCreateFromSheets.getInputStream()));

            List<Vehicle> vehiclesCreated = vehiclesToCreate.stream()
                    .map(this::activateOrNot)
                    .map(vehicleGateway::save)
                    .collect(Collectors.toList());

            vehiclesCreated
                    .forEach(vehicleEventPublisher::publishVehicleCreated);

            logger.debug("End createFromSheets");

            return vehiclesCreated;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Vehicle> extractVehiclesFromSheets(InputStream inputStreamVehicles) {
        logger.debug("Begin extractVehiclesFromSheets");

        List<Vehicle> vehiclesToCreate = new ArrayList<>();

        try {
            extractFromWorkbook(inputStreamVehicles, vehiclesToCreate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("End extractVehiclesFromSheets");
        return vehiclesToCreate;

    }

    private void extractFromWorkbook(InputStream inputStreamVehicles, List<Vehicle> vehiclesToCreate) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStreamVehicles);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Sheet> sheets = workbook.sheetIterator();

        while (sheets.hasNext()) {
            Sheet sh = sheets.next();
            Iterator<Row> iteratorRow = sh.iterator();
            iteratorRow.next();
            while (iteratorRow.hasNext()) {
                Row row = iteratorRow.next();

                vehiclesToCreate.add(
                        Vehicle.builder()
                                .name(dataFormatter.formatCellValue(row.getCell(0)))
                                .description(dataFormatter.formatCellValue(row.getCell(1)))
                                .detail(dataFormatter.formatCellValue(row.getCell(2)))
                                .build());
            }
        }

        workbook.close();
    }

    private Vehicle activateOrNot(@NotNull Vehicle vehicleToCreate) {
        vehicleToCreate.setActive(true);
        return vehicleToCreate;
    }

}
