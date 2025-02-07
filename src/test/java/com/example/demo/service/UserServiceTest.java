package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.EventsEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.EventsRepository;
import com.example.demo.repository.UserRepository;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EventsRepository eventsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserDetailsToDatabase() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmailId("test@example.com");
        
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        
        boolean result = userService.addUserDetailsToDatabase(userEntity);
        
        assertTrue(result);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testCheckForCorrectEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmailId("test@example.com");

        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(userEntity));

        boolean result = userService.checkForCorrectEmail("test@example.com");

        assertTrue(result);
        verify(userRepository, times(1)).findById("test@example.com");
    }

    @Test
    public void testCheckForCorrectPassword() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmailId("test@example.com");
        userEntity.setUserPassword("password123");
        userEntity.setUserName("Test User");

        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(userEntity));

        String result = userService.checkForCorrectPassword("test@example.com", "password123");

        assertEquals("Test User", result);
        verify(userRepository, times(1)).findById("test@example.com");
    }

    @Test
    public void testGetAllEventsForUserPage() {
        List<EventsEntity> events = List.of(new EventsEntity(), new EventsEntity());

        when(eventsRepository.findAll()).thenReturn(events);

        List<EventsEntity> result = userService.getAllEventsForUserPage();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(eventsRepository, times(1)).findAll();
    }

    @Test
    public void testAddEventIdToUserDataBase() {
        UserEntity userEntity = new UserEntity();
        EventsEntity eventsEntity = new EventsEntity();
        userEntity.setUserEmailId("test@example.com");
        eventsEntity.setEventId("event123");

        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(userEntity));
        when(eventsRepository.findById("event123")).thenReturn(Optional.of(eventsEntity));

        boolean result = userService.addEventIdToUserDataBase("test@example.com", "event123");

        assertFalse(result); 
        verify(userRepository, times(1)).findById("test@example.com");
        verify(eventsRepository, times(1)).findById("event123");
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    public void testGetEventIdFromUserDataBase() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmailId("test@example.com");

        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(userEntity));

        UserEntity result = userService.getEventIdFromUserDataBase("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getUserEmailId());
        verify(userRepository, times(1)).findById("test@example.com");
    }
}
