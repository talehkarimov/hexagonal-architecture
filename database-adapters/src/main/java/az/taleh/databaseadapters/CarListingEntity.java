package az.taleh.databaseadapters;

import az.taleh.core.domain.CarListingStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@Table(name = "car_listings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CarListingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private String model;

    private int productionYear;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private CarListingStatus status;
}
