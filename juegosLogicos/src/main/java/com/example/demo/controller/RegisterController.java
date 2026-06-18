package com.example.demo.controller;

import com.example.demo.clases.user;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    // Mostrar el formulario de registro
    @GetMapping
    public String showRegisterForm(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "success", required = false) String success,
                                   @RequestParam(value = "mensajeError", required = false) String mensajeError,
                                   Model model) {

        if (error != null && error.equals("passwordsDoNotMatch")) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
        }

        if (success != null) {
            model.addAttribute("success", "¡Registro exitoso! Ahora puedes iniciar sesión.");
        }

        if (mensajeError != null) {
            model.addAttribute("mensajeError", mensajeError);
        }

        return "create"; // Retorna la vista "create.html"
    }

    // Registrar Usuario
    @PostMapping
    public String registerUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword,
            Model model) {

        try {
            // Validar que las contraseñas coincidan
            if (!password.equals(confirmPassword)) {
                return "redirect:/register?error=passwordsDoNotMatch"; // Redirigir con error si las contraseñas no coinciden
            }

            // Validar si el correo ya está registrado
        if (userService.findByEmail(email).isPresent()) {  // Verificación correcta usando isPresent()
            model.addAttribute("mensajeError", "Este correo ya está registrado.");
            return "create"; // Retorna a la vista de registro con el mensaje de error
        }

            // Validar si el nombre de usuario ya está registrado
            if (userService.findUserByUsername(username).isPresent()) {
                model.addAttribute("mensajeError", "Este nombre de usuario ya está registrado.");
                return "create"; // Retorna a la vista de registro con el mensaje de error
            }

            // Crear un nuevo usuario
            user newUser = new user();
            newUser.setUsername(username);
            newUser.setEmail(email);

            // Aquí ya no encriptamos la contraseña
            newUser.setPassword(password); // Guardamos la contraseña tal como está

            // Guardar el usuario en la base de datos
            userService.saveUser(newUser);

            // Redirigir a la página de inicio de sesión con mensaje de éxito
            return "redirect:/login?success";

        } catch (Exception e) { 
            // Log de la excepción
            e.printStackTrace(); // Esto imprimirá la traza completa de la excepción en la consola

            // Mostrar el mensaje de error en la vista
            model.addAttribute("mensajeError", "Hubo un problema al registrar al usuario: " + e.getMessage());
            return "create"; // Retorna a la vista de registro con el mensaje de error
        }
    }
}
