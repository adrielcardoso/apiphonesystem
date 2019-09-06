package phonesystem.adrielcardoso.com.br.phonesystem;

import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import phonesystem.adrielcardoso.com.br.phonesystem.service.CustomerService;

@DisplayName("Config my CustomService")
public class CustomerTestsConfiguration {

    @Bean
    @Primary
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }
}
