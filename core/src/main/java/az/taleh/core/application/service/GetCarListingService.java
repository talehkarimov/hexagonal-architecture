package az.taleh.core.application.service;

import az.taleh.core.application.exception.CarListingNotFoundException;
import az.taleh.core.application.port.in.GetCarListingUseCase;
import az.taleh.core.application.port.out.LoadCarListingPort;
import az.taleh.core.domain.CarListing;

public class GetCarListingService implements GetCarListingUseCase {

    private final LoadCarListingPort loadCarListingPort;

    public GetCarListingService(LoadCarListingPort loadCarListingPort) {
        this.loadCarListingPort = loadCarListingPort;
    }

    @Override
    public CarListing getById(Long id) {
        return loadCarListingPort.loadById(id)
                .orElseThrow(() -> new CarListingNotFoundException(id));
    }
}
