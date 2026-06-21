package az.taleh.bootstrap.config;

import az.taleh.core.application.port.out.LoadCarListingPort;
import az.taleh.core.application.port.out.SaveCarListingPort;
import az.taleh.core.application.service.ApproveCarListingService;
import az.taleh.core.application.service.CreateCarListingService;
import az.taleh.core.application.service.GetCarListingService;
import az.taleh.core.application.service.RejectCarListingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCarListingService createCarListingService(
            SaveCarListingPort saveCarListingPort
    ) {
        return new CreateCarListingService(saveCarListingPort);
    }

    @Bean
    public GetCarListingService getCarListingService(
            LoadCarListingPort loadCarListingPort
    ) {
        return new GetCarListingService(loadCarListingPort);
    }

    @Bean
    public ApproveCarListingService approveCarListingService(
            LoadCarListingPort loadCarListingPort,
            SaveCarListingPort saveCarListingPort
    ) {
        return new ApproveCarListingService(loadCarListingPort, saveCarListingPort);
    }

    @Bean
    public RejectCarListingService rejectCarListingService(
            LoadCarListingPort loadCarListingPort,
            SaveCarListingPort saveCarListingPort
    ) {
        return new RejectCarListingService(loadCarListingPort, saveCarListingPort);
    }
}
