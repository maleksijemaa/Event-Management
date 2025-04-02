package org.example.serviceeventmanagement.Service;

import org.example.serviceeventmanagement.DTO.TicketRequest;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.Repository.EventRepository;
import org.example.serviceeventmanagement.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    public Ticket createTicket(TicketRequest request) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found!"));

        Ticket ticket = new Ticket();
        ticket.setBuyerName(request.getBuyerName());
        ticket.setPrice(request.getPrice());
        ticket.setPaid(request.isPaid());
        ticket.setEvent(event);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByEvent(String eventId) {
        return ticketRepository.findByEventId(eventId);
    }
}




