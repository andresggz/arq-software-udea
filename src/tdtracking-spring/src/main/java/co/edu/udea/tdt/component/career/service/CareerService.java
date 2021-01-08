package co.edu.udea.tdt.component.career.service;

import co.edu.udea.tdt.component.career.model.Career;
import co.edu.udea.tdt.component.career.service.model.CareerQuerySearchCmd;
import co.edu.udea.tdt.component.career.service.model.CareerSaveCmd;
import co.edu.udea.tdt.component.career.service.model.RoadmapAddCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CareerService {

    Career create(@NotNull CareerSaveCmd roadmapToCreateCmd);

    Career findById(@NotNull Long id);

    Page<Career> findByParameters(@NotNull CareerQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Career addRoadmap(@NotNull Long careerId, @NotNull RoadmapAddCmd roadmapToAddCmd);

    List<Career> createFromSheets(@NotNull MultipartFile careersToCreateFromSheets);

    Career update(@NotNull Long id, @NotNull CareerSaveCmd careerToUpdateCmd);

    void deleteById(@NotNull Long id);
}
