package youngjun.bigdataProject.domain.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import youngjun.bigdataProject.domain.dto.*;
import youngjun.bigdataProject.domain.entity.WeatherEntity;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository repository;

    private final Pageable topSix  = PageRequest.of(0, 6);

    public WeatherEntity getWeather(String region) {
        Optional<WeatherEntity> find = repository.findByRegion(region);
        return find.orElse(null);
    }

    public WeatherEntity requestWeather(String region) {
        API api = new API(region);
        return new WeatherEntity(region, api.getWeather());
    }

    public HistoryData<Double> getHistoryTemp(String region) {
        List<Double> value = new ArrayList<>();
        List<String> time = new ArrayList<>();

        for (WeatherEntity w : repository.findTest(region, topSix)) {
            value.add(w.getTemp());
            time.add(String.valueOf(w.getCreatedDate().getDayOfMonth() + "일"));
        }
        return new HistoryData<Double>(value, time);
    }

    public HistoryData<Integer> getHistoryHumidity(String region) {
        List<Integer> value = new ArrayList<>();
        List<String> time = new ArrayList<>();

        for (WeatherEntity w : repository.findTest(region, topSix)) {
            value.add(w.getHumidity());
            time.add(w.getCreatedDate().getDayOfMonth() + "일");
        }
        return new HistoryData<Integer>(value, time);
    }

    public RankData<Double> getTempRank() {
        List<String> region = new ArrayList<>();
        List<Double> value = new ArrayList<>();

        for (WeatherEntity w : repository.getTempRank()) {
            region.add(w.getRegion());
            value.add(w.getTemp());
        }

        return new RankData<Double>(region, value);
    }

    public RankData<Integer> getHumRank() {
        List<String> region = new ArrayList<>();
        List<Integer> value = new ArrayList<>();

        for (WeatherEntity w : repository.getHumRank()) {
            region.add(w.getRegion());
            value.add(w.getHumidity());
        }

        return new RankData<Integer>(region, value);
    }

    public RankData<Double> getWindRank() {
        List<String> region = new ArrayList<>();
        List<Double> value = new ArrayList<>();

        for (WeatherEntity w : repository.getWindRank()) {
            region.add(w.getRegion());
            value.add(w.getWind_speed());
        }

        return new RankData<Double>(region, value);
    }
}
