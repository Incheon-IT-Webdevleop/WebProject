package com.korea.project.controller.mapController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    @GetMapping("/map")
    public String showMap() {
        return "/map/MapViewer";
    }
}
