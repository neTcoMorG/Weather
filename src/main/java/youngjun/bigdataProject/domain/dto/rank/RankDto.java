package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;
import youngjun.bigdataProject.domain.entity.Weather;

import java.util.List;

@Getter
public class RankDto {

    private TempRankObject tempRankObject;
    private WindRankObject windRankObject;

    public RankDto(TempRankObject tempRankObject, WindRankObject windRankObject) {
        this.tempRankObject = tempRankObject;
        this.windRankObject = windRankObject;
    }
}
