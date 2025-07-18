package org.example.serviceeventmanagement.Service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Entity.Infoline;
import org.example.serviceeventmanagement.Enum.EventStatus;
import org.example.serviceeventmanagement.Enum.Mood;
import org.example.serviceeventmanagement.Repository.EventRepository;
import org.example.serviceeventmanagement.Service.Interface.EventService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event createEvent(String title,
                             String date,
                             String location,
                             String description,
                             EventStatus status,
                             Infoline infoline,
                             byte[] imageBytes,
                             Mood mood) {

        Event event = Event.builder()
                .title(title)
                .date(date)
                .location(location)
                .description(description)
                .status(status != null ? status : EventStatus.PRE_RELEASE)
                .infoline(infoline)
                .image(imageBytes)
                .mood(mood)
                .build();

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(String id,
                             String title,
                             String date,
                             String location,
                             String description,
                             EventStatus status,
                             Infoline infoline,
                             String imageBase64,
                             Mood mood) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement introuvable avec ID : " + id));

        event.setTitle(title);
        event.setDate(date);
        event.setLocation(location);
        event.setDescription(description);
        if (status != null) event.setStatus(status);
        event.setInfoline(infoline);
        if (mood != null) event.setMood(mood);

        if (imageBase64 != null && !imageBase64.isBlank()) {
            try {
                byte[] imageBytes = Base64.getDecoder().decode(imageBase64);
                event.setImage(imageBytes);
            } catch (IllegalArgumentException e) {
                log.error("Erreur lors du décodage de l'image Base64", e);
                throw new RuntimeException("Image Base64 invalide");
            }
        }

        return eventRepository.save(event);
    }

    @Override
    public Event cancelEvent(String id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement introuvable avec ID : " + id));
        event.setStatus(EventStatus.CANCELLED);
        return eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
