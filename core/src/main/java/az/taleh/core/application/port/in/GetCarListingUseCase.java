package az.taleh.core.application.port.in;

import az.taleh.core.domain.CarListing;

public interface GetCarListingUseCase {
    CarListing getById(Long id);
}
