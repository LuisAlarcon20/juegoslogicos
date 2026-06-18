
package com.example.demo.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicTacToeController {
   private char[][] board = {
        {'-', '-', '-'},
        {'-', '-', '-'},
        {'-', '-', '-'}
    };

    private char currentPlayer = 'X';

    @GetMapping("/3enRaya")
    public String getTicTacToe() {
        return "tictactoe"; // Mapea a un archivo tictactoe.html en /resources/templates.
    }

    @GetMapping("/play")
    @ResponseBody
    public String play(@RequestParam int row, @RequestParam int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != '-') {
            return "Movimiento inválido";
        }
        board[row][col] = currentPlayer;
        if (checkWin(currentPlayer)) {
            char winner = currentPlayer;
            resetBoard();
            return "Jugador " + winner + " gana!";
        }
        if (isBoardFull()) {
            resetBoard();
            return "Es un empate!";
        }
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        return "Movimiento registrado";
    }
    @GetMapping("/board")
@ResponseBody
public char[][] getBoard() {
    return board; // Devuelve el tablero actual
}

    private boolean checkWin(char player) {
        // Verifica filas, columnas y diagonales
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 'X';
    }
}
