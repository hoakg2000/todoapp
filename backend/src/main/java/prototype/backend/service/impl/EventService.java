package prototype.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototype.backend.ExceptionHandler.exception.NotFoundException;
import prototype.backend.dto.request.EventRequestDTO;
import prototype.backend.model.Event;
import prototype.backend.model.EventType;
import prototype.backend.repository.IEventRepository;
import prototype.backend.repository.IEventTypeRepository;
import prototype.backend.service.IEventService;
import prototype.backend.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventService implements IEventService {

    private final Logger logger = Logger.getLogger(EventService.class.getName());
    @Autowired
    IEventRepository eventRepository;

    @Autowired
    IEventTypeRepository eventTypeRepository;

    @Override
    public List<Event> getAll() {
        List<Event> events = eventRepository.findAll();

        return events;
    }

    @Override
    public List<Event> getByRange(Date start, Date end) {
        List<Event> events = eventRepository.findByDateBetween(start, end);

        return events;
    }

    @Override
    public List<Event> getByMonth(int year, int month) {
        List<Event> events = eventRepository.findByDateInMonthAndYear(month, year);

        return events;
    }

    @Override
    public List<Event> update(EventRequestDTO eventRequestDTO) {

        return null;
    }

    @Override
    public List<Event> create(EventRequestDTO eventRequestDTO) {
        Optional<EventType> eventType = eventTypeRepository.findById(eventRequestDTO.getType());
        if (eventType.isEmpty()){
            throw new NotFoundException(logger, "Can't find event type id: " + eventRequestDTO.getType());
        }
        List<Event> addedEvent = new ArrayList<>();
        Event event = new Event()
                .setName(eventRequestDTO.getName())
                .setDetail(eventRequestDTO.getDetail())
                .setEventType(eventType.get())
                .setDate(eventRequestDTO.getStart());
        do {
            addedEvent.add(eventRepository.save(event));
            event.setDate(DateTimeUtils.increaseDate(event.getDate(), eventRequestDTO.getRepeat()));
        }while(event.getDate().before(eventRequestDTO.getEnd()));
        return addedEvent;
    }

    @Override
    public void delete(int id) {

    }


}
