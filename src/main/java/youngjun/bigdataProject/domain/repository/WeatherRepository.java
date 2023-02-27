package youngjun.bigdataProject.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.entity.Weather;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    List<Weather> findTop5ByRegionOrderByIdDesc (Region region);
    List<Weather> findTop17ByOrderByIdDesc();
}
