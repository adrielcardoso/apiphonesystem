package phonesystem.adrielcardoso.com.br.phonesystem.unit.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import phonesystem.adrielcardoso.com.br.phonesystem.service.CustomerService;

@DisplayName("Test my CustomService")
@ActiveProfiles("test")
public class CustomerServiceTests{

    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindAll(){
        Mockito.when(
                this.customerService.findAll(0,10).getTotalPages()
        ).thenReturn(10);
    }
}
