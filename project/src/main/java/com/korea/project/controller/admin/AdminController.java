

package com.korea.project.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin/login";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/home";
    }
}
