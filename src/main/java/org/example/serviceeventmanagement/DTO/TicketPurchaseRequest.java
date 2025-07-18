package org.example.serviceeventmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketPurchaseRequest {
    private Long userId;
    private String userName;
    private String eventId;
    private String category;
}
