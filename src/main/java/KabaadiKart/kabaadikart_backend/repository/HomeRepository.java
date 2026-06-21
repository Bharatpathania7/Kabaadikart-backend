package KabaadiKart.kabaadikart_backend.repository;

import KabaadiKart.kabaadikart_backend.model.Home;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HomeRepository  extends MongoRepository<Home , String> {
    List<Home> findByStatus(String status);

    // Exact match (existing)
    List<Home> findByNameIgnoreCase(String name);
    List<Home> findByMobile(String mobile);
    Optional<Home> findByBookingId(String bookingId);

    // ✅ Partial match / autocomplete
    List<Home> findByNameStartingWithIgnoreCase(String namePrefix);
    List<Home> findByMobileStartingWith(String mobilePrefix);
    List<Home> findByBookingIdStartingWith(String bookingIdPrefix);
}


