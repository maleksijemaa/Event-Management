package org.example.serviceeventmanagement.DTO;

import lombok.*;
import org.example.serviceeventmanagement.Enum.EventStatus;
import org.example.serviceeventmanagement.Enum.Mood;
import org.example.serviceeventmanagement.Entity.Infoline;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {
    private String title;
    private String description;
    private String date;
    private String location;
    private EventStatus status;
    private Infoline infoline;
    private String imageBase64; // pour update
    private Mood mood; // <-- ajouté ici pour le mood
}
