package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.EventsEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.AdminService;
import com.example.demo.service.EventsService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    EventsService eventsService;

    @GetMapping("/")
    public String homePage() {
        log.debug("Accessing home page");
        return "index";
    }

    // for admin event page where he can perform CRUD operations
    @GetMapping("/adminevents")
    public String adminEventPage(Model model) {
        log.debug("Accessing admin event page");
        List<EventsEntity> allUserEventsInDatabase = userService.getAllEventsForUserPage();
        model.addAttribute("allUserEventsInDatabase", allUserEventsInDatabase);
        log.info("Loaded {} events for admin event page", allUserEventsInDatabase.size());
        return "adminevents";
    }

    @GetMapping("/login")
    public String loginPage() {
        log.debug("Accessing login page");
        return "login";
    }

    // for checking the login details of admin and redirect to admin profile if details are correct
    @PostMapping("/check")
    public String loginSuccessOrNot(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String email) {
        String adminOrUser = request.getParameter("adminOrUser");
        log.debug("Login attempt with email: {}", email);

        if ("admin".equalsIgnoreCase(adminOrUser)) {
            String emailFromClient = request.getParameter("email");
            String password = request.getParameter("password");

            boolean adminEmailTrue = adminService.checkForCorrectEmail(emailFromClient);
            String adminPasswordTrue = adminService.checkForCorrectPassword(emailFromClient, password);
            String adminName = "";
            boolean passwordTrueOrFalse = true;
            if ("false".equals(adminPasswordTrue)) {
                passwordTrueOrFalse = false;
            } else {
                adminName += adminPasswordTrue;
            }
            if (adminEmailTrue && passwordTrueOrFalse) {
                session.setAttribute("adminName", adminName);
                session.setAttribute("adminEmail", emailFromClient);
                log.info("Login successful for admin: {}", emailFromClient);
                return "redirect:/admin/profile";
            } else if (!adminEmailTrue) {
                redirectAttributes.addFlashAttribute("adminEmailError", "Admin email is wrong");
                log.warn("Login failed - incorrect email: {}", emailFromClient);
                return "redirect:/login";
            } else if (!passwordTrueOrFalse) {
                redirectAttributes.addFlashAttribute("adminPassowrdError", "Password is wrong");
                log.warn("Login failed - incorrect password for email: {}", emailFromClient);
                return "redirect:/login";
            }
        }
        log.warn("Login failed - user not recognized: {}", email);
        return "login";
    }

    @GetMapping("/admin/profile")
    public String getAdminProfile(HttpSession session) {
        String adminEmail = (String) session.getAttribute("adminEmail");
        log.debug("Fetching profile for admin: {}", adminEmail);
        AdminEntity admin = adminService.findAdmin(adminEmail);
        session.setAttribute("adminName", admin.getName());
        session.setAttribute("adminPhoneNumber", admin.getPhoneNumber());
        session.setAttribute("adminRole", admin.getRole());
        log.info("Admin profile loaded for: {}", adminEmail);
        return "adminprofile";
    }

    @GetMapping("/admin/admindashboard")
    public String getAdminDashBoard() {
        log.debug("Accessing admin dashboard");
        return "admin-dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Logging out");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        log.info("User logged out");
        return "redirect:/login";
    }

    @GetMapping("/addevents")
    public String addEventsToDataBase() {
        log.debug("Accessing add events page");
        return "adminproductsform";
    }

    // for user signUp folder direction
    @GetMapping("/signup")
    public String userSignUpPage() {
        log.debug("Accessing user signup page");
        return "usersignup";
    }

    // after clicking signup button
    @PostMapping("/signup/details")
    public String afterUserSignUp(@ModelAttribute UserEntity userEntity) {
        log.debug("User signup details received for: {}", userEntity.getUserEmailId());
        log.info("User signed up: {}", userEntity);
        return "login";
    }

    @GetMapping("/basicevents")
    public String basicEventsPage(Model model) {
        log.debug("Accessing basic events page");
        List<EventsEntity> allAdminEventsInDatabase = eventsService.getAllEventsForAdminPage();
        model.addAttribute("basicEventsInDatabase", allAdminEventsInDatabase);
        log.info("Loaded {} basic events", allAdminEventsInDatabase.size());
        return "basicevents";
    }

    // for directing to user login page
    @GetMapping("/userlogin")
    public String getUserLoginPage() {
        log.debug("Accessing user login page");
        return "userlogin";
    }

    // for directing to user sign up page
    @GetMapping("/usersignup")
    public String getUserSignUpPage() {
        log.debug("Accessing user sign up page");
        return "usersignup";
    }
}
