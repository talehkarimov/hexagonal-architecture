package az.taleh.databaseadapters;

import az.taleh.core.application.port.out.LoadCarListingPort;
import az.taleh.core.application.port.out.SaveCarListingPort;
import az.taleh.core.domain.CarListing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarListingPersistenceAdapter implements LoadCarListingPort, SaveCarListingPort {

    private final SpringDataCarListingRepository repository;
    private final CarListingPersistenceMapper mapper = new CarListingPersistenceMapper();

    public CarListingPersistenceAdapter(SpringDataCarListingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CarListing> loadById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public CarListing save(CarListing carListing) {
        CarListingEntity entity = mapper.toEntity(carListing);
        CarListingEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}