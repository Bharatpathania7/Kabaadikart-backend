package KabaadiKart.kabaadikart_backend.repository;

import KabaadiKart.kabaadikart_backend.model.Home;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeRepository  extends MongoRepository<Home , String> {
    Home findByMobileNumber(String MobileNumber);
}
