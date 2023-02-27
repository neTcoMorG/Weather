package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;

import java.util.List;

@Getter
public class WindRankObject extends RankObject {
    public WindRankObject(List<String> name, List<Double> value) {
        super(name, value);
    }
}
