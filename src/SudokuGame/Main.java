package SudokuGame;

import java.util.Scanner;

/* Solução para sodoku padrão:
 
2 8 4 | 1 3 7 | 9 6 5
6 1 9 | 2 4 5 | 8 7 3
5 3 7 | 6 9 8 | 2 4 1
------+-------+-------
4 7 6 | 5 1 9 | 3 8 2
3 2 1 | 7 8 6 | 5 9 4
8 9 5 | 4 2 3 | 6 1 7
------+-------+-------
9 4 3 | 8 7 2 | 1 5 6
1 6 8 | 3 5 4 | 7 2 9
7 5 2 | 9 6 1 | 4 3 8
 
*/

public class Main {
    public static void main(String[] args) {
        // Sudoku padrão para testes
        String[] initialValues = {
        		"0,0,0,1,0,7,9,6,0",
                "6,1,9,2,4,0,0,7,3",
                "0,0,7,6,0,8,0,4,0",
                "0,7,0,5,0,0,3,0,0",
                "3,2,0,0,8,0,5,9,0",
                "0,9,5,4,2,3,0,1,0",
                "9,4,3,8,0,2,1,5,6",
                "0,0,8,0,0,0,0,2,0",
                "7,5,2,9,0,0,4,0,0"

        };
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja usar o Sudoku padrão ou gerar um tabuleiro aleatório?");
        System.out.println("1 - Sudoku Padrão (para testes)");
        System.out.println("2 - Gerar Tabuleiro Aleatório");
        System.out.print("Escolha uma opção: ");
        int choice = scanner.nextInt();

        
        if (choice == 1) {
            System.out.println("Usando Sudoku padrão...");
        } else if (choice == 2) {
            System.out.println("Gerando tabuleiro aleatório...");
            initialValues = null; 
        } else {
            System.out.println("Opção inválida. Gerando tabuleiro aleatório por padrão...");
            initialValues = null; 
        }

        
        Game game = new Game(initialValues);
        game.start();
    }
}
