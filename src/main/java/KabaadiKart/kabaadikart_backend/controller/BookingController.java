//package KabaadiKart.kabaadikart_backend.controller;
//
//import KabaadiKart.kabaadikart_backend.model.Home;
//import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
//import KabaadiKart.kabaadikart_backend.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/bookings")
////@CrossOrigin(origins = "*")
//public class BookingController {
//    @Autowired
//    private HomeRepository homeRepository;
//    @Autowired
//    private EmailService emailService;
//
//    // 🔹 Step 1: Create booking with name & mobile
//    @PostMapping("/step1")
//    public  Home createStep1(@RequestBody Home request) {
//        Home booking = new Home();
//        booking.setName(request.getName());
//        booking.setMobile(request.getMobile());
//        booking.setStatus("Draft");// Not yet confirmed
//        booking.setCreatedAt(LocalDateTime.now());
//        booking.setUpdatedAt(LocalDateTime.now());
//        return homeRepository.save(booking);
//    }
//
//    // 🔹 Step 2: Update booking with address & scrap details
//    @PutMapping("/step2/{id}")
//    public Home updateStep2(@PathVariable String id, @RequestBody Home request) {
//        Optional<Home> optionalBooking = homeRepository.findById(id);
//        if (optionalBooking.isEmpty()) throw new RuntimeException("Booking not found");
//
//        Home booking = optionalBooking.get();
//        booking.setAddress(request.getAddress());
//        booking.setLandmark(request.getLandmark());
//        booking.setCity(request.getCity());
//        booking.setPincode(request.getPincode());
//       booking.setScrapType(request.getScrapType());
//      booking.setQuantity(request.getQuantity());
//       booking.setPhotoUrl(request.getPhotoUrl());
//        booking.setNotes(request.getNotes());
//        booking.setUpdatedAt(LocalDateTime.now());
//        return homeRepository.save(booking);
//    }
////    @PutMapping("/step3/{id}")
////    public Home updateStep3(@PathVariable String id, @RequestBody Home request) {
////        Optional<Home> optionalBooking = homeRepository.findById(id);
////        if (optionalBooking.isEmpty()) throw new RuntimeException("Booking not found");
////        Home booking = optionalBooking.get();
////        booking.setScrapType(request.getScrapType());
////        booking.setQuantity(request.getQuantity());
////        booking.setPhotoUrl(request.getPhotoUrl());
////        booking.setNotes(request.getNotes());
////        booking.setUpdatedAt(LocalDateTime.now());
////        return homeRepository.save(booking);
////    }
//    // 🔹 Step 3: Final confirmation with date + slot, generate bookingId
////    @PutMapping("/step4/{id}")
////    public Home confirmBooking(@PathVariable String id, @RequestBody Home request) {
////        Optional<Home> optionalBooking = homeRepository.findById(id);
////        if (optionalBooking.isEmpty()) throw new RuntimeException("Booking not found");
////
////        Home booking = optionalBooking.get();
////        booking.setPickupDate(request.getPickupDate());
////        booking.setPickupSlot(request.getPickupSlot());
////
////        // Generate Booking ID if not exists
////        if (booking.getBookingId() == null) {
////            String bookingId = "BK" + System.currentTimeMillis();
////            booking.setBookingId(bookingId);
////        }
////
////        booking.setStatus("Pending Confirmation");
////        booking.setUpdatedAt(LocalDateTime.now());
////        return homeRepository.save(booking);
////
////    }
//@PutMapping("/step4/{id}")
//public Home confirmBooking(@PathVariable String id, @RequestBody Home request) {
//    Optional<Home> optionalBooking = homeRepository.findById(id);
//    if (optionalBooking.isEmpty()) throw new RuntimeException("Booking not found");
//
//    Home booking = optionalBooking.get();
//    booking.setPickupDate(request.getPickupDate());
//    booking.setPickupSlot(request.getPickupSlot());
//
//    // Generate Booking ID if not exists
//    if (booking.getBookingId() == null) {
//        String bookingId = "BK" + System.currentTimeMillis();
//        booking.setBookingId(bookingId);
//    }
//
//    booking.setStatus("Pending Confirmation");
//    booking.setUpdatedAt(LocalDateTime.now());
//
//    Home savedBooking = homeRepository.save(booking);
//
//    // 📧 Trigger email to your team
//    try {
//        emailService.sendBookingToTeam(savedBooking);
//    } catch (Exception e) {
//        System.err.println("Failed to send booking email: " + e.getMessage());
//    }
//
//    return savedBooking;
//}
//
//    @GetMapping("/all")
//    public List<Home> getAllBookings() {
//        return homeRepository.findAll();
//    }
//    @GetMapping("/mobile")
//    public List<Home> searchByMobile(@RequestParam String mobile) {
//
//        return homeRepository.findByMobile(mobile);
//    }
//
//
//}
package KabaadiKart.kabaadikart_backend.controller;

import KabaadiKart.kabaadikart_backend.model.Home;
import KabaadiKart.kabaadikart_backend.model.ScrapItem;
import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
import KabaadiKart.kabaadikart_backend.repository.ScrapItemRepository;
import KabaadiKart.kabaadikart_backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ScrapItemRepository scrapItemRepository;

    // 🔹 Step 1: Create booking
    @PostMapping("/step1")
    public Home createStep1(@RequestBody Home request) {
        Home booking = new Home();
        booking.setName(request.getName());
        booking.setMobile(request.getMobile());
        booking.setEmail(request.getEmail());
        booking.setStatus("Draft");
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setCreatedAt(LocalDateTime.now());
        booking.setStepStatus("Step1 Completed"); // Track progress
        return homeRepository.save(booking);
    }

    // 🔹 Step 2: Update booking with address & scrap
    @PutMapping("/step2/{id}")
    public Home updateStep2(@PathVariable String id, @RequestBody Home request) {
        Home booking = homeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setAddress(request.getAddress());
        booking.setLandmark(request.getLandmark());
        booking.setCity(request.getCity());
        booking.setPincode(request.getPincode());
        booking.setScrapType(request.getScrapType());
        booking.setQuantity(request.getQuantity());
        booking.setPhotoUrl(request.getPhotoUrl());
        booking.setNotes(request.getNotes());
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setStepStatus("Step2 Completed");

        return homeRepository.save(booking);
    }

    // 🔹 Step 4: Final confirmation (pickup + bookingId)
    @PutMapping("/step4/{id}")
    public Home confirmBooking(@PathVariable String id, @RequestBody Home request) {
        Home booking = homeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setPickupDate(request.getPickupDate());
        booking.setPickupSlot(request.getPickupSlot());

        if (booking.getBookingId() == null) {
            booking.setBookingId("BK" + System.currentTimeMillis());
        }

        booking.setStatus("Pending Confirmation");
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setStepStatus("Step4 Completed");

        Home savedBooking = homeRepository.save(booking);

        // Send email
        try {
            emailService.sendBookingToTeam(savedBooking);
        } catch (Exception e) {
            System.err.println("Failed to send booking email: " + e.getMessage());
        }

        return savedBooking;
    }

    // 🔹 GET all bookings
    @GetMapping("/all")
    public List<Home> getAllBookings() {
        return homeRepository.findAll();
    }

    // 🔹 GET by mobile
    @GetMapping("/mobile")
    public List<Home> searchByMobile(@RequestParam String mobile) {
        return homeRepository.findByMobile(mobile);
    }

    @GetMapping("/scrap/item")
    public List<ScrapItem> getAllScrapItems() {
        return scrapItemRepository.findByActiveTrue();
    }
}
