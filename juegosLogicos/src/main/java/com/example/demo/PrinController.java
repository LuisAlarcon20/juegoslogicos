                        package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrinController {
    @GetMapping("/ingreso")
    public String login() {
        return "login"; // Esto debería hacer que se busque login.html en src/main/resources/templates/
    }
    
    @GetMapping("/principal")
    public String home() {
        return "INDEX";
    }
    
    @GetMapping("/crear")
    public String crear() {
        return "create";
    }
    
    @GetMapping("/privacidad")
    public String privacidad() {
        return "politicas";
    }
    
    @GetMapping("/Gato Juego")
    public String cal() {
        return "tictactoe";
    }
}
