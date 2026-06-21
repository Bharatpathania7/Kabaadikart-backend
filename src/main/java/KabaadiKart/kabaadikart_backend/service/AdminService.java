package KabaadiKart.kabaadikart_backend.service;

import KabaadiKart.kabaadikart_backend.model.Admin;
import KabaadiKart.kabaadikart_backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ Initialize default admins at startup
    @PostConstruct
    public void initDefaultAdmins() {
        createDefaultAdmin("bharatadmin", "Scrapsell@786", "SUPER_ADMIN");
        createDefaultAdmin("teamadmin", "Scrapsell@786", "ADMIN");
    }

    private void createDefaultAdmin(String username, String rawPassword, String role) {
        Optional<Admin> existing = adminRepository.findByUsername(username);
        if (existing.isEmpty()) {
            Admin admin = new Admin(username, passwordEncoder.encode(rawPassword), role);
            adminRepository.save(admin);
            System.out.println("✅ Created default admin: " + username + " with role " + role);
        } else {
            System.out.println("ℹ️ Admin already exists: " + username);
        }
    }

    // ✅ Create new admin with custom role
    public Admin createAdmin(String username, String rawPassword, String role) {
        Admin admin = new Admin(username, passwordEncoder.encode(rawPassword), role);
        return adminRepository.save(admin);
    }

    // ✅ Validate login
    public boolean validateAdmin(String username, String rawPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        return optionalAdmin.isPresent() &&
                passwordEncoder.matches(rawPassword, optionalAdmin.get().getPassword());
    }
}
