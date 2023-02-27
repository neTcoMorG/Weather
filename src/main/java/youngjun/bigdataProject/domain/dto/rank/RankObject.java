package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class RankObject {
    private List<String> name;
    private List<Double> value;

    protected RankObject(List<String> name, List<Double> value) {
        this.name = name;
        this.value = value;
    }
}
