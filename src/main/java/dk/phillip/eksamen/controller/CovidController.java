package dk.phillip.eksamen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CovidController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/updateParish")
    public String updateParish(){
        return "updateParish";
    }

    @GetMapping("/showCommune")
    public String showCommune(){
        return "showCommune";
    }
}
