package KabaadiKart.kabaadikart_backend.repository;

import KabaadiKart.kabaadikart_backend.model.ScrapItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScrapItemRepository  extends MongoRepository <ScrapItem , String> {
    List<ScrapItem> findByCategoryIgnoreCaseAndActiveTrue(String category);
    List<ScrapItem> findByActiveTrue();
}
