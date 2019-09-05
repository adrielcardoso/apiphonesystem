package phonesystem.adrielcardoso.com.br.phonesystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import phonesystem.adrielcardoso.com.br.phonesystem.entity.CustomerEntity;
import phonesystem.adrielcardoso.com.br.phonesystem.repository.CustomerRepository;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    public Page<CustomerEntity> findAll(Integer page, Integer size)
    {
        Pageable firstPageWithTwoElements = PageRequest.of(page, size);

        return customerRepository.findAll(firstPageWithTwoElements);
    }
}