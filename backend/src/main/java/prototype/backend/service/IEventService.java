package prototype.backend.service;

import prototype.backend.dto.request.EventRequestDTO;
import prototype.backend.model.Event;

import java.util.Date;
import java.util.List;

public interface IEventService {
    List<Event> getAll();
    List<Event> getByRange(Date start, Date end);
    List<Event> getByMonth(int year, int month);
    List<Event> update(EventRequestDTO eventRequestDTO);
    List<Event> create(EventRequestDTO eventRequestDTO);
    void delete(int id);
}
