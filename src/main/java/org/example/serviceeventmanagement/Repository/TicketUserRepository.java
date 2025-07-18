package org.example.serviceeventmanagement.Repository;

import org.example.serviceeventmanagement.Entity.TicketUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketUserRepository extends MongoRepository<TicketUser, String> {
    List<TicketUser> findByBuyerId(Long buyerId);
    List<TicketUser> findByTicketId(String ticketId); // ça va marcher maintenant
}
