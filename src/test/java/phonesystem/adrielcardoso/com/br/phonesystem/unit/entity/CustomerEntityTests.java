package phonesystem.adrielcardoso.com.br.phonesystem.unit.entity;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste my CustomEntity")
public class CustomerEntityTests {

    @Test
    public void givenTestMyCreateObject() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName("Joaquim");
        customerEntity.setPhone(null);
        customerEntity.setId(99);
        assertEquals(null, customerEntity.getPhone());
        assertEquals( "Joaquim", customerEntity.getName());
        assertEquals( 99, customerEntity.getId());
    }
}
