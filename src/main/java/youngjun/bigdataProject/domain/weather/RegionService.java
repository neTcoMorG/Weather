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
import youngjun.bigdataProject.domain.repository.RegionRepository;
import youngjun.bigdataProject.domain.repository.WeatherRepository;

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

    public WeatherDto getWeather (String region) {
        List<Weather> totalWeather = getTotalWeather(region);

        return new WeatherDto(
                getRecentWeather(totalWeather),
                getTempObject(totalWeather),
                getHumObject(totalWeather));
    }

    public RankDto getRank (byte limit) {
        List<Weather> test = weatherRepository.findTop17ByOrderByIdDesc();

        return new RankDto(
                getTempRankObject(limit, test),
                getWindRankObject(limit, test));
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

    private TempRankObject getTempRankObject (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getTemp).reversed())
                .collect(Collectors.toList())
                .subList(0, limit);

        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        calc.forEach(weather -> {
            names.add(weather.getRegion().getName());
            values.add(weather.getTemp());
        });

        return new TempRankObject(names, values);
    }

    private WindRankObject getWindRankObject (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getWind_speed).reversed())
                .collect(Collectors.toList())
                .subList(0, limit);

        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        calc.forEach(weather -> {
            names.add(weather.getRegion().getName());
            values.add(weather.getTemp());
        });

        return new WindRankObject(names, values);
    }

    private TempObject getTempObject (List<Weather> weatherList) {
        List<Double> tempList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();

        weatherList.forEach(w -> {
            tempList.add(w.getTemp());
            timeList.add(w.getCreatedDate().toString());
        });

        return new TempObject(tempList, timeList);
    }

    private HumObject getHumObject (List<Weather> weatherList) {
        List<Integer> humList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();
        weatherList.forEach(w -> {
            humList.add(w.getHumidity());
            timeList.add(w.getCreatedDate().toString());
        });

        return new HumObject(humList, timeList);
    }
}
