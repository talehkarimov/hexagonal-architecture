package az.taleh.core.application.service;

import az.taleh.core.application.exception.CarListingNotFoundException;
import az.taleh.core.application.port.in.ApproveCarListingUseCase;
import az.taleh.core.application.port.out.LoadCarListingPort;
import az.taleh.core.application.port.out.SaveCarListingPort;
import az.taleh.core.domain.CarListing;

public class ApproveCarListingService implements ApproveCarListingUseCase {

    private final LoadCarListingPort loadCarListingPort;
    private final SaveCarListingPort saveCarListingPort;

    public ApproveCarListingService(
            LoadCarListingPort loadCarListingPort,
            SaveCarListingPort saveCarListingPort
    ) {
        this.loadCarListingPort = loadCarListingPort;
        this.saveCarListingPort = saveCarListingPort;
    }

    @Override
    public void approve(Long id) {
        CarListing carListing = loadCarListingPort.loadById(id)
                .orElseThrow(() -> new CarListingNotFoundException(id));

        carListing.approve();

        saveCarListingPort.save(carListing);
    }
}
