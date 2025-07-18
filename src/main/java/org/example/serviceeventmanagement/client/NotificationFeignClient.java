package org.example.serviceeventmanagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service", url = "http://localhost:8083")
public interface NotificationFeignClient {
    @PostMapping("/notifications")
    void sendNotificationToArtist(@RequestParam String artistId, @RequestParam String message);
}

