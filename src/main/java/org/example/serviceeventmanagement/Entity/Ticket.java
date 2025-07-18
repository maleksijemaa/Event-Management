// Ticket.java
package org.example.serviceeventmanagement.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    private String id;

    private String eventId;
    private String category;
    private BigDecimal price;
    private Integer totalQuantity;
    private Integer remainingQuantity;
    private byte[] image;

    public int getSoldQuantity() {
        return (totalQuantity != null && remainingQuantity != null)
                ? totalQuantity - remainingQuantity : 0;
    }
}