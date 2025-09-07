package KabaadiKart.kabaadikart_backend.repository;

import KabaadiKart.kabaadikart_backend.model.ContactUs;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactUsRepository  extends MongoRepository<ContactUs , String> {
    ContactUs findByEmail (String  email);
}
