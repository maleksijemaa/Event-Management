package org.example.serviceeventmanagement.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.example.serviceeventmanagement.DTO.CollaborationEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LineUpListener {

    @KafkaListener(topics = "collab-invitation-topic", groupId = "lineup-group")
    public void listenCollabInvitation(CollaborationEvent event) {
        log.info("📩 Événement Kafka reçu dans LineUpListener: {}", event);

        // Pour l'instant, on ignore le traitement et on log uniquement
        log.info("ℹ️ Traitement LineUp temporairement désactivé");
    }
}
