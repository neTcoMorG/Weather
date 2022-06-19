package youngjun.bigdataProject.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class WeatherEntity {

    public WeatherEntity() {}
    public WeatherEntity(String region, WeatherData data) {
        this.region = region;
        temp = Double.parseDouble(String.format("%.1f", data.getMain().getTemp() - 273.15));
        temp_min = data.getMain().getTempMin();
        temp_max = data.getMain().getTempMax();
        wind_speed = data.getWind().getSpeed();
        humidity = data.getMain().getHumidity();
        status = data.getWeather().get(0).getDescription();
        main = data.getWeather().get(0).getMain();
    }

    @Id @GeneratedValue
    private Long id;

    private String region;
    private String status;
    private String main;
    private int humidity;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double wind_speed;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdDate;
}
