package az.taleh.webadapters;

import az.taleh.core.domain.CarListingStatus;

import java.math.BigDecimal;

public record CarListingResponse(
        Long id,
        String brand,
        String model,
        int year,
        BigDecimal price,
        CarListingStatus status
) {
}
