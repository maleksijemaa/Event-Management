package org.example.serviceeventmanagement.Controller;
import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.example.serviceeventmanagement.DTO.TicketRequest;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest request) {
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/by-event/{eventId}")
    public ResponseEntity<List<Ticket>> getTicketsByEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(ticketService.getTicketsByEvent(eventId));
    }
}

