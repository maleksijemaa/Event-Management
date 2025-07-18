package org.example.serviceeventmanagement.client;


import org.example.serviceeventmanagement.DTO.CollaborationEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Collabo", url = "http://localhost:8082") // ⚠️ adapte le port et nom si tu as un service discovery
public interface CollaboClient {

    @PostMapping("/api/collaborations")
    CollaborationEvent createCollaboration(@RequestParam String eventId, @RequestParam String artistId);

    @GetMapping("/api/collaborations/event/{eventId}")
    List<CollaborationEvent> getCollaborationsByEvent(@PathVariable("eventId") String eventId);


}

