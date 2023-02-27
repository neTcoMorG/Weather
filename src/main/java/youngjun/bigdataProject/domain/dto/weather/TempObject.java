package youngjun.bigdataProject.domain.dto.weather;

import lombok.Getter;

import java.util.List;

@Getter
public class TempObject {

    public TempObject(List<Double> tempValueList, List<String> tempTimeList) {
        this.tempTimeList = tempTimeList;
        this.tempValueList = tempValueList;
    }

    private List<Double> tempValueList;
    private List<String> tempTimeList;
}
