package KabaadiKart.kabaadikart_backend.controller;

import KabaadiKart.kabaadikart_backend.model.ContactUs;
import KabaadiKart.kabaadikart_backend.model.Home;
import KabaadiKart.kabaadikart_backend.repository.ContactUsRepository;
import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ContactUsRepository contactUsRepository;
    @Autowired
    private HomeRepository homeRepository;


    @GetMapping("/BookingsDetail")  //  get all booking detail in admin pannel
    public List<Home> getAllBookings(){
        return homeRepository.findAll();
    }
    @GetMapping("/Query")
    public List<ContactUs> getAllMessage(){
        return  contactUsRepository.findAll();
    }
}
