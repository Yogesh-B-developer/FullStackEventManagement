package com.example.demo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.EventsEntity;
import com.example.demo.service.EventsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminEventManagementController {

    private static final Logger log = LoggerFactory.getLogger(AdminEventManagementController.class);

    @Autowired
    EventsService eventsService;

    // for updating event when admin clicks update button
    @PostMapping("/update/{id}")
    public String updateItemById(@PathVariable String id, Model model) {
        log.debug("Updating event with ID: {}", id);
        model.addAttribute("idOfRow", id);
        EventsEntity eventsEntity = eventsService.getEventById(id);
        model.addAttribute("eventToBeUpdated", eventsEntity);
        log.info("Event to be updated: {}", eventsEntity);
        return "adminupdatevent";
    }

    @PostMapping("/confirmupdate/{id}")
    public String updateConfirmById(@PathVariable String id, @RequestParam("eventArea") String eventArea, @RequestParam("eventPrice") String eventPrice) {
        log.debug("Confirming update for event ID: {}, Area: {}, Price: {}", id, eventArea, eventPrice);
        eventsService.updateEventById(id, eventArea, eventPrice);
        log.info("Event updated successfully with ID: {}", id);
        return "redirect:/adminevents";
    }

    // for deleting event when admin clicks delete button of particular event
    @PostMapping("/delete/{id}")
    public String deletById(@PathVariable String id) {
        log.debug("Deleting event with ID: {}", id);
        eventsService.deleteEventById(id);
        log.info("Event deleted successfully with ID: {}", id);
        return "redirect:/adminevents";
    }

    // for adding events to database after submission of form
    @PostMapping("confirm/addproducts")
    public String confirmAddEventsToDataBase(@ModelAttribute EventsEntity eventsEntity) {
        log.debug("Adding new event: {}", eventsEntity);
        eventsService.addEventsToDataBase(eventsEntity);
        log.info("Event added successfully: {}", eventsEntity);
        return "redirect:/adminevents";
    }
}
