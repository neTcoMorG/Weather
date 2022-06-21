package youngjun.bigdataProject.domain.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import youngjun.bigdataProject.domain.entity.WeatherEntity;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class WeatherScheduler {

    private final WeatherRepository repository;
    private Map<String, API> bigData = new HashMap<>();
    private final String[] regions = {"Seoul", "Busan", "Daegu", "Incheon", "Gwangju", "Daejeon",
            "Ulsan", "Gyeonggi-do", "Gangwon-do", "Chungcheongbuk-do", "Chungcheongnam-do", "Jeollabuk-do",
            "Jeollanam-do", "Gyeongsangbuk-do", "Gyeongsangnam-do", "Jeju-do", "Sejong"
    };

    public void core() {
        for (String region : regions) {
            if (!bigData.containsKey(region)) {
                bigData.put(region, new API(region));
            }
            else {
                bigData.replace(region, new API(region));
            }

            WeatherEntity entity = new WeatherEntity();
            entity.setRegion(region);
            entity.setTemp(bigData.get(region).getWeather().getMain().getTemp());
            entity.setTemp(Double.parseDouble(String.format("%.1f", bigData.get(region).getWeather().getMain().getTemp() - 273.15)));
            entity.setTemp_min(bigData.get(region).getWeather().getMain().getTempMin());
            entity.setTemp_max(bigData.get(region).getWeather().getMain().getTempMax());
            entity.setWind_speed(bigData.get(region).getWeather().getWind().getSpeed());
            entity.setHumidity(bigData.get(region).getWeather().getMain().getHumidity());
            entity.setStatus(bigData.get(region).getWeather().getWeather().get(0).getDescription());
            entity.setMain(bigData.get(region).getWeather().getWeather().get(0).getMain());
            repository.save(entity);
        }
    }

    //@Scheduled(cron = "0 */5 * * * *")
    @Scheduled(cron = "0 * * * * *")
    public void processor() {
        core();
    }
}
