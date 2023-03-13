public class Sudoku {

    Board board;

    public Sudoku(int initialValues) {
        board = new Board(initialValues);
    }

    public int findNextValue(Cell curr) {
        /*
         * Find the next value to try for a specific cell
         */

        for (int i = curr.getValue() + 1; i < 10; i++) {
            if (board.validValue(curr.getRow(), curr.getCol(), i)) {
                return i;
            }
        }

        return 0;
    }

    public Cell findNextCell() {
        /*
         * Find the next cell that seems fit
         */

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board.get(r, c).getValue() == 0) {
                    if (findNextValue(board.get(r, c)) != 0) {
                        board.get(r, c).setValue(findNextValue(board.get(r, c)));
                        return board.get(r, c);
                    } else {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    public boolean solve() {
        /*
         * Solve a given board
         */

        Stack<Cell> stack = new LinkedList<>();

        while (stack.size() < (board.getCol() * board.getRow()) - board.numberOfLockedCells()) {

            Cell next = findNextCell();

            while (next == null & stack.size() > 0) {
                Cell temp = stack.pop();
                int tempVal = findNextValue(temp);
                temp.setValue(tempVal);

                if (tempVal != 0) {
                    next = temp;
                }
            }

            if (next == null) {
                return false;
            } else {
                stack.push(next);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Sudoku gameSudoku = new Sudoku(10);
        System.out.println(gameSudoku.board);

        System.out.println(gameSudoku.solve());
        System.out.println(gameSudoku.board);
    }
}
