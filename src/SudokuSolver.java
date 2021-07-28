public class SudokuSolver {

    // size of grid as a CONSTANT
    private static final int GRID_SIZE = 9;


    public static void main(String[] args) {

        // starting sudoku board
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3},
        };

        printBoard(board);

        if(solveBoard(board)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++) {
            if(row % 3 == 0 && row != 0) {   // % 3, because of every 3rd row
                System.out.println("-----------");
            }
            for(int column = 0; column < GRID_SIZE; column++) {
                if(column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println(); // just prints a new line
        }
    }


    // creating helper methods
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column){
        for(int i = 0; i < GRID_SIZE; i++) {
            if(board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        // finding top left location in current box
        int localBoxRow = row - row % 3; // one minus (one divided by 3 is zero, remainder one): 1-1 = 0
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++) {
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if(board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    // creating one method that checks all three: is Number in Row, Column and Box
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for(int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) { // next: loop through all rows and columns to try one of them (1-9)
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry; //if valid, place number there

                            // recursion, if number already exists
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0; // will be set back to 0, if no number is valid any more and try another number
                            }
                        }

                    }
                    return false; // if none of a number can be set any more, not be solved any further
                }
            }
        }
    return true;
    }
}
