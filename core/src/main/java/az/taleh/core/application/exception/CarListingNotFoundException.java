package az.taleh.core.application.exception;

public class CarListingNotFoundException extends RuntimeException {

    public CarListingNotFoundException(Long id) {
        super("Car listing not found. id=" + id);
    }
}
