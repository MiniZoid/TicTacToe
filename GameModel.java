/**
 * GameModel controls the game grid and checks conditions on if a win, loss, or tie has occurred.
 * This class handles calls to the grid to adjust specific cells.
 */

public class GameModel {
    Grid grid;
    boolean gameOver = false;
    boolean gameInProgress = false;

    /**
     * GameModel1 constructor. Makes and stores a grid object.
     */
    public GameModel(){
        grid = new Grid();
    }
    
    /**
     * sets target cell on the grid to Blue (player color)
     * @param r row for target cell
     * @param c column for target cell
     */
    public void toggleCellBlue(int r, int c){
        grid.setCellToBlue(r, c);
    }
    
    /**
     * sets target cell on the grid to red (AI color)
     * @param ai ai that is making the current move
     * @param r row for target cell
     * @param c column for target cell
     */
    public void toggleCellRed(ComputerAI ai, int r, int c){
        grid.setCellToRed(ai, r, c);
    }

    /**
     * checks if cell at location is used. 
     * @param r row for target cell
     * @param c column for target cell
     * @return returns 0 if cell is empty, returns 1 if cell is used by user, returns 2 if cell is used by AI
     */
    public int getCellState(int r, int c){
        return grid.getCurrentState(r,c);
    }


    /**
     * checks if cell has been used by user or AI.
     * @param r row for target cell
     * @param c column for target cell
     * @return true if used, false if unused
     */
    public boolean isCellUsed(int r, int c){
        return grid.isCellUsed(r, c);
    }

    /**
     * sets all cells to default color and unused state.
     */
    public void toggleAllCellEmpty(){
        grid.setAllCellsEmpty();
    }

    /**
     * sets all cell to used state without changing color.
     */
    public void toggleAllCellUsed(){
        grid.setAllCellsUsed();
    }

    /**
     * Checks all conditions if the game has ended
     * @param gui1 game gui to display win, loss, or tie screen.
     */
    public void checkGameOver(GUI gui1){
        if(grid.checkWin(1) == 1 && gameOver == false){
            gui1.paintWinScreen();
            toggleAllCellUsed();
            gameOver = true;
        }
        else if(grid.checkWin(2) == 2 && gameOver == false){
            gui1.paintLoseScreen();
            toggleAllCellUsed();
            gameOver = true;
        }
        else if(checkTie() == true && gameOver == false){
            gui1.paintTieScreen();
            toggleAllCellUsed();
            gameOver = true;
        }
        return;
    }

    /**
     * 
     * @param n takes int based on player that it is checking. 1 for User, 2 for AI.
     * @return returns parameter if that player has one.
     */
    public int checkWin(int n){
        if(grid.checkWin(n) == n){
            return n;
        }
        return -1;
    }
    
    /**
     * checks if no win condition has been met and all cells have been used.
     * @return returns true if game has tied.
     */
    public boolean checkTie(){
        if(checkWin(1) != 1 && checkWin(2) != 2 && grid.totalCellsUsed() >= 9){
            return true;
        }
        return false;
    }
}
