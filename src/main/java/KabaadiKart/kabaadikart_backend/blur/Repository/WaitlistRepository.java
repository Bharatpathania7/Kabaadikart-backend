package KabaadiKart.kabaadikart_backend.blur.Repository;

import KabaadiKart.kabaadikart_backend.blur.entity.Waitlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitlistRepository
        extends MongoRepository<Waitlist, String> {

    boolean existsByEmail(String email);
}