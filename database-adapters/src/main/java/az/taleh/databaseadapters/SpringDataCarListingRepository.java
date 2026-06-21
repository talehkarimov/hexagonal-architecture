package az.taleh.databaseadapters;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCarListingRepository extends JpaRepository<CarListingEntity, Long> {
}
