package phonesystem.adrielcardoso.com.br.phonesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>
{
}

