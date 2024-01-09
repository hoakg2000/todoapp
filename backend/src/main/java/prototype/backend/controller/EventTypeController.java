package prototype.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import prototype.backend.model.EventType;
import prototype.backend.service.IEventTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/event-types")
public class EventTypeController {

    @Autowired
    private IEventTypeService eventTypeService;

    @GetMapping
    public ResponseEntity<List<EventType>> getAllEventTypes() {
        return ResponseEntity.ok(eventTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventType> getEventTypeById(@PathVariable int id) {
        return ResponseEntity.ok(eventTypeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventType> createEventType(@RequestBody EventType eventType) {
        EventType createdEventType = eventTypeService.create(eventType);
        return new ResponseEntity<>(createdEventType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventType> updateEventType(@PathVariable int id, @RequestBody EventType eventType) {
        EventType updatedEventType = eventTypeService.update(id, eventType);
        return updatedEventType != null ? ResponseEntity.ok(updatedEventType) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventType(@PathVariable int id) {
        eventTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
