package az.taleh.databaseadapters;

import az.taleh.core.domain.CarListing;

public class CarListingPersistenceMapper {

    public CarListing toDomain(CarListingEntity entity) {
        return new CarListing(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getProductionYear(),
                entity.getPrice(),
                entity.getStatus()
        );
    }

    public CarListingEntity toEntity(CarListing domain) {
        return new CarListingEntity(
                domain.getId(),
                domain.getBrand(),
                domain.getModel(),
                domain.getYear(),
                domain.getPrice(),
                domain.getStatus()
        );
    }
}
