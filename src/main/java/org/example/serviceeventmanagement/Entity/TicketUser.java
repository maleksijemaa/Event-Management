package org.example.serviceeventmanagement.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "ticket_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUser {

    @Id
    private String id;

    private Long buyerId;
    private String buyerName;

    private BigDecimal price;
    private boolean paid;
    private boolean validated;

    private String category;
    private String eventId;

    private String ticketId; // ✅ Ajouté ici

    private LocalDateTime purchaseDate;
}
