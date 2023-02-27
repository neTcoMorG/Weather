package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youngjun.bigdataProject.domain.dto.RankDto;
import youngjun.bigdataProject.domain.dto.WeatherDto;
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

    @Transactional
    public WeatherDto getWeather (String region, byte limit) {
        List<Weather> totalWeather = getTotalWeather(region);

        return new WeatherDto(
                getRecentWeather(totalWeather),
                getTempHistory(totalWeather),
                getHumHistory(totalWeather),
                getRank(limit));
    }

    private Weather getRecentWeather (List<Weather> weatherList) {
        return weatherList.get(0);
    }

    private List<Double> getTempHistory (List<Weather> weatherList) {
        List<Double> result = new ArrayList<>();
        weatherList.forEach(w -> result.add(w.getTemp()));
        return result;
    }

    private List<Integer> getHumHistory (List<Weather> weatherList) {
        List<Integer> result = new ArrayList<>();
        weatherList.forEach(w -> result.add(w.getHumidity()));
        return result;
    }

    private List<Weather> getTotalWeather (String region) {
        return weatherRepository.findTop5ByRegionOrderByIdDesc(toRegionEntity(region));
    }

    private Region toRegionEntity (String region) {
        return regionRepository.findByName(region).orElseThrow();
    }

    private RankDto getRank (byte limit) {
        List<Weather> test = weatherRepository.findTop17ByOrderByIdDesc();

        return new RankDto(
                getTempRank(limit, test),
                getHumRank(limit, test));
    }

    private List<Weather> getTempRank (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getTemp).reversed())
                .collect(Collectors.toList());
        return calc.subList(0, limit);
    }

    private List<Weather> getHumRank (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getHumidity).reversed())
                .collect(Collectors.toList());
        return calc.subList(0, limit);
    }
}
