package youngjun.bigdataProject.domain.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import youngjun.bigdataProject.domain.entity.WeatherEntity;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    Optional<WeatherEntity> findByRegion(String region);
}
