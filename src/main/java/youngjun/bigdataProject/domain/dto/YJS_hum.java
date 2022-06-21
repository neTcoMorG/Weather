package youngjun.bigdataProject.domain.dto;

import lombok.Data;

@Data
public class YJS_hum {
    private Long id;
    private int humidity;
    private String region;

    public YJS_hum(Long id, int hum, String region) {
        this.id = id;
        this.humidity = hum;
        this.region = region;
    }
}
