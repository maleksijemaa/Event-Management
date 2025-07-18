package org.example.serviceeventmanagement.Controller;

import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.Service.Interface.TicketUserService;
import org.example.serviceeventmanagement.Entity.TicketUser;
import org.example.serviceeventmanagement.dto.TicketPurchaseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-users")
@RequiredArgsConstructor
public class TicketUserController {

    private final TicketUserService ticketUserService;

    @PostMapping("/buy")
    public ResponseEntity<TicketUser> buyTicket(@RequestBody TicketPurchaseRequest request) {
        TicketUser ticketUser = ticketUserService.buyTicket(
                request.getUserId(),
                request.getUserName(),
                request.getEventId(),
                request.getCategory()
        );
        return ResponseEntity.ok(ticketUser);
    }

    @GetMapping("/buyer/{buyerId}")
    public ResponseEntity<List<TicketUser>> getTicketsByBuyer(@PathVariable Long buyerId) {
        List<TicketUser> tickets = ticketUserService.getTicketsByBuyerId(buyerId);
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/cancel/{ticketUserId}")
    public ResponseEntity<Void> cancelTicketUser(@PathVariable String ticketUserId) {
        ticketUserService.cancelTicketUser(ticketUserId);
        return ResponseEntity.noContent().build();
    }
}
