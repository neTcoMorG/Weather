package youngjun.bigdataProject.domain.weather;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import youngjun.bigdataProject.domain.dto.projection.HUMProjection;
import youngjun.bigdataProject.domain.dto.projection.TEMProjection;
import youngjun.bigdataProject.domain.dto.projection.WINDProjection;
import youngjun.bigdataProject.domain.entity.WeatherEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    Optional<WeatherEntity> findByRegion(String region);

    //@Query("SELECT g FROM Game g ORDER BY g.localDate ASC, g.localTime ASC")
    @Query("SELECT w.temp FROM WeatherEntity w where w.region =:region ORDER BY w.createdDate DESC")
    List<Double> findHistoryTemp(@Param("region")String region);

    @Query("SELECT w.humidity FROM WeatherEntity w where w.region =:region ORDER BY w.createdDate DESC")
    List<Integer> findHistoryHumidity(@Param("region")String region);

    @Query("SELECT w FROM WeatherEntity w where w.region =:region ORDER BY w.createdDate ASC")
    List<WeatherEntity> findTest(@Param("region")String region, Pageable pageable);

    @Query(value = "select max(id) as id, max(temp) as temp, region from weather_entity group by region order by temp desc LIMIT 4", nativeQuery = true)
    List<TEMProjection> getTempRank();

    @Query(value = "select max(id) as id, max(humidity) as humidity, region from weather_entity group by region order by humidity desc LIMIT 4", nativeQuery = true)
    List<HUMProjection> getHumRank();

    @Query(value = "select max(id) as id, max(wind_speed) as wind_speed, region from weather_entity group by region order by wind_speed desc LIMIT 4", nativeQuery = true)
    List<WINDProjection> getWindRank();
}
