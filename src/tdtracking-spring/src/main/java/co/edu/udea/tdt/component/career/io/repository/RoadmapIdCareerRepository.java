package co.edu.udea.tdt.component.career.io.repository;

import co.edu.udea.tdt.component.career.model.RoadmapIdCareer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapIdCareerRepository extends PagingAndSortingRepository<RoadmapIdCareer, Long> {
}
