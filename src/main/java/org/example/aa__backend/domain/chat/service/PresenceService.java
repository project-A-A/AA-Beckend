package org.example.aa__backend.domain.chat.service;

import org.example.aa__backend.domain.chat.payload.PresenceEvent;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceService {

    private final Map<UUID, PresenceEvent> presenceMap = new ConcurrentHashMap<>();

    public void update(PresenceEvent event) {
        presenceMap.put(event.getUserId(), event);
    }

    public Map<UUID, PresenceEvent> snapshot() {
        return Map.copyOf(presenceMap);
    }
}

