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
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    Object findAllByAnything()
    {
        try{
            return new Response("", 200, this.customerService.findAllByAnything());
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    Object findAllByOk()
    {
        try{
            return new Response("", 200, this.customerService.findAllByOk());
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }

    @RequestMapping(value = "/nok", method = RequestMethod.GET)
    Object findAllByNOk()
    {
        try{
            return new Response("", 200, this.customerService.findAllByNOk());
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }

    @RequestMapping(value = "/region/ddi/{ddi}/ok", method = RequestMethod.GET)
    Object findAllByRegionOk(@PathVariable Integer ddi)
    {
        try{
            return new Response("", 200, this.customerService.findAllByRegionOk(ddi));
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }

    @RequestMapping(value = "/region/ddi/{ddi}", method = RequestMethod.GET)
    Object findAllByRegion(@PathVariable Integer ddi)
    {
        try{
            return new Response("", 200, this.customerService.findAllByRegion(ddi));
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }

    @RequestMapping(value = "/region/ddi/{ddi}/nok", method = RequestMethod.GET)
    Object findAllByRegionNOk(@PathVariable Integer ddi)
    {
        try{
            return new Response("", 200, this.customerService.findAllByRegionNOk(ddi));
        }catch(Exception e){
            return new Response(e.getMessage(), 400);
        }
    }
}