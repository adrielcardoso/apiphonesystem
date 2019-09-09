//package phonesystem.adrielcardoso.com.br.phonesystem.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;
//
//import java.util.List;
//
//@Repository
//public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long>
//{
//    Page<CustomerEntity> findAll(Pageable pageable);
//
//    @Query("select c from CustomerEntity c where c.phone like ?1")
//    Page<CustomerEntity> findAllByDDI(String phone, Pageable pageable);
//
//    @Query(value="SELECT * FROM customer u where phone not REGEXP 212", nativeQuery = true)
//    List<CustomerEntity> findAllByDDIRegex();
//}
//
