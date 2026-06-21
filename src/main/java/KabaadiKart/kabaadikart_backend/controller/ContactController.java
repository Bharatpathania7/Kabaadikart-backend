package KabaadiKart.kabaadikart_backend.controller;

import KabaadiKart.kabaadikart_backend.model.ContactUs;
import KabaadiKart.kabaadikart_backend.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ContactUs")
//@CrossOrigin( origins = "*")
public class ContactController {
    @Autowired
    private ContactUsRepository contactUsRepository;

    @PostMapping
    public ContactUs Message(@RequestBody ContactUs contactUs){
        return contactUsRepository.save(contactUs);
    }
//    @GetMapping
//    public List<ContactUs> getAllMessage(){
//        return  contactUsRepository.findAll();
//    }
//  @GetMapping("/{id}")
//    public  ContactUs getMessageId (@PathVariable String id){
//        return  contactUsRepository.findById(id).orElse(null);
//  }
}
