package youngjun.bigdataProject.domain.dto;

import lombok.Getter;
import youngjun.bigdataProject.domain.entity.Weather;

import java.util.List;

@Getter
public class RankDto {

    private List<Weather> tempRank;
    private List<Weather> humRank;

    public RankDto(List<Weather> tempRank, List<Weather> humRank) {
        this.tempRank = tempRank;
        this.humRank = humRank;
    }
}
