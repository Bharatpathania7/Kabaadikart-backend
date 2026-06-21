package KabaadiKart.kabaadikart_backend.controller;//package KabaadiKart.kabaadikart_backend.controller;
//
//public class ScrapController {
//}
import KabaadiKart.kabaadikart_backend.model.ScrapItem;
import KabaadiKart.kabaadikart_backend.repository.ScrapItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scrap")
public class ScrapController {

    @Autowired
    private ScrapItemRepository scrapItemRepository;

    // ✅ 1. Admin: Add new scrap item
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @PostMapping
//    public ScrapItem addScrapItem(@RequestBody ScrapItem item) {
//        return scrapItemRepository.save(item);
//    }
//
//    // ✅ 2. Admin: Update scrap item price or details
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @PutMapping("/{id}")
//    public ScrapItem updateScrapItem(@PathVariable String id, @RequestBody ScrapItem updated) {
//        ScrapItem existing = scrapItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//        existing.setCategory(updated.getCategory());
//        existing.setName(updated.getName());
//        existing.setUnit(updated.getUnit());
//        existing.setPrice(updated.getPrice());
//        existing.setDescription(updated.getDescription());
//        return scrapItemRepository.save(existing);
//    }
//
//    // ✅ 3. Admin: Delete (soft delete)
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
//    @DeleteMapping("/{id}")
//    public String deleteScrapItem(@PathVariable String id) {
//        ScrapItem existing = scrapItemRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//        existing.setActive(false);
//        scrapItemRepository.save(existing);
//        return "Scrap item deleted successfully";
//    }

    // ✅ 4. Customer side: Get all active scrap items
    @GetMapping
    public List<ScrapItem> getAllScrapItems() {
        return scrapItemRepository.findByActiveTrue();
    }

//    // ✅ 5. Customer side: Get by category (Paper, Metal, etc.)
//    @GetMapping("/category/{category}")
//    public List<ScrapItem> getScrapByCategory(@PathVariable String category) {
//        return scrapItemRepository.findByCategoryIgnoreCaseAndActiveTrue(category);
//    }
//}
}