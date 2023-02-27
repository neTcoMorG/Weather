package youngjun.bigdataProject.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import youngjun.bigdataProject.domain.entity.mapping.WeatherData;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Weather {

    public Weather (Region region, WeatherData data) {
        this.region = region;
        temp = Double.parseDouble(String.format("%.1f", data.getMain().getTemp() - 273.15));
        temp_min = data.getMain().getTempMin();
        temp_max = data.getMain().getTempMax();
        wind_speed = data.getWind().getSpeed();
        humidity = data.getMain().getHumidity();
        status = data.getWeather().get(0).getDescription();
        main = data.getWeather().get(0).getMain();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name = "REGION") Region region;
    private String status;
    private String main;
    private int humidity;
    private double temp;
    private double temp_min;
    private double temp_max;
    private double wind_speed;

    @Column(updatable = false) @CreationTimestamp private LocalDateTime createdDate;
}
