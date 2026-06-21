package az.taleh.webadapters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateCarListingRequest(
        @NotBlank String brand,
        @NotBlank String model,
        @Min(1900) int year,
        @NotNull @Positive BigDecimal price
) {
}
