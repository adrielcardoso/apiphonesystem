package phonesystem.adrielcardoso.com.br.phonesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import phonesystem.adrielcardoso.com.br.phonesystem.service.CustomerService;
import phonesystem.adrielcardoso.com.br.phonesystem.utils.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController
{
    private

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    Object findAll(@RequestParam Integer page, @RequestParam Integer size)
    {
        try{
            return new Response("", 200, this.customerService.findAll(page, size));
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }
}