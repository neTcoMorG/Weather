package youngjun.bigdataProject.domain.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import java.net.URI;

public class API {

    private RestTemplate rest;
    private String region;
    private URI uri;

    public API (String region) {
        this.region = region;
        uri = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org")
                .path("/data/2.5/weather")
                .queryParam("q", region + ",kr")
                .queryParam("appid", "0bca0313b02e0b99e35433afd9f4721b")
                .queryParam("lang", "kr")
                .encode()
                .build()
                .toUri();
        rest = new RestTemplate();
    }

    public WeatherData getWeather() {
        ResponseEntity<WeatherData> result = rest.getForEntity(uri, WeatherData.class);
        return result.getBody();
    }
}
