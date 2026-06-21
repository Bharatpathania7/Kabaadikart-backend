package KabaadiKart.kabaadikart_backend.blur.Service;

import KabaadiKart.kabaadikart_backend.blur.Repository.WaitlistRepository;
import KabaadiKart.kabaadikart_backend.blur.entity.Waitlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WaitlistService {

    private final WaitlistRepository repository;

    public void join(String email) {

        if (repository.existsByEmail(email)) {
            throw new RuntimeException("Already joined");
        }

        Waitlist waitlist = new Waitlist();
        waitlist.setEmail(email);
        waitlist.setCreatedAt(LocalDateTime.now().toString());

        repository.save(waitlist);
    }
}