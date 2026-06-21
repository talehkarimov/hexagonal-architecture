package az.taleh.core.application.port.out;

import az.taleh.core.domain.CarListing;

public interface SaveCarListingPort {
    CarListing save(CarListing carListing);
}
