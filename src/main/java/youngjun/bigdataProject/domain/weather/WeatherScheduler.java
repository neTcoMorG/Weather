package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.entity.Weather;
import youngjun.bigdataProject.domain.weather.api.Api;
import youngjun.bigdataProject.domain.weather.api.mapping.WeatherData;
import youngjun.bigdataProject.domain.repository.RegionRepository;
import youngjun.bigdataProject.domain.repository.WeatherRepository;
import youngjun.bigdataProject.web.Init;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherRepository weatherRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public void core() {
        for (String region : Init.REGIONS) {
            WeatherData data = Api.getWeather(region);
            weatherRepository.save(createWeatherEntity(region, data));
        }
    }

    private Weather createWeatherEntity (String region, WeatherData data) {
        Region findRegion = regionRepository.findByName(region).orElseThrow();
        return new Weather(findRegion, data);
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    public void processor() {
        core();
    }
}
