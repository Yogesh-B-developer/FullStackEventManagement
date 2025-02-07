package com.example.demo.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.AdminEntity;
import com.example.demo.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private AdminEntity adminEntity;

    @BeforeEach
    void setUp() {
        adminEntity = new AdminEntity();
        adminEntity.setEmailId("admin@example.com");
        adminEntity.setName("Admin Name");
        adminEntity.setPassword("password123");
    }

    @Test
    public void testFindAdmin() {
        when(adminRepository.findById(anyString())).thenReturn(Optional.of(adminEntity));

        AdminEntity result = adminService.findAdmin("admin@example.com");

        assertNotNull(result);
        assertEquals("Admin Name", result.getName());
        verify(adminRepository, times(1)).findById(anyString());
    }

    @Test
    public void testCheckForCorrectEmail() {
        when(adminRepository.findById(anyString())).thenReturn(Optional.of(adminEntity));

        boolean result = adminService.checkForCorrectEmail("admin@example.com");

        assertTrue(result);
        verify(adminRepository, times(1)).findById(anyString());
    }

    @Test
    public void testCheckForCorrectPassword() {
        when(adminRepository.findById(anyString())).thenReturn(Optional.of(adminEntity));

        String result = adminService.checkForCorrectPassword("admin@example.com", "password123");

        assertEquals("Admin Name", result);
        verify(adminRepository, times(1)).findById(anyString());
    }

    @Test
    public void testGetAdminNameFromDataBase() {
        when(adminRepository.findById(anyString())).thenReturn(Optional.of(adminEntity));

        String result = adminService.getAdminNameFromDataBase("admin@example.com");

        assertEquals("Admin Name", result);
        verify(adminRepository, times(1)).findById(anyString());
    }

    @Test
    public void testGetRole() {
        String result = adminService.getRole();

        assertNull(result);
    }
}
