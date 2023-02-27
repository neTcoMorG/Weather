package youngjun.bigdataProject.domain.weather;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import java.net.URI;

public class Api {

    private static RestTemplate rest;
    private static URI uri;

    private Api () {}

    public static WeatherData getWeather (String region) {
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
        return rest.getForEntity(uri, WeatherData.class).getBody();
    }
}
