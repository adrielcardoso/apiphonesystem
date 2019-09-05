package phonesystem.adrielcardoso.com.br.phonesystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;
import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long>
{
    List<CustomerEntity> findAllByName(Pageable pageable);
}

