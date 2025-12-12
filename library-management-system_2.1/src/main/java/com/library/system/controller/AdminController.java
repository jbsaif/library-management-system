package com.library.system.controller;

import com.library.system.service.BorrowService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
// Ensure only users with ROLE_ADMIN can access any endpoint in this controller
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final BorrowService borrowService;

    public AdminController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    // Handles the request for the admin loan report page
    @GetMapping("/loans")
    public String viewActiveLoans(Model model) {

        // Retrieve all active (non-returned) borrow records
        model.addAttribute("activeLoans", borrowService.findAllActiveBorrowRecords());

        // Used for conditional styling in the HTML
        model.addAttribute("today", LocalDate.now());

        model.addAttribute("title", "Active Loans Report");

        // Maps to src/main/resources/templates/admin/loans.html
        return "admin/loans";
    }
}
