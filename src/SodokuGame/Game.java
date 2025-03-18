package SodokuGame;

import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;

    // Construtor para inicializar com um tabuleiro personalizado
    public Game(String[] initialValues) {
        if (initialValues == null || initialValues.length == 0) {
            // Se nenhum tabuleiro for fornecido, gera um aleatório
            board = new Board();
            board.generateRandomBoard();
        } else {            
            board = new Board(initialValues);
        }
        scanner = new Scanner(System.in);
    }

    // Construtor sem argumentos para gerar um tabuleiro aleatório automaticamente
    public Game() {
        this(null); // Chama o outro construtor com null para gerar um tabuleiro aleatório
    }

    public void start() {
        while (true) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                handleChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número entre 1 e 8.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Iniciar um novo jogo");
        System.out.println("2. Colocar um novo número");
        System.out.println("3. Remover um número");
        System.out.println("4. Verificar jogo");
        System.out.println("5. Verificar status do jogo");
        System.out.println("6. Limpar");
        System.out.println("7. Finalizar o jogo");
        System.out.println("8. Gerar novo tabuleiro aleatório");
        System.out.print("Escolha uma opção: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                board.display();
                break;
            case 2:
                placeNumber();
                break;
            case 3:
                removeNumber();
                break;
            case 4:
                board.display();
                break;
            case 5:
                printGameStatus();
                break;
            case 6:
                board.clearUserNumbers();
                board.display();
                break;
            case 7:
                finishGame();
                break;
            case 8:
                board.generateRandomBoard();
                System.out.println("Novo tabuleiro gerado!");
                board.display();
                break;
            default:
                System.out.println("Opção inválida. Escolha um número entre 1 e 8.");
        }
    }

    private void placeNumber() {
        System.out.print("Digite a linha (0-8), coluna (0-8) e número (1-9) separados por vírgula (ex: 0,0,1): ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        if (parts.length == 3) {
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                int num = Integer.parseInt(parts[2]);
                if (row < 0 || row > 8 || col < 0 || col > 8 || num < 1 || num > 9) {
                    System.out.println("Entrada inválida. Certifique-se de que a linha e coluna estão entre 0 e 8 e o número entre 1 e 9.");
                } else {
                    board.placeNumber(row, col, num);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar números válidos.");
            }
        } else {
            System.out.println("Formato inválido. Use o formato: linha,coluna,número (ex: 0,0,1)");
        }
    }

    private void removeNumber() {
        System.out.print("Digite a linha (0-8), coluna (0-8): ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        if (parts.length == 2) {
            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                if (row < 0 || row > 8 || col < 0 || col > 8) {
                    System.out.println("Entrada inválida. Certifique-se de que a linha e coluna estão entre 0 e 8.");
                } else {
                    board.removeNumber(row, col);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Certifique-se de digitar números válidos.");
            }
        } else {
            System.out.println("Formato inválido. Use o formato: linha,coluna (ex: 0,0)");
        }
    }

    private void printGameStatus() {
        if (board.isComplete()) {
            if (board.hasErrors()) {
                System.out.println("Jogo completo, mas com erros.");
            } else {
                System.out.println("Jogo completo e sem erros!");
            }
        } else {
            if (board.hasErrors()) {
                System.out.println("Jogo incompleto e com erros.");
            } else {
                System.out.println("Jogo incompleto e sem erros.");
            }
        }
    }

    private void finishGame() {
        if (board.isComplete() && !board.hasErrors()) {
            System.out.println("Parabéns! Você completou o jogo corretamente.");
        } else {
            if (!board.isComplete()) {
                System.out.println("O jogo ainda não está completo. Continue tentando!");
            }
            if (board.hasErrors()) {
                System.out.println("O jogo contém erros. Corrija-os e tente novamente!");
            }
        }
        
        System.out.println("Obrigado por jogar! Até a próxima.");
        scanner.close();
        System.exit(0); 
    }
}
