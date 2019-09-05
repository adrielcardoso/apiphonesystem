package phonesystem.adrielcardoso.com.br.phonesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phonesystem.adrielcardoso.com.br.phonesystem.repository.CustomerRepository;
import phonesystem.adrielcardoso.com.br.phonesystem.utils.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController
{
    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    Object findAll()
    {
        try{
            return new Response("", 200, this.customerRepository.findAll());
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }
}