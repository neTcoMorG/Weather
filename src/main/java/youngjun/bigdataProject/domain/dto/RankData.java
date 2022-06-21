package youngjun.bigdataProject.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RankData<T> {
    private List<String> region = new ArrayList<>();
    private List<T> value = new ArrayList<>();

    public RankData(List<String> region, List<T> value) {
        this.region = region;
        this.value = value;
    }
}
