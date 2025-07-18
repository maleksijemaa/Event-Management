package org.example.serviceeventmanagement.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Entity.Ticket;
import org.example.serviceeventmanagement.Entity.TicketUser;
import org.example.serviceeventmanagement.Repository.EventRepository;
import org.example.serviceeventmanagement.Repository.TicketRepository;
import org.example.serviceeventmanagement.Repository.TicketUserRepository;
import org.example.serviceeventmanagement.Service.Interface.TicketUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketUserServiceImpl implements TicketUserService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final TicketUserRepository ticketUserRepository;

    @Override
    public TicketUser buyTicket(Long userId, String userName, String eventId, String category) {
        // Vérifier si l'événement existe
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Événement introuvable avec ID : " + eventId));

        // Récupérer tous les tickets pour cet événement
        List<Ticket> tickets = ticketRepository.findByEventId(eventId);

        // Trouver le ticket correspondant à la catégorie demandée
        Ticket ticketForCategory = tickets.stream()
                .filter(t -> category.equals(t.getCategory()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ticket introuvable pour la catégorie : " + category));

        // Vérifier la disponibilité dans le ticket (remainingQuantity)
        if (ticketForCategory.getRemainingQuantity() == null || ticketForCategory.getRemainingQuantity() <= 0) {
            throw new RuntimeException("Plus de tickets disponibles dans la catégorie : " + category);
        }

        // Vérifier que le prix est défini
        BigDecimal price = ticketForCategory.getPrice();
        if (price == null) {
            throw new RuntimeException("Prix non défini pour la catégorie : " + category);
        }

        // Décrémenter la quantité restante dans le ticket
        ticketForCategory.setRemainingQuantity(ticketForCategory.getRemainingQuantity() - 1);
        ticketRepository.save(ticketForCategory);

        // Créer le TicketUser représentant l'achat du ticket
        TicketUser ticketUser = TicketUser.builder()
                .buyerId(userId)
                .buyerName(userName)
                .price(price)
                .paid(false) // À mettre à jour si le paiement est effectué
                .validated(false)
                .category(category)
                .eventId(eventId)
                .ticketId(ticketForCategory.getId())
                .purchaseDate(LocalDateTime.now())
                .build();

        return ticketUserRepository.save(ticketUser);
    }

    @Override
    public List<TicketUser> getTicketsByBuyerId(Long buyerId) {
        return ticketUserRepository.findByBuyerId(buyerId);
    }

    @Override
    public void cancelTicketUser(String ticketUserId) {
        ticketUserRepository.deleteById(ticketUserId);
    }
}
