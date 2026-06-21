package az.taleh.webadapters;

import az.taleh.core.application.command.CreateCarListingCommand;
import az.taleh.core.application.port.in.ApproveCarListingUseCase;
import az.taleh.core.application.port.in.CreateCarListingUseCase;
import az.taleh.core.application.port.in.GetCarListingUseCase;
import az.taleh.core.application.port.in.RejectCarListingUseCase;
import az.taleh.core.domain.CarListing;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-listings")
public class CarListingController {

    private final CreateCarListingUseCase createCarListingUseCase;
    private final GetCarListingUseCase getCarListingUseCase;
    private final ApproveCarListingUseCase approveCarListingUseCase;
    private final RejectCarListingUseCase rejectCarListingUseCase;

    public CarListingController(
            CreateCarListingUseCase createCarListingUseCase,
            GetCarListingUseCase getCarListingUseCase,
            ApproveCarListingUseCase approveCarListingUseCase,
            RejectCarListingUseCase rejectCarListingUseCase
    ) {
        this.createCarListingUseCase = createCarListingUseCase;
        this.getCarListingUseCase = getCarListingUseCase;
        this.approveCarListingUseCase = approveCarListingUseCase;
        this.rejectCarListingUseCase = rejectCarListingUseCase;
    }

    @PostMapping
    public ResponseEntity<Long> create(@Valid @RequestBody CreateCarListingRequest request) {
        Long id = createCarListingUseCase.create(
                new CreateCarListingCommand(
                        request.brand(),
                        request.model(),
                        request.year(),
                        request.price()
                )
        );

        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarListingResponse> getById(@PathVariable("id") Long id) {
        CarListing carListing = getCarListingUseCase.getById(id);
        return ResponseEntity.ok(toResponse(carListing));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable("id") Long id) {
        approveCarListingUseCase.approve(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable("id") Long id) {
        rejectCarListingUseCase.reject(id);
        return ResponseEntity.noContent().build();
    }

    private CarListingResponse toResponse(CarListing carListing) {
        return new CarListingResponse(
                carListing.getId(),
                carListing.getBrand(),
                carListing.getModel(),
                carListing.getYear(),
                carListing.getPrice(),
                carListing.getStatus()
        );
    }
}
