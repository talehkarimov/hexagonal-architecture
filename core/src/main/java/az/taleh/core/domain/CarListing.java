package az.taleh.core.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CarListing {

    private final Long id;
    private final String brand;
    private final String model;
    private final int year;
    private final BigDecimal price;
    private CarListingStatus status;

    public CarListing(
            Long id,
            String brand,
            String model,
            int year,
            BigDecimal price,
            CarListingStatus status
    ) {
        validate(brand, model, year, price);

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.status = status == null ? CarListingStatus.PENDING : status;
    }

    public static CarListing createNew(
            String brand,
            String model,
            int year,
            BigDecimal price
    ) {
        return new CarListing(
                null,
                brand,
                model,
                year,
                price,
                CarListingStatus.PENDING
        );
    }

    public void approve() {
        if (status != CarListingStatus.PENDING) {
            throw new InvalidCarListingStateException("Only pending listing can be approved");
        }

        status = CarListingStatus.APPROVED;
    }

    public void reject() {
        if (status != CarListingStatus.PENDING) {
            throw new InvalidCarListingStateException("Only pending listing can be rejected");
        }

        status = CarListingStatus.REJECTED;
    }

    private void validate(
            String brand,
            String model,
            int year,
            BigDecimal price
    ) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand is required");
        }

        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model is required");
        }

        if (year < 1900) {
            throw new IllegalArgumentException("Year is invalid");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
}
