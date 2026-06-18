package com.example.demo.service;

import com.example.demo.clases.user;
import com.example.demo.repository.Repository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private Repository userRepository;  // El repositorio correcto

    public boolean validateUserByEmail(String email, String password) {
    // Usamos Optional para manejar el resultado de findByEmail
    Optional<user> optionalUser = userRepository.findByEmail(email);

    // Comprobamos si el usuario existe en la base de datos
    if (optionalUser.isPresent()) {
        user existingUser = optionalUser.get();  // Obtener el usuario si está presente
        return password.equals(existingUser.getPassword());
    } else {
        return false;  // Si no está presente, retornamos false
    }
}


    // Buscar usuario por nombre de usuario
    public Optional<user> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<user> findByEmail(String email) {
    return userRepository.findByEmail(email.trim().toLowerCase());  // Normaliza el correo
}

    // Guardar un nuevo usuario en la base de datos
    public user saveUser(user newUser) {
        return userRepository.save(newUser);  // Guarda el usuario
    }

    // Otros métodos según sea necesario
}
