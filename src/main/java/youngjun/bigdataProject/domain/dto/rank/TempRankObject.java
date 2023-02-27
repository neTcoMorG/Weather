package youngjun.bigdataProject.domain.dto.rank;

import lombok.Getter;

import java.util.List;

@Getter
public class TempRankObject {
    private List<String> name;
    private List<Double> value;

    public TempRankObject(List<String> name, List<Double> value) {
        this.name = name;
        this.value = value;
    }
}
