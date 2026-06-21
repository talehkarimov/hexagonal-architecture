package az.taleh.core.application.command;

import java.math.BigDecimal;

public record CreateCarListingCommand(
        String brand,
        String model,
        int year,
        BigDecimal price
) {
}
