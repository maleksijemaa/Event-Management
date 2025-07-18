package org.example.serviceeventmanagement.client;
import org.example.serviceeventmanagement.DTO.ArtistDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UsersArtAndOrg", url = "http://localhost:8087") // Change le port si besoin
public interface ArtistClient {
    @GetMapping("/api/artists/{id}")
    ArtistDto getArtistById(@PathVariable String id);

    @GetMapping("/api/artists")
    List<ArtistDto> getAllArtists();

    @GetMapping("/api/users/artist/nickname/{nickname}")
    ArtistDto getArtistByNickname(@PathVariable("nickname") String nickname);
}

