package org.example.serviceeventmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }
    @Override
    public Event createEvent(Event event) {
        event.setValidated(false);
        event.setTicketsAvailable(false);
        return eventRepository.save(event);
    }
    @Override
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }
    @Override
    public Event updateEvent(String id, Event eventDetails) {
        Optional<Event> existingEvent = eventRepository.findById(id);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setDate(eventDetails.getDate());
            event.setLocation(eventDetails.getLocation());
            event.setValidated(eventDetails.isValidated());
            event.setTicketsAvailable(eventDetails.isTicketsAvailable());
            return eventRepository.save(event);
        } else {
            throw new RuntimeException("Event not found with id: " + id);
        }
    }

}

