package KabaadiKart.kabaadikart_backend.blur.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "waitlist")
public class Waitlist {

    @Id
    private String id;

    private String email;

    private String createdAt;


}