package prototype.backend.service;

import prototype.backend.model.EventType;

import java.util.List;

public interface IEventTypeService {
    List<EventType> getAll();
    EventType getById(int id);
    EventType create(EventType eventType);
    EventType update(int id, EventType eventType);
    void delete(int id);
}
