package com.vinyl.musicstore.controller;

import com.vinyl.musicstore.model.Vinyl;
import com.vinyl.musicstore.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vinyls")
public class VinylController {

    private final VinylService vinylService;

    @Autowired
    public VinylController(VinylService vinylService) {
        this.vinylService = vinylService;
    }

    @GetMapping
    public List<Vinyl> getAllVinyls() {
        return vinylService.getAllVinyls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vinyl> getVinylById(@PathVariable Long id) {
        Vinyl vinyl = vinylService.getVinylById(id);
        return ResponseEntity.ok(vinyl);
    }

    @PostMapping
    public ResponseEntity<Vinyl> createVinyl(@RequestBody Vinyl vinyl) {
        Vinyl createdVinyl = vinylService.createVinyl(vinyl);
        return ResponseEntity.ok(createdVinyl);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vinyl> updateVinyl(@PathVariable Long id, @RequestBody Vinyl updatedVinyl) {
        Vinyl updated = vinylService.updateVinyl(id, updatedVinyl);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVinyl(@PathVariable Long id) {
        vinylService.deleteVinyl(id);
        return ResponseEntity.ok("Vinyl with id: " + id + "has been deleted.");
    }

    @GetMapping("/search")
    public List<Vinyl> searchVinyls(@RequestParam String query) {
        return vinylService.searchVinyl(query);
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Vinyl> purchaseVinyl(@PathVariable Long id, @RequestParam int quantity) {
        Vinyl purchasedVinyl = vinylService.purchaseVinyl(id, quantity);
        return ResponseEntity.ok(purchasedVinyl);
    }

}
