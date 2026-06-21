package az.taleh.core.application.port.in;

import az.taleh.core.application.command.CreateCarListingCommand;

public interface CreateCarListingUseCase {
    Long create(CreateCarListingCommand command);
}
