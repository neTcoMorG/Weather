package youngjun.bigdataProject.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youngjun.bigdataProject.domain.entity.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName (String name);
}
