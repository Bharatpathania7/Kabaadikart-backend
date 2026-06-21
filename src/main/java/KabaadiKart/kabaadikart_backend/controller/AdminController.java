//package KabaadiKart.kabaadikart_backend.controller;
//
////import KabaadiKart.kabaadikart_backend.Security.JwtUtil;
//import KabaadiKart.kabaadikart_backend.Security.JwtUtil;
//import KabaadiKart.kabaadikart_backend.model.Admin;
//import KabaadiKart.kabaadikart_backend.model.ContactUs;
//import KabaadiKart.kabaadikart_backend.model.Home;
//import KabaadiKart.kabaadikart_backend.repository.AdminRepository;
//import KabaadiKart.kabaadikart_backend.repository.ContactUsRepository;
//import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
////import KabaadiKart.kabaadikart_backend.service.AdminService;
//import KabaadiKart.kabaadikart_backend.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/admin")
//public class AdminController {
//    @Autowired
//    private EmailService emailService;
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Autowired
//    private HomeRepository bookingRepository;
//
//
////    private AdminService adminService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    // ✅ Admin login
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody Admin loginRequest) {
//        return adminRepository.findByUsername(loginRequest.getUsername())
//                .filter(admin -> passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword()))
//                .map(admin -> {
//                    String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
//                    Map<String, String> response = new HashMap<>();
//                    response.put("token", token);
//                    response.put("role", admin.getRole());
//                    return ResponseEntity.ok(response);
//                })
//                .orElseGet(() -> {
//                    Map<String, String> error = new HashMap<>();
//                    error.put("error", "Invalid Credentials");
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
//                });
//    }
//
//
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @GetMapping("/bookings")
//    public List<Home> getAllBookings() {
//        return bookingRepository.findAll();
//    }
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @GetMapping("/bookings/pending")
//    public ResponseEntity<List<Home>> getPendingBookings() {
//        List<Home> pendingBookings = bookingRepository.findByStatus("Pending Confirmation");
//        return ResponseEntity.ok(pendingBookings);
//    }
////
////    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
////    @GetMapping("/bookings/confirmed")
////    public ResponseEntity<List<Home>> getConfirmedBookings() {
////        List<Home> confirmedBookings = bookingRepository.findByStatus("Confirmed");
////        return ResponseEntity.ok(confirmedBookings);
////    }
//@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//@PutMapping("/bookings/{id}/Pickup")
//public Home PickupBooking(@PathVariable String id) {
//    Home booking = bookingRepository.findById(id)
//            .orElseThrow(() -> new RuntimeException("Booking not found"));
//    booking.setStatus(" Start Pickup");
//    emailService.sendBookingToTeam(booking);
//    booking.setUpdatedAt(LocalDateTime.now());
//    return bookingRepository.save(booking);
//}
//
//
//    //    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
////    @GetMapping("/bookings/search/name")
////    public List<Home> searchByName(@RequestParam String name) {
////        return bookingRepository.findByNameIgnoreCase(name);
////    }
////
////    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
////    @GetMapping("/bookings/search/mobile")
////    public List<Home> searchByMobile(@RequestParam String mobile) {
////        return bookingRepository.findByMobile(mobile);
////    }
////
////    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
////    @GetMapping("/bookings/search/id")
////    public Home searchByBookingId(@RequestParam String bookingId) {
////        return bookingRepository.findByBookingId(bookingId)
////                .orElseThrow(() -> new RuntimeException("Not Found"));
////    }
////@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
////@GetMapping("/bookings/search")
////public ResponseEntity<?> searchBookings(@RequestParam String query) {
////
////    if (query.startsWith("Bk")) { // Booking ID
////        return bookingRepository.findByBookingId(query)
////                .map(booking -> ResponseEntity.ok(booking))
////                .orElseThrow(() -> new RuntimeException("Booking not found"));
////    }
////
////    if (query.matches("\\d+")) { // Mobile number
////        return ResponseEntity.ok(bookingRepository.findByMobile(query));
////    }
////
////    // Otherwise treat as name
////    return ResponseEntity.ok(bookingRepository.findByNameIgnoreCase(query));
////}
//@PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//@GetMapping("/bookings/search")
//public ResponseEntity<?> searchBookings(@RequestParam String query) {
//
//    if (query.startsWith("Bk")) { // Booking ID search
//        List<Home> bookings = bookingRepository.findByBookingIdStartingWith(query);
//        return ResponseEntity.ok(bookings);
//    }
//
//    if (query.matches("\\d+")) { // Mobile number search
//        List<Home> bookings = bookingRepository.findByMobileStartingWith(query);
//        return ResponseEntity.ok(bookings);
//    }
//
//    // Name search (letters)
//    List<Home> bookings = bookingRepository.findByNameStartingWithIgnoreCase(query);
//    return ResponseEntity.ok(bookings);
//}
//
//
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @PutMapping("/bookings/{id}/confirm")
//    public Home confirmBooking(@PathVariable String id) {
//        Home booking = bookingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//        booking.setStatus("Confirmed");
//        emailService.sendBookingToTeam(booking);
//        booking.setUpdatedAt(LocalDateTime.now());
//        return bookingRepository.save(booking);
//
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @PutMapping("/bookings/{id}/complete")
//    public Home completeBooking(@PathVariable String id) {
//        Home booking = bookingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Booking not found"));
//        booking.setStatus("Completed");
//        return bookingRepository.save(booking);
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @DeleteMapping("/bookings/{id}")
//    public String deleteBooking(@PathVariable String id) {
//        bookingRepository.deleteById(id);
//        return "Booking deleted successfully";
//    }
//}
package KabaadiKart.kabaadikart_backend.controller;

import KabaadiKart.kabaadikart_backend.Security.JwtUtil;
import KabaadiKart.kabaadikart_backend.model.Admin;
import KabaadiKart.kabaadikart_backend.model.Home;
import KabaadiKart.kabaadikart_backend.model.ScrapItem;
import KabaadiKart.kabaadikart_backend.repository.AdminRepository;
import KabaadiKart.kabaadikart_backend.repository.HomeRepository;
import KabaadiKart.kabaadikart_backend.repository.ScrapItemRepository;
import KabaadiKart.kabaadikart_backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private HomeRepository bookingRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ScrapItemRepository scrapItemRepository;

    // ✅ Admin login
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Admin loginRequest) {
        return adminRepository.findByUsername(loginRequest.getUsername())
                .filter(admin -> passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword()))
                .map(admin -> {
                    String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);
                    response.put("role", admin.getRole());
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, String> error = new HashMap<>();
                    error.put("error", "Invalid Credentials");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
                });
    }

    // ✅ Get all bookings
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookings")
    public List<Home> getAllBookings() {
        return bookingRepository.findAll();
    }

    // ✅ Get pending bookings
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookings/pending")
    public ResponseEntity<List<Home>> getPendingBookings() {
        List<Home> pendingBookings = bookingRepository.findByStatus("Pending Confirmation");
        return ResponseEntity.ok(pendingBookings);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookings/confirmed")
    public ResponseEntity<List<Home>> getConfirmedBookings() {
        List<Home> ConfirmedBookings = bookingRepository.findByStatus("Confirmed");
        return ResponseEntity.ok(ConfirmedBookings);
    }
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookings/complete")
    public ResponseEntity<List<Home>> getCompleteBookings() {
        List<Home> completeBookings = bookingRepository.findByStatus("Completed");
        return ResponseEntity.ok(completeBookings);
    }

    // ✅ Start Pickup
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/bookings/{id}/Pickup")
    public Home pickupBooking(@PathVariable String id) {
        Home booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("Start Pickup");
//        emailService.sendBookingToTeam(booking); // notify team
        booking.setUpdatedAt(LocalDateTime.now());

        // Notify team
//        emailService.sendBookingToTeam(booking);

        // Notify customer about pickup started
        String customerEmail = "bharatpathania700@gmail.com"; // replace with booking.getEmail() when available
        emailService.sendPickupStarted(booking, customerEmail);

        return bookingRepository.save(booking);
    }

    // ✅ Search bookings (by ID, mobile, or name prefix)
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/bookings/search")
    public ResponseEntity<?> searchBookings(@RequestParam String query) {
        if (query.startsWith("Bk")) {
            return ResponseEntity.ok(bookingRepository.findByBookingIdStartingWith(query));
        }
        if (query.matches("\\d+")) {
            return ResponseEntity.ok(bookingRepository.findByMobileStartingWith(query));
        }
        return ResponseEntity.ok(bookingRepository.findByNameStartingWithIgnoreCase(query));
    }

    // ✅ Confirm booking (send email to team + customer)
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/bookings/{id}/confirm")
    public Home confirmBooking(@PathVariable String id) {
        Home booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("Confirmed");
        booking.setUpdatedAt(LocalDateTime.now());

        // Notify team
//        emailService.sendBookingToTeam(booking);

        // ✅ Send confirmation email to customer (for testing, hardcoded)
        String customerEmail = "bharatpathania700@gmail.com"; // replace later with booking.getEmail()
        emailService.sendBookingConfirmation(booking, customerEmail);

        return bookingRepository.save(booking);
    }

    // ✅ Complete booking
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/bookings/{id}/complete")
    public Home completeBooking(@PathVariable String id) {
        Home booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("Completed");
        booking.setUpdatedAt(LocalDateTime.now());
//        emailService.sendBookingToTeam(booking);

        // ✅ Send confirmation email to customer (for testing, hardcoded)
        String customerEmail = "bharatpathania700@gmail.com"; // replace later with booking.getEmail()
        emailService.sendBookingCompleted(booking, customerEmail);
        return bookingRepository.save(booking);
    }

    // ✅ Delete booking
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/bookings/{id}")
    public String deleteBooking(@PathVariable String id) {
        bookingRepository.deleteById(id);
        return "Booking deleted successfully";

    }
    // ✅ 1. Admin: Add new scrap item
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PostMapping("/scrap/item")
    public ScrapItem addScrapItem(@RequestBody ScrapItem item) {
        return scrapItemRepository.save(item);
    }

    // ✅ 2. Admin: Update scrap item price or details
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/scrap/item/{id}")
    public ScrapItem updateScrapItem(@PathVariable String id, @RequestBody ScrapItem updated) {
        ScrapItem existing = scrapItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existing.setCategory(updated.getCategory());
        existing.setName(updated.getName());
        existing.setUnit(updated.getUnit());
        existing.setPrice(updated.getPrice());
        existing.setDescription(updated.getDescription());
        return scrapItemRepository.save(existing);
    }

    // ✅ 3. Admin: Delete (soft delete)
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/scrap/item/{id}")
    public String deleteScrapItem(@PathVariable String id) {
        ScrapItem existing = scrapItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        existing.setActive(false);
        scrapItemRepository.save(existing);
        return "Scrap item deleted successfully";
    }

    // ✅ 4. Customer side: Get all active scrap items
    @GetMapping("/scrap/item")
    public List<ScrapItem> getAllScrapItems() {
        return scrapItemRepository.findByActiveTrue();
    }

    // ✅ 5. Customer side: Get by category (Paper, Metal, etc.)
    @GetMapping("/scrap/item/category/{category}")
    public List<ScrapItem> getScrapByCategory(@PathVariable String category) {
        return scrapItemRepository.findByCategoryIgnoreCaseAndActiveTrue(category);
    }
}
