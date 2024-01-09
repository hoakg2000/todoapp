package prototype.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prototype.backend.ExceptionHandler.exception.ApiRequestException;
import prototype.backend.ExceptionHandler.exception.NotFoundException;
import prototype.backend.model.EventType;
import prototype.backend.repository.IEventTypeRepository;
import prototype.backend.service.IEventTypeService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EventTypeService implements IEventTypeService {

    private final Logger logger = Logger.getLogger(EventTypeService.class.getName());

    @Autowired
    IEventTypeRepository eventTypeRepository;

    @Override
    public List<EventType> getAll() {
        List<EventType> eventTypes = eventTypeRepository.findAll();
        return eventTypes;
    }

    @Override
    public EventType getById(int id) {
        Optional<EventType> optionalEventType = eventTypeRepository.findById(id);
        if (optionalEventType.isEmpty()){
            throw new NotFoundException(logger, "Can't find event type match id " + id);
        }
        return optionalEventType.get();
    }

    @Override
    public EventType create(EventType eventType) {
        EventType res;
        try{
            res = eventTypeRepository.save(eventType);
        }catch (Exception ex){
            throw new ApiRequestException(logger, ex.getMessage());
        }
        return res;
    }

    @Override
    public EventType update(int id, EventType eventType) {
        Optional<EventType> optionalEventType = eventTypeRepository.findById(id);
        if (optionalEventType.isEmpty()){
            throw new NotFoundException(logger, "Can't find matched event");
        }
        EventType event = optionalEventType.get();

        event.setName(eventType.getName())
                .setIcon(eventType.getIcon());
        try{
            return eventTypeRepository.save(event);
        }catch (Exception ex){
            throw new ApiRequestException(logger, ex.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        eventTypeRepository.deleteById(id);
    }
}
