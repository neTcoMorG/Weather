package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.entity.Weather;
import youngjun.bigdataProject.domain.repository.MemoryCacheRepository;
import youngjun.bigdataProject.domain.weather.api.Api;
import youngjun.bigdataProject.domain.weather.api.mapping.WeatherData;
import youngjun.bigdataProject.domain.repository.RegionRepository;
import youngjun.bigdataProject.domain.repository.WeatherRepository;
import youngjun.bigdataProject.web.Init;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherRepository weatherRepository;
    private final RegionRepository regionRepository;
    private final MemoryCacheRepository memoryCacheRepository;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void processor() throws Exception {
        collect();
    }

    private void collect () throws Exception {
        for (int i=0; i<Init.REGIONS.length; i++) {
            WeatherData data = Api.getWeather(Init.REGIONS[i]);
            weatherRepository.save(createWeatherEntity(Init.KOREAN[i], data));
            memoryCacheRepository.updateCache(Init.KOREAN[i], data);
        }
    }

    private Weather createWeatherEntity (String region, WeatherData data) {
        Region findRegion = regionRepository.findByName(region).orElseThrow();
        return new Weather(findRegion, data);
    }
}
