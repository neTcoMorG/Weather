package youngjun.bigdataProject.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class HistoryData<T> {
    private List<T> value;
    private List<String> time;

    public HistoryData(List<T> value, List<String> time) {
        this.value = value;
        this.time = time;
    }
}
