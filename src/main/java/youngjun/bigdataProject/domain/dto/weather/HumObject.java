package youngjun.bigdataProject.domain.dto.weather;

import lombok.Getter;

import java.util.List;

@Getter
public class HumObject {

    public HumObject(List<Integer> humValueList, List<String> humTimeList) {
        this.humValueList = humValueList;
        this.humTimeList = humTimeList;
    }

    private List<Integer> humValueList;
    private List<String> humTimeList;
}
