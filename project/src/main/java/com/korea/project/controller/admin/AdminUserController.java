package com.korea.project.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.korea.project.service.admin.AdminUserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;
    //유저 리스트 불러오기
    @GetMapping("/admin/user/list")
    public String listUsers(Model model) {
        model.addAttribute("users", adminUserService.getAllUsers());
        return "admin/adminUser/userlist";
    }
    // 유저 삭제 처리
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int userIdx, RedirectAttributes redirectAttributes) {
        boolean success = adminUserService.deleteUser(userIdx);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to delete user");
        }
        return "redirect:/admin/user/list";
    }
}
