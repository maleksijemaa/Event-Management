package org.example.serviceeventmanagement.Service.Interface;

import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.DTO.TicketRequest;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket createTicket(TicketRequest request, byte[] imageBytes);

    Ticket updateTicket(String id, TicketRequest request, byte[] imageBytes);

    Optional<Ticket> getTicketById(String id);

    List<Ticket> getTicketsByEventId(String eventId);

    void deleteTicket(String id);

    List<Ticket> getAvailableTickets();
    List<Ticket> getSoldOutTickets();
    List<Ticket> getMostSoldTickets();

    int getTotalSoldTicketsByEventId(String eventId);


}
