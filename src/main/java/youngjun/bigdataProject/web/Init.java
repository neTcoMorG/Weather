package youngjun.bigdataProject.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import youngjun.bigdataProject.domain.entity.Region;
import youngjun.bigdataProject.domain.entity.Weather;
import youngjun.bigdataProject.domain.repository.MemoryCacheRepository;
import youngjun.bigdataProject.domain.repository.RegionRepository;
import youngjun.bigdataProject.domain.repository.WeatherRepository;
import youngjun.bigdataProject.domain.weather.api.Api;
import youngjun.bigdataProject.domain.weather.api.mapping.WeatherData;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    public static final String[] REGIONS = {"Seoul", "Busan", "Daegu", "Incheon", "Gwangju", "Daejeon",
            "Ulsan", "Gyeonggi-do", "Gangwon-do", "Chungcheongbuk-do", "Chungcheongnam-do", "Jeollabuk-do",
            "Jeollanam-do", "Gyeongsangbuk-do", "Gyeongsangnam-do", "Jeju-do", "Sejong"};
    public static final String[] KOREAN = {"서울", "부산", "대구", "인천", "광주", "대전",
        "울산", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도", "세종"};

    private final RegionRepository regionRepository;
    private final WeatherRepository weatherRepository;
    private final MemoryCacheRepository memoryCacheRepository;

    @PostConstruct
    private void init () throws Exception {
        initRegion();
        initWeather();

        memoryCacheRepository.getCache().forEach((region, data) -> {
            log.info(region + ": " + data);});
    }

    private void initRegion () {
        for (String region : KOREAN) {
            if (!isExistRegion(region))
                regionRepository.save(new Region(region));
        }
    }

    private void initWeather () throws Exception {
        for (int i=0; i<Init.REGIONS.length; i++) {
            WeatherData data = Api.getWeather(REGIONS[i]);
            weatherRepository.save(createWeatherEntity(KOREAN[i], data));
            memoryCacheRepository.updateCache(KOREAN[i], data);
        }
    }

    private Weather createWeatherEntity (String region, WeatherData data) {
        Region findRegion = regionRepository.findByName(region).orElseThrow();
        return new Weather(findRegion, data);
    }

    private boolean isExistRegion (String region) {
        Optional<Region> findRegion = regionRepository.findByName(region);
        if (findRegion.isEmpty()) return false;
        return true;
     }
}
