package youngjun.bigdataProject.domain.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class YJS_temp {

    private Long id;
    private double temp;
    private String region;

    public YJS_temp() {}

    public YJS_temp(Long id, double temp, String region) {
        this.id = id;
        this.temp = temp;
        this.region = region;
    }
}
