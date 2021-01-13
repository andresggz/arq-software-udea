package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.Fleet;
import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
import co.edu.udea.tdt.component.fleet.service.model.FleetSaveCmd;
import co.edu.udea.tdt.component.fleet.service.model.VehicleAddCmd;
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
public class FleetServiceImpl implements FleetService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FleetGateway fleetGateway;

    private final VehicleIdFleetService vehicleIdFleetService;

    private final FleetEventPublisher fleetEventPublisher;


    @Override
    public Fleet create(@NotNull FleetSaveCmd fleetToCreateCmd) {
        logger.debug("Begin create: fleetToCreateCmd");

        Fleet fleetToCreate = FleetSaveCmd.toModel(fleetToCreateCmd);

        activateOrNot(fleetToCreate);

        Fleet fleetCreated = fleetGateway.save(fleetToCreate);

        logger.debug("End create: fleetCreated = {}", fleetCreated);
        return fleetCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Fleet findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        Fleet fleetFound = fleetGateway.findById(id);

        logger.debug("End findById: fleetFound = {}", fleetFound);
        return fleetFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Fleet> findByParameters(@NotNull FleetQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Page<Fleet> fleetsFound = fleetGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: fleetsFound = {}", fleetsFound);
        return fleetsFound;
    }

    @Override
    public Fleet addVehicle(@NotNull Long fleetId, @NotNull VehicleAddCmd vehicleToAddCmd) {
        logger.debug("Begin addVehicle: fleetId = {}, vehicleToAddCmd = {}", fleetId, vehicleToAddCmd);

        final Long vehicleIdToAdd = vehicleToAddCmd.getVehicleId();

        VehicleIdFleet vehicleIdInDataBase = vehicleIdFleetService.findById(vehicleIdToAdd);

        Fleet fleetUpdated = fleetGateway.addVehicle(fleetId, vehicleIdInDataBase);

        fleetEventPublisher.publishVehicleAddedToFleet(vehicleIdInDataBase);

        logger.debug("End addVehicle: fleetUpdated = {}", fleetUpdated);
        return fleetUpdated;
    }

    @Override
    public List<Fleet> createFromSheets(@NotNull MultipartFile fleetsToCreateFromSheets) {
        logger.debug("Begin createFromSheets");
        try {
            List<Fleet> fleetsToCreate =
                    extractFleetsFromSheets(new BufferedInputStream(fleetsToCreateFromSheets.getInputStream()));

            List<Fleet> fleetsCreated = fleetsToCreate.stream()
                    .map(fleetGateway::save)
                    .collect(Collectors.toList());

            logger.debug("End createFromSheets");

            return fleetsCreated;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Fleet update(@NotNull Long id, @NotNull FleetSaveCmd fleetToUpdateCmd) {
        logger.debug("Begin update: id = {}, fleetToUpdateCmd = {}", id, fleetToUpdateCmd);

        Fleet fleetInDataBase = findById(id);

        Fleet fleetToUpdate = fleetInDataBase.toBuilder().name(fleetToUpdateCmd.getName()).description(fleetToUpdateCmd.getDescription())
                .build();

        Fleet fleetUpdated = fleetGateway.update(fleetToUpdate);

        logger.debug("End update: fleetUpdated = {}", fleetUpdated);
        return fleetUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin deleteById id = {}", id);

        fleetGateway.deleteById(id);

        logger.debug("End deleteById");
    }

    private void activateOrNot(@NotNull Fleet fleetToCreate) {
        fleetToCreate.setActive(true);
    }

    private List<Fleet> extractFleetsFromSheets(@NotNull InputStream inputStreamFleets) {
        logger.debug("Begin extractFleetsFromSheets");

        List<Fleet> fleetsToCreate = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(inputStreamFleets);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Sheet> sheets = workbook.sheetIterator();

            while (sheets.hasNext()) {
                Sheet sh = sheets.next();
                Iterator<Row> iteratorRow = sh.iterator();
                iteratorRow.next();
                while (iteratorRow.hasNext()) {
                    Row row = iteratorRow.next();

                    fleetsToCreate.add(
                            Fleet.builder()
                                    .name(dataFormatter.formatCellValue(row.getCell(0)))
                                    .description(dataFormatter.formatCellValue(row.getCell(1)))
                                    .build());
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("End extractFleetsFromSheets");
        return fleetsToCreate;
    }

}
