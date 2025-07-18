package org.example.serviceeventmanagement.Service.Interface;

import org.example.serviceeventmanagement.Entity.TicketUser;

import java.util.List;

public interface TicketUserService {
    TicketUser buyTicket(Long userId, String userName, String eventId, String category);
    List<TicketUser> getTicketsByBuyerId(Long buyerId);
    void cancelTicketUser(String ticketUserId);
}
