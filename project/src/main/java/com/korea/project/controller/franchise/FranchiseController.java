package com.korea.project.controller.franchise;

import com.korea.project.service.franchise.FranchiseService;
import com.korea.project.vo.franchise.FranchiseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FranchiseController {
    private final FranchiseService franchiseService;

    @Autowired
    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @GetMapping("/franchises")
    public String getAllFranchises(Model model) {
        List<FranchiseVO> franchises = franchiseService.getAllFranchises();
        model.addAttribute("franchises", franchises);
        System.out.println("Franchises added to model: " + franchises.size());
        return "franchiseList";
    }
}