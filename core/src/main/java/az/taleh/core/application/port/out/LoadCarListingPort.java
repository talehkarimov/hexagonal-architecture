package az.taleh.core.application.port.out;

import az.taleh.core.domain.CarListing;

import java.util.Optional;

public interface LoadCarListingPort {
    Optional<CarListing> loadById(Long id);
}