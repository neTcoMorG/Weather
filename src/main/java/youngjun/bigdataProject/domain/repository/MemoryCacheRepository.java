package youngjun.bigdataProject.domain.repository;

import lombok.Getter;
import org.springframework.stereotype.Component;
import youngjun.bigdataProject.domain.weather.api.mapping.WeatherData;
import youngjun.bigdataProject.web.Init;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Component
public class MemoryCacheRepository {

    private final Map<String, WeatherData> cache = new ConcurrentHashMap<>();

    @PostConstruct
    private void init () {
        WeatherData mock = new WeatherData();
        for (String region : Init.KOREAN)
            cache.put(region, mock); // MOCK
    }

    public void updateCache (String region, WeatherData weather) throws Exception {
        if (!cache.containsKey(region))
            throw new NullPointerException();
        cache.replace(region, weather);
    }

}
