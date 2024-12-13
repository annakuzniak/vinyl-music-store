package com.vinyl.musicstore.repository;

import com.vinyl.musicstore.model.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinylRepository extends JpaRepository<Vinyl,Long> {

    List<Vinyl> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String title, String artist);
}
