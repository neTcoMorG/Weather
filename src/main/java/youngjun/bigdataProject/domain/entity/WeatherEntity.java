package youngjun.bigdataProject.domain.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class WeatherEntity {

    public WeatherEntity() {}

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
