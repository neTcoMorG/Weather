package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import youngjun.bigdataProject.domain.dto.rank.TempRankObject;
import youngjun.bigdataProject.domain.dto.rank.WindRankObject;
import youngjun.bigdataProject.domain.dto.weather.HumObject;
import youngjun.bigdataProject.domain.dto.rank.RankDto;
import youngjun.bigdataProject.domain.dto.weather.TempObject;
import youngjun.bigdataProject.domain.dto.weather.WeatherDto;
import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.entity.Weather;
import youngjun.bigdataProject.domain.repository.MemoryCacheRepository;
import youngjun.bigdataProject.domain.repository.RegionRepository;
import youngjun.bigdataProject.domain.repository.WeatherRepository;
import youngjun.bigdataProject.domain.weather.util.TimeUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;
    private final WeatherRepository weatherRepository;
    private final MemoryCacheRepository memoryCacheRepository;

    public WeatherDto getWeather (String region) {
        List<Weather> totalWeather = getTotalWeather(region);

        return new WeatherDto(
                getRecentWeather(totalWeather),
                getTempObject(totalWeather),
                getHumObject(totalWeather));
    }

    private Weather getRecentWeather (List<Weather> weatherList) {
        return weatherList.get(0);
    }

    private List<Weather> getTotalWeather (String region) {
        return weatherRepository.findTop5ByRegionOrderByIdDesc(toRegionEntity(region));
    }

    private Region toRegionEntity (String region) {
        return regionRepository.findByName(region).orElseThrow();
    }

    private TempObject getTempObject (List<Weather> weatherList) {
        List<Double> tempList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();

        weatherList.forEach(w -> {
            tempList.add(w.getTemp());
            timeList.add(TimeUtil.txtDate(Timestamp.valueOf(w.getCreatedDate())));
        });

        return new TempObject(tempList, timeList);
    }

    private HumObject getHumObject (List<Weather> weatherList) {
        List<Integer> humList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        weatherList.forEach(w -> {
            humList.add(w.getHumidity());
            timeList.add(TimeUtil.txtDate(Timestamp.valueOf(w.getCreatedDate())));
        });

        return new HumObject(humList, timeList);
    }
}
