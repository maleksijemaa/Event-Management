package org.example.serviceeventmanagement.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDto {
    private String id;  // String pour MongoDB ObjectId
    private String genre;
    private String email;
}
