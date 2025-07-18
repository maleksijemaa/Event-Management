package org.example.serviceeventmanagement.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.DTO.TicketRequest;
import org.example.serviceeventmanagement.Repository.EventRepository;
import org.example.serviceeventmanagement.Repository.TicketRepository;
import org.example.serviceeventmanagement.Service.Interface.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Override
    public Ticket createTicket(TicketRequest request, byte[] imageBytes) {
        if (!eventRepository.existsById(request.getEventId())) {
            throw new RuntimeException("Événement introuvable avec ID : " + request.getEventId());
        }

        Ticket ticket = Ticket.builder()
                .eventId(request.getEventId())
                .category(request.getCategory())
                .price(request.getPrice())
                .totalQuantity(request.getTotalQuantity())
                .remainingQuantity(request.getTotalQuantity())
                .image(imageBytes)
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(String id, TicketRequest ticketRequest, byte[] image) {
        Event event = eventRepository.findById(ticketRequest.getEventId())
                .orElseThrow(() -> new RuntimeException("Événement introuvable avec ID : " + ticketRequest.getEventId()));

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket introuvable avec ID : " + id));

        // Met à jour les champs
        ticket.setCategory(ticketRequest.getCategory());
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setTotalQuantity(ticketRequest.getTotalQuantity());
        ticket.setRemainingQuantity(ticketRequest.getTotalQuantity() - ticket.getSoldQuantity());
        ticket.setEventId(ticketRequest.getEventId());

        if (image != null) {
            ticket.setImage(image);
        }

        return ticketRepository.save(ticket);
    }


    @Override
    public Optional<Ticket> getTicketById(String id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getTicketsByEventId(String eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    @Override
    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }


    @Override
    public List<Ticket> getAvailableTickets() {
        return ticketRepository.findAll().stream()
                .filter(t -> t.getRemainingQuantity() != null && t.getRemainingQuantity() > 0)
                .toList();
    }

    @Override
    public List<Ticket> getSoldOutTickets() {
        return ticketRepository.findAll().stream()
                .filter(t -> t.getRemainingQuantity() != null && t.getRemainingQuantity() == 0)
                .toList();
    }

    @Override
    public List<Ticket> getMostSoldTickets() {
        return ticketRepository.findAll().stream()
                .sorted((a, b) -> Integer.compare(b.getSoldQuantity(), a.getSoldQuantity()))
                .limit(5) // Par exemple : top 5
                .toList();
    }
    @Override
    public int getTotalSoldTicketsByEventId(String eventId) {
        return ticketRepository.findByEventId(eventId).stream()
                .mapToInt(Ticket::getSoldQuantity)
                .sum();
    }

}
