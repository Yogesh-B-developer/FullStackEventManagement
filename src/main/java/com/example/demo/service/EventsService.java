package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EventsEntity;
import com.example.demo.repository.EventsRepository;

@Service
public class EventsService {
    
    private static final Logger log = LoggerFactory.getLogger(EventsService.class);

    @Autowired
    EventsRepository eventsRepository;

    public List<EventsEntity> getAllEventsForAdminPage() {
        log.debug("Fetching all events for admin page");
        List<EventsEntity> allAdminEventsInDataBase = eventsRepository.findAll();
        log.info("Fetched {} events for admin page", allAdminEventsInDataBase.size());
        return allAdminEventsInDataBase;
    }

    // for adding events to database
    public boolean addEventsToDataBase(EventsEntity eventsEntity) {
        log.debug("Adding event to database: {}", eventsEntity);
        eventsRepository.save(eventsEntity);
        log.info("Event added to database: {}", eventsEntity.getEventId());
        return true;
    }

    // method for getting event by id
    public EventsEntity getEventById(String id) {
        log.debug("Fetching event by ID: {}", id);
        EventsEntity event = eventsRepository.findById(id).orElse(null);
        if (event != null) {
            log.info("Event found with ID: {}", id);
        } else {
            log.warn("No event found with ID: {}", id);
        }
        return event;
    }

    // method for updating events in database using id
    public boolean updateEventById(String id, String eventArea, String eventPrice) {
        log.debug("Updating event with ID: {}", id);
        Optional<EventsEntity> eventToBeUpdated = eventsRepository.findById(id);
        if (eventToBeUpdated.isPresent()) {
            EventsEntity ee = getEventById(id);
            if (eventArea != null) {
                ee.setArea(eventArea);
            }
            if (eventPrice != null) {
                ee.setPrice(eventPrice);
            }
            eventsRepository.save(ee);
            log.info("Event updated successfully with ID: {}", id);
            return true;
        }
        log.warn("No event found with ID: {} to update", id);
        return false;
    }

    // method for deleting events in database using id
    public boolean deleteEventById(String id) {
        log.debug("Deleting event with ID: {}", id);
        Optional<EventsEntity> eventToBeDeleted = eventsRepository.findById(id);
        if (eventToBeDeleted.isPresent()) {
            eventsRepository.deleteById(id);
            log.info("Event deleted successfully with ID: {}", id);
            return true;
        }
        log.warn("No event found with ID: {} to delete", id);
        return false;
    }
}
