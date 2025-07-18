package org.example.serviceeventmanagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.DTO.TicketRequest;
import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.Service.Interface.TicketService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ticket> createTicket(
            @RequestPart("ticket") String ticketJson,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        try {
            // Désérialiser la chaîne JSON vers un objet TicketRequest
            ObjectMapper objectMapper = new ObjectMapper();
            TicketRequest ticketRequest = objectMapper.readValue(ticketJson, TicketRequest.class);

            // Lire les octets de l'image si elle est présente
            byte[] imageBytes = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                imageBytes = imageFile.getBytes();
            }

            // Créer le ticket
            Ticket ticket = ticketService.createTicket(ticketRequest, imageBytes);
            return ResponseEntity.ok(ticket);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ticket> updateTicket(
            @PathVariable String id,
            @RequestPart("ticket") String ticketJson,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TicketRequest ticketRequest = objectMapper.readValue(ticketJson, TicketRequest.class);

            byte[] imageBytes = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                imageBytes = imageFile.getBytes();
            }

            Ticket updatedTicket = ticketService.updateTicket(id, ticketRequest, imageBytes);
            return ResponseEntity.ok(updatedTicket);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Ticket>> getTicketsByEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(ticketService.getTicketsByEventId(eventId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/available")
    public ResponseEntity<List<Ticket>> getAvailableTickets() {
        return ResponseEntity.ok(ticketService.getAvailableTickets());
    }

    @GetMapping("/soldout")
    public ResponseEntity<List<Ticket>> getSoldOutTickets() {
        return ResponseEntity.ok(ticketService.getSoldOutTickets());
    }

    @GetMapping("/top-sold")
    public ResponseEntity<List<Ticket>> getMostSoldTickets() {
        return ResponseEntity.ok(ticketService.getMostSoldTickets());
    }


    @GetMapping("/sold/event/{eventId}")
    public ResponseEntity<Integer> getTotalSoldTicketsByEvent(@PathVariable String eventId) {
        int totalSold = ticketService.getTotalSoldTicketsByEventId(eventId);
        return ResponseEntity.ok(totalSold);
    }

    @GetMapping("/total-sold/{eventId}")
    public ResponseEntity<Integer> getTotalSoldTicketsByEventId(@PathVariable String eventId) {
        int totalSold = ticketService.getTotalSoldTicketsByEventId(eventId);
        return ResponseEntity.ok(totalSold);
    }


}
