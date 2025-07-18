package org.example.serviceeventmanagement.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollaborationEvent {
    private String eventType; // ex: INVITATION_SENT, INVITATION_ACCEPTED, INVITATION_REJECTED
    private String eventId;
    private String artistId;
    private String organizerId;
}
