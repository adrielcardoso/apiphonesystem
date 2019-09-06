package phonesystem.adrielcardoso.com.br.phonesystem.unit.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;
import phonesystem.adrielcardoso.com.br.phonesystem.repository.CustomerRepository;
import phonesystem.adrielcardoso.com.br.phonesystem.service.CustomerService;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test my CustomService")
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTests{

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void whenFindAll_thenReturnCustomerListWithError(){

        /**
         * find with force, without mock
         */
        Page<CustomerEntity> list = this.customerService.findAll(0,10);

        /**
         * return is equals null
         */
        assertThat(list).isEqualTo(null);
    }

    @Test
    public void whenFindAll_thenReturnCustomerListTotalPage(){

        /**
         * create context of the return type, with results
         */
        Page<CustomerEntity> temp = new Page<CustomerEntity>() {
            @Override
            public int getTotalPages() {
                return 10;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super CustomerEntity, ? extends U> function) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List<CustomerEntity> getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator<CustomerEntity> iterator() {
                return null;
            }
        };

        /**
         * define Mock in service to method findall
         */
        Mockito.when(customerService.findAll(0, 10)).thenReturn(temp);


        /**
         * test consumer
         */
        Page<CustomerEntity> list = this.customerService.findAll(0,10);


        /**
         * parse result, to page size 10
         */
        assertThat(list.getTotalPages()).isEqualTo(10);
    }
}
