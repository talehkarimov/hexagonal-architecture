package az.taleh.core.application.service;

import az.taleh.core.application.exception.CarListingNotFoundException;
import az.taleh.core.application.port.in.RejectCarListingUseCase;
import az.taleh.core.application.port.out.LoadCarListingPort;
import az.taleh.core.application.port.out.SaveCarListingPort;
import az.taleh.core.domain.CarListing;

public class RejectCarListingService implements RejectCarListingUseCase {

    private final LoadCarListingPort loadCarListingPort;
    private final SaveCarListingPort saveCarListingPort;

    public RejectCarListingService(
            LoadCarListingPort loadCarListingPort,
            SaveCarListingPort saveCarListingPort
    ) {
        this.loadCarListingPort = loadCarListingPort;
        this.saveCarListingPort = saveCarListingPort;
    }

    @Override
    public void reject(Long id) {
        CarListing carListing = loadCarListingPort.loadById(id)
                .orElseThrow(() -> new CarListingNotFoundException(id));

        carListing.reject();

        saveCarListingPort.save(carListing);
    }
}
