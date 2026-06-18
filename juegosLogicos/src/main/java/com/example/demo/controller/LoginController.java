package com.example.demo.controller;

import com.example.demo.clases.user;
import com.example.demo.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    // Mostrar el formulario de login
    @GetMapping
    public String showLoginForm() {
        return "login"; // Retorna la vista "login.html"
    }

    // Validar el login del usuario
    @PostMapping("/validate")
public String validateLogin(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
    
    // Buscar al usuario por correo
    Optional<user> foundUser = userService.findByEmail(email);
    
    // Verificar si el usuario existe y si la contraseña coincide
    if (foundUser.isPresent()) {
        user user = foundUser.get();  // Obtener el usuario
        if (user.getPassword().equals(password)) {
            return "redirect:/principal";  // Redirigir a la página principal si el login es correcto
        } else {
            model.addAttribute("error", "Contraseña incorrecta. Intenta de nuevo.");
        }
    } else {
        model.addAttribute("error", "Usuario no encontrado. Intenta de nuevo.");
    }

    // Si el login no es correcto, mostramos nuevamente el formulario
    return "login";  // Mostrar nuevamente la vista de login con mensaje de error
}
}