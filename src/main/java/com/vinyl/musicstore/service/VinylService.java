package com.vinyl.musicstore.service;

import com.vinyl.musicstore.model.Vinyl;
import com.vinyl.musicstore.repository.VinylRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylService {

    private final VinylRepository vinylRepository;

    @Autowired
    public VinylService(VinylRepository vinylRepository) {
        this.vinylRepository = vinylRepository;
    }

    public Vinyl save(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    public List<Vinyl> getAllVinyls() {
        return vinylRepository.findAll();
    }

    public Vinyl getVinylById(Long id) {
        return vinylRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vinyl with id: " + id + "not found"));
    }

    public Vinyl updateVinyl(Long id, Vinyl updatedVinyl) {
        Vinyl existingVinyl = vinylRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vinyl with id: " + id + "not found"));

        existingVinyl.setTitle(updatedVinyl.getTitle());
        existingVinyl.setArtist(updatedVinyl.getArtist());
        existingVinyl.setGenre(updatedVinyl.getGenre());
        existingVinyl.setPrice(updatedVinyl.getPrice());
        existingVinyl.setStock(updatedVinyl.getStock());

        return vinylRepository.save(existingVinyl);
    }

    public void deleteVinyl(Long id) {
        if (!vinylRepository.existsById(id)) {
            throw new RuntimeException("Vinyl with id: " + id + "not found");
        }
        vinylRepository.deleteById(id);
    }

    public List<Vinyl> searchVinyl(String query) {
        return vinylRepository.findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(query, query);
    }

    public Vinyl purchaseVinyl(Long id, int quantity) {
        Vinyl vinyl = vinylRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vinyl with id: " + id + "not found"));

        if (vinyl.getStock() < quantity) {
            throw new RuntimeException("Not enough stock for vinyl " + vinyl.getTitle());
        }

        vinyl.setStock(vinyl.getStock() - quantity);
        return vinylRepository.save(vinyl);
    }
}
