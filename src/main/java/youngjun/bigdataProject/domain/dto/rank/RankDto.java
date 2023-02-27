package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;
import youngjun.bigdataProject.domain.entity.Weather;

import java.util.List;

@Getter
public class RankDto {

    private RankObject tempRankObject;
    private RankObject windRankObject;
    private RankObject humRankObject;

    public RankDto(RankObject tempRankObject, RankObject windRankObject, RankObject humRankObject) {
        this.tempRankObject = tempRankObject;
        this.windRankObject = windRankObject;
        this.humRankObject = humRankObject;
    }
}
