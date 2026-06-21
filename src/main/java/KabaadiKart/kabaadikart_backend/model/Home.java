package KabaadiKart.kabaadikart_backend.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document (collection = "bookings" )
public class Home {
    @Id
    private String id;  // MongoDB will generate ObjectId

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Step 1: Basic User Info
    private String name;
    private String mobile;
    private  String email;
    private String address;
    private String landmark;
    private String city;
    private String pincode;

    // Step 2: Scrap Details
    private String scrapType;    // Plastic, Metal, E-Waste, etc.
    private String quantity;     // Small, Medium, Large, Bulk
    private String photoUrl;     // Path or S3 URL
    private String notes;

    // Step 3: Pickup Scheduling
    private String bookingId;    // BK1757270481160
    private String status;       // Pending Confirmation, Confirmed, Picked, Completed
    private String pickupDate;   // yyyy-MM-dd
    private String pickupSlot;   // Morning (9-12), Afternoon (12-3), Evening (3-6)

    // System fields
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String stepStatus; // Track which step is completed

    public String getStepStatus() { return stepStatus; }
    public void setStepStatus(String stepStatus) { this.stepStatus = stepStatus; }


    public Home() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = "Pending Confirmation"; // default status
    }

    // Getters & Setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getScrapType() {
        return scrapType;
    }
    public void setScrapType(String scrapType) {
        this.scrapType = scrapType;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPickupDate() {
        return pickupDate;
    }
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupSlot() {
        return pickupSlot;
    }
    public void setPickupSlot(String pickupSlot) {
        this.pickupSlot = pickupSlot;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}