package org.example.serviceeventmanagement.Service;

import org.example.serviceeventmanagement.Entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAllEvents();
    Optional<Event> getEventById(String id);
    Event createEvent(Event event);
    Event updateEvent(String id, Event eventDetails);  // Ajout de la méthode update
    void deleteEvent(String id);
}