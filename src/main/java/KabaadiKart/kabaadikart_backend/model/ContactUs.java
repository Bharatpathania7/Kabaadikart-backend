package KabaadiKart.kabaadikart_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document ( collection =  "Message")
public class ContactUs {
    @Id
    private String id;

    private String fullName;
    private String mobileNumber;

    public ContactUs(String fullName, String mobileNumber, String email, String subject, String message) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String email;
    private String subject;
    private String message;
}
