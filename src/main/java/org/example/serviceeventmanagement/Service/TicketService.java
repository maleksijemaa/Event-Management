package org.example.serviceeventmanagement.Service;

import org.example.serviceeventmanagement.DTO.TicketRequest;
import org.example.serviceeventmanagement.Entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTicketsByEvent(String eventId);
    Ticket createTicket(TicketRequest request);

}
