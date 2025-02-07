package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity,String> {

}
