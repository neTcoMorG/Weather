package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import youngjun.bigdataProject.domain.dto.rank.*;
import youngjun.bigdataProject.domain.entity.Weather;
import youngjun.bigdataProject.domain.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankService {

    private final WeatherRepository weatherRepository;

    public RankDto getRank (byte limit) {
        List<Weather> test = weatherRepository.findTop17ByOrderByIdDesc();

        return new RankDto(
                getTempRankObject(limit, test),
                getWindRankObject(limit, test),
                getHumRankObject(limit, test));
    }

    private RankObject getTempRankObject (byte limit, List<Weather> weatherList) {
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

    private RankObject getWindRankObject (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getWind_speed).reversed())
                .collect(Collectors.toList())
                .subList(0, limit);

        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        calc.forEach(weather -> {
            names.add(weather.getRegion().getName());
            values.add(weather.getWind_speed());
        });

        return new WindRankObject(names, values);
    }

    private RankObject getHumRankObject (byte limit, List<Weather> weatherList) {
        List<Weather> calc = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getHumidity).reversed())
                .collect(Collectors.toList())
                .subList(0, limit);

        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        calc.forEach(weather -> {
            names.add(weather.getRegion().getName());
            values.add((double)weather.getHumidity());
        });

        return new HumRankObject(names, values);
    }
}
