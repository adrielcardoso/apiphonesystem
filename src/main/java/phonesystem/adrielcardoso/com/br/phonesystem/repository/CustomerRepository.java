package phonesystem.adrielcardoso.com.br.phonesystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long>
{
    @Query(
            value = "SELECT * FROM customer u WHERE phone REGEXP '\\(237\\)\\ ?[2368]\\d{7,8}$'",
            nativeQuery = true)
    Page<CustomerEntity> findAll(Pageable pageable);
}

