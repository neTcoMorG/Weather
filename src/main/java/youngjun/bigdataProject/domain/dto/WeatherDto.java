package youngjun.bigdataProject.domain.dto;

import lombok.Getter;
import lombok.ToString;
import youngjun.bigdataProject.domain.entity.Weather;

import java.util.List;

@Getter
@ToString
public class WeatherDto {

    private WeatherDto() {}
    public WeatherDto(Weather recent, List<Double> tempHistory, List<Integer> humHistory, RankDto rank) {
        this.recent = recent;
        this.tempHistory = tempHistory;
        this.humHistory = humHistory;
        this.rank = rank;
    }

    private Weather recent;
    private List<Double> tempHistory;
    private List<Integer> humHistory;
    private RankDto rank;


}
