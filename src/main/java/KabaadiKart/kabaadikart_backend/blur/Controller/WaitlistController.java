package KabaadiKart.kabaadikart_backend.blur.Controller;

import KabaadiKart.kabaadikart_backend.blur.Service.WaitlistService;
import KabaadiKart.kabaadikart_backend.blur.dto.WaitlistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/waitlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WaitlistController {

    private final WaitlistService service;

    @PostMapping
    public ResponseEntity<?> join(
            @RequestBody WaitlistRequest request) {

        service.join(request.getEmail());

        return ResponseEntity.ok().build();
    }
}