package org.example.serviceeventmanagement.Service.Interface;

import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Entity.Infoline;
import org.example.serviceeventmanagement.Enum.EventStatus;
import org.example.serviceeventmanagement.Enum.Mood;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(String title,
                      String date,
                      String location,
                      String description,
                      EventStatus status,
                      Infoline infoline,
                      byte[] imageBytes,
                      Mood mood);

    Event updateEvent(String id,
                      String title,
                      String date,
                      String location,
                      String description,
                      EventStatus status,
                      Infoline infoline,
                      String imageBase64,
                      Mood mood);

    Event cancelEvent(String id);

    Optional<Event> getEventById(String id);

    List<Event> getAllEvents();
}
