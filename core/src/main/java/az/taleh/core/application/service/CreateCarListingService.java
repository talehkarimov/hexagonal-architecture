package az.taleh.core.application.service;

import az.taleh.core.application.command.CreateCarListingCommand;
import az.taleh.core.application.port.in.CreateCarListingUseCase;
import az.taleh.core.application.port.out.SaveCarListingPort;
import az.taleh.core.domain.CarListing;

public class CreateCarListingService implements CreateCarListingUseCase {

    private final SaveCarListingPort saveCarListingPort;

    public CreateCarListingService(SaveCarListingPort saveCarListingPort) {
        this.saveCarListingPort = saveCarListingPort;
    }

    @Override
    public Long create(CreateCarListingCommand command) {
        CarListing carListing = CarListing.createNew(
                command.brand(),
                command.model(),
                command.year(),
                command.price()
        );

        return saveCarListingPort.save(carListing).getId();
    }
}
