package prototype.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prototype.backend.ExceptionHandler.exception.NotFoundException;
import prototype.backend.ExceptionHandler.exception.UniqueEntityException;
import prototype.backend.dto.request.EventRequestDTO;
import prototype.backend.model.Event;
import prototype.backend.service.IEventService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private IEventService eventService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        throw new UniqueEntityException("Test");
//        return new ResponseEntity<>("testing", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<List<Event>> getEventsByRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return new ResponseEntity<>(eventService.getByRange(start, end), HttpStatus.OK);
    }

    @GetMapping("/month")
    public ResponseEntity<List<Event>> getEventsByMonth(@RequestParam int year, @RequestParam int month) {
        return new ResponseEntity<>(eventService.getByMonth(year, month), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<List<Event>> updateEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return new ResponseEntity<>(eventService.update(eventRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<List<Event>> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        return new ResponseEntity<>(eventService.create(eventRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable int id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
