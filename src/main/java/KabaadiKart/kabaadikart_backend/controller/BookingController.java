package KabaadiKart.kabaadikart_backend.controller;

import KabaadiKart.kabaadikart_backend.model.Home;
import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {
    @Autowired
    private HomeRepository homeRepository;

    @PostMapping   // home create booking
    public   Home  createBooking(@RequestBody  Home home){
        return  homeRepository.save(home);
    }
//    @GetMapping   //  get all booking detail in admin pannel
//    public List<Home> getAllBookings(){
//        return homeRepository.findAll();
//    }
//    @GetMapping("/{id}")   // find by id booking request
//    public  Home getBookingByid (@PathVariable String id){
//        return homeRepository.findById(id).orElse(null);
//    }
}
