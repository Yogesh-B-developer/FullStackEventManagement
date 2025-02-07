package com.example.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.EventsEntity;
import com.example.demo.repository.EventsRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventsRepository eventsRepository;

    @InjectMocks
    private EventsService eventsService;

    private EventsEntity eventsEntity;

    @BeforeEach
    void setUp() {
        eventsEntity = new EventsEntity();
        eventsEntity.setEventId("1");
        eventsEntity.setArea("Area 1");
        eventsEntity.setPrice("100");
    }

    @Test
    public void testGetAllEventsForAdminPage() {
        when(eventsRepository.findAll()).thenReturn(Collections.singletonList(eventsEntity));

        List<EventsEntity> result = eventsService.getAllEventsForAdminPage();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(eventsRepository, times(1)).findAll();
    }

    @Test
    public void testAddEventsToDataBase() {
        when(eventsRepository.save(any(EventsEntity.class))).thenReturn(eventsEntity);

        boolean result = eventsService.addEventsToDataBase(eventsEntity);

        assertTrue(result);
        verify(eventsRepository, times(1)).save(any(EventsEntity.class));
    }

    @Test
    public void testGetEventById() {
        when(eventsRepository.findById(anyString())).thenReturn(Optional.of(eventsEntity));

        EventsEntity result = eventsService.getEventById("1");

        assertNotNull(result);
        assertEquals("Area 1", result.getArea());
        verify(eventsRepository, times(1)).findById(anyString());
    }


    @Test
    public void testDeleteEventById() {
        when(eventsRepository.findById(anyString())).thenReturn(Optional.of(eventsEntity));
        doNothing().when(eventsRepository).deleteById(anyString());

        boolean result = eventsService.deleteEventById("1");

        assertTrue(result);
        verify(eventsRepository, times(1)).findById(anyString());
        verify(eventsRepository, times(1)).deleteById(anyString());
    }
}
