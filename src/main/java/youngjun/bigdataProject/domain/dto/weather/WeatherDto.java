package youngjun.bigdataProject.domain.dto.weather;

import lombok.Getter;
import lombok.ToString;
import youngjun.bigdataProject.domain.dto.weather.HumObject;
import youngjun.bigdataProject.domain.dto.weather.TempObject;
import youngjun.bigdataProject.domain.entity.Weather;

@Getter
@ToString
public class WeatherDto {

    public WeatherDto (Weather recent, TempObject tempObject, HumObject humObject) {
        this.recent = recent;
        this.tempObject = tempObject;
        this.humObject = humObject;
    }

    private Weather recent;
    private TempObject tempObject;
    private HumObject humObject;
}
