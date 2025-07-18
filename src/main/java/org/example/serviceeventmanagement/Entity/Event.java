package org.example.serviceeventmanagement.Entity;

import lombok.*;
import org.example.serviceeventmanagement.Enum.EventStatus;
import org.example.serviceeventmanagement.Enum.Mood;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    private String id;

    private String title;
    private String description;
    private String date;
    private String location;
    private byte[] image;
    private EventStatus status = EventStatus.PRE_RELEASE;
    private Infoline infoline;
    private Mood mood;
    private String organizedBy;
}
