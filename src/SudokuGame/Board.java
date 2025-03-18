package SudokuGame;

import java.util.Random;

public class Board {
    private int[][] grid; 
    private boolean[][] isFixed; 

    // Construtor para inicializar com valores iniciais
    public Board(String[] initialValues) {
        grid = new int[9][9];
        isFixed = new boolean[9][9];
        if (initialValues != null && initialValues.length == 9) {
            initializeBoard(initialValues);
        } else {
            // Se initialValues for inválido, gera um tabuleiro aleatório
            generateRandomBoard();
        }
    }

    // Construtor sem parâmetros
    public Board() {
        grid = new int[9][9];
        isFixed = new boolean[9][9];
        generateRandomBoard(); 
    }

    // Inicializa o tabuleiro com os valores iniciais
    private void initializeBoard(String[] initialValues) {
        for (int i = 0; i < 9; i++) {
            String[] values = initialValues[i].split(",");
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(values[j]);
                if (grid[i][j] != 0) {
                    isFixed[i][j] = true; 
                }
            }
        }
    }

    // Exibe o tabuleiro no terminal com o template desejado
    public void display() {
        System.out.println("************************");
        System.out.println("   0 1 2   3 4 5   6 7 8");
        System.out.println("************************");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println(" |-------+-------+-------");
            }
            System.out.print(i + "| ");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(grid[i][j] == 0 ? "." : grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("************************");
    }

    // Coloca um número no tabuleiro (se a célula não for fixa)
    public void placeNumber(int row, int col, int num) {
        // Verifica se a célula já contém um número
        if (grid[row][col] != 0) {
            System.out.println("Erro: Esta célula já contém um número. Use a opção 3 para remover o número antes de adicionar um novo.");
            return; 
        }
        
        if (isValid(row, col, num)) {
            grid[row][col] = num;
            System.out.println("Número " + num + " colocado na posição (" + row + "," + col + ").");
        } else {
            System.out.println("Erro: O número " + num + " não pode ser colocado na posição (" + row + "," + col + ").");
        }
    }

    // Remove um número do tabuleiro (se a célula não for fixa)
    public void removeNumber(int row, int col) {
        if (!isFixed[row][col]) {
            grid[row][col] = 0;
            System.out.println("Número removido da posição (" + row + "," + col + ").");
        } else {
            System.out.println("Erro: Esta posição é fixa e não pode ser alterada.");
        }
    }

    // Limpa apenas os números inseridos pelo usuário
    public void clearUserNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isFixed[i][j]) {
                    grid[i][j] = 0;
                }
            }
        }
        System.out.println("Números do usuário removidos. Valores fixos mantidos.");
    }

    // Verifica se o jogo está completo
    public boolean isComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Verifica se há erros no tabuleiro
    public boolean hasErrors() {
        // Verifica linhas, colunas e subgrades 3x3
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != 0 && !isValid(i, j, grid[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Verifica se um número é válido em uma determinada posição
    private boolean isValid(int row, int col, int num) {
        // Verifica a linha
        for (int i = 0; i < 9; i++) {
            if (i != col && grid[row][i] == num) {
                return false;
            }
        }

        // Verifica a coluna
        for (int i = 0; i < 9; i++) {
            if (i != row && grid[i][col] == num) {
                return false;
            }
        }

        // Verifica a subgrade 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (i != row && j != col && grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Gera um novo tabuleiro aleatório
    public void generateRandomBoard() {
        Random random = new Random();
        grid = new int[9][9];
        isFixed = new boolean[9][9];

        // Preenche o tabuleiro com números válidos
        solveRandomly(random);

        // Remove alguns números para criar um jogo jogável
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (random.nextDouble() < 0.5) {
                    grid[i][j] = 0;
                    isFixed[i][j] = false;
                } else {
                    isFixed[i][j] = true;
                }
            }
        }
    }

    // Resolve o tabuleiro aleatoriamente (usado para gerar um tabuleiro válido)
    private boolean solveRandomly(Random random) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        int randomNum = random.nextInt(9) + 1;
                        if (isValid(i, j, randomNum)) {
                            grid[i][j] = randomNum;
                            if (solveRandomly(random)) {
                                return true;
                            }
                            grid[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
