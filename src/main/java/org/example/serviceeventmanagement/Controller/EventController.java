package org.example.serviceeventmanagement.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.serviceeventmanagement.DTO.EventRequest;
import org.example.serviceeventmanagement.Entity.Event;
import org.example.serviceeventmanagement.Service.Interface.EventService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * ✅ Créer un événement avec image envoyée en MultipartFormData
     * Body Postman : form-data
     * - key: event (type: text) → JSON string
     * - key: image (type: file) → image
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Event> createEvent(
            @RequestPart("event") String eventJson,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EventRequest eventRequest = objectMapper.readValue(eventJson, EventRequest.class);

            byte[] imageBytes = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                imageBytes = imageFile.getBytes();
            }

            Event event = eventService.createEvent(
                    eventRequest.getTitle(),
                    eventRequest.getDate(),
                    eventRequest.getLocation(),
                    eventRequest.getDescription(),
                    eventRequest.getStatus(),
                    eventRequest.getInfoline(),
                    imageBytes,
                    eventRequest.getMood()
            );
            return ResponseEntity.ok(event);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * ✅ Mise à jour d’un événement (image en Base64 incluse dans le JSON)
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody EventRequest eventRequest) {
        try {
            Event event = eventService.updateEvent(
                    id,
                    eventRequest.getTitle(),
                    eventRequest.getDate(),
                    eventRequest.getLocation(),
                    eventRequest.getDescription(),
                    eventRequest.getStatus(),
                    eventRequest.getInfoline(),
                    eventRequest.getImageBase64(),  // image sous forme de base64 string
                    eventRequest.getMood()
            );
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * ✅ Annuler un événement
     */
    @PutMapping("/cancel/{id}")
    public ResponseEntity<Event> cancelEvent(@PathVariable String id) {
        try {
            Event event = eventService.cancelEvent(id);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * ✅ Récupérer un événement par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * ✅ Récupérer tous les événements
     */
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
}
