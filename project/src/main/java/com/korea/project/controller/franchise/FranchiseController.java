package com.korea.project.controller.franchise;

import com.korea.project.service.franchise.FranchiseService;
import com.korea.project.vo.franchise.FranchiseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "/franchise/franchiseList";
    }

    @GetMapping("/franchisesBySector")
    public String getFranchisesBySector(@RequestParam(name = "sector", required = false) Integer sector, Model model) {
        List<FranchiseVO> franchises;

        if (sector != null) {
            franchises = franchiseService.getFranchisesBySector(sector);
        } else {
            franchises = franchiseService.getAllFranchises();
        }

        model.addAttribute("franchises", franchises);
        return "/franchise/franchiseList";
    }
}
