package youngjun.bigdataProject.domain.dto;

import lombok.Data;

@Data
public class YJS_wind {
    private Long id;
    private double speed;
    private String region;

    public YJS_wind(Long id, double speed, String region) {
        this.id = id;
        this.speed = speed;
        this.region = region;
    }
}
