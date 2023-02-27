package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;

import java.util.List;

@Getter
public class TempRankObject extends RankObject{

    public TempRankObject(List<String> name, List<Double> value) {
        super(name, value);
    }
}
