package org.example.serviceeventmanagement.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String title;
    private String description;
    private String date;
    private String location;
    private boolean validated = false;
    private boolean ticketsAvailable = false;

    @DBRef
    private List<Ticket> tickets;

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public void setTicketsAvailable(boolean ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", validated=" + validated +
                ", ticketsAvailable=" + ticketsAvailable +
                '}';
    }
}




