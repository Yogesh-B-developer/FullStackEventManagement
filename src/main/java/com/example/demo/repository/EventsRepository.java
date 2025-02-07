package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.EventsEntity;

public interface EventsRepository extends JpaRepository<EventsEntity,String> {

}
