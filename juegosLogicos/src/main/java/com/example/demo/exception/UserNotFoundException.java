

package com.example.demo.exception;  // Asegúrate de que esté en un paquete adecuado

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);  // Llama al constructor de la clase base (RuntimeException)
    }
}
