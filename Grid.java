/**
 * Creates and manages a 2D array of cells to represent the game board.
 * Handles state of cells and computes weather or not a win condition has been met
 */

public class Grid{
    
    Cell [][] cells;
    final int rows = 3;
    final int cols = 3;

    /**
     * Constructor that makes a 2D array of cells to represent the 3 by 3 grid.
     */
    public Grid(){
        cells = new Cell[rows][cols];
        Cell cell;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                cell = new Cell();
                cells[r][c] = cell;                
            }
        }
    }

    /**
     * Checks to see if the given row and column are playable cells on the grid.
     * @param r row to be checked for validity
     * @param c column to be checked for validity
     * @return returns true if cell is playable
     */
    public boolean isValid(int r, int c){
        return(r>=0 && c>=0 && r<rows && c<cols);
    }

    /**
     * Checks if the given cell is used by the user or AI
     * @param r row to be checked
     * @param c column to be checked
     * @return returns true if cell is used.
     */
    public boolean isCellUsed(int r, int c){
        return(cells[r][c].isUsed);
    }

    /**
     * calculates total number of cells on the grid used by the user and AI.
     * @return int representing number of cells used.
     */
    public int totalCellsUsed(){
        int usedCellCount = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(isCellUsed(r, c) == true){
                    usedCellCount++;
                }
            }
        }
        return usedCellCount;
    }

    /**
     * Checks if given cell is unused, or used by the player or AI.
     * @param r row position to be checked.
     * @param c column position to be checked.
     * @return Returns 0 if unused, returns 1 if used by user, returns 2 if used by AI.
     */
    public int getCurrentState(int r, int c){
        Cell cell;
        int state = -1;
        if(isValid(r,c)){
            cell = cells[r][c];
            state = cell.getColor();
        }
        return state;

    }

    /**
     * Getter method to return cell at given location
     * @param r row position to be returned
     * @param c column position to be returned 
     * @return returns cell at given row and column
     */
    public Cell getCell(int r,int c){
        if(isValid(r,c)){
            return cells[r][c];
        }
    return null;
    }

    /**
     * Changes a given cell to blue (User's color)
     * @param r row of cell to be changed
     * @param c column of cell to be changed.
     */
    public void setCellToBlue(int r, int c){
        Cell thisCell;
        if(isValid(r,c)){
            if(isCellUsed(r,c) == false){
                thisCell = getCell(r,c);
                thisCell.setColor(1);
            }
        }
    }

    /**
     * Changes given cell to Red (AI's color)
     * if chosen cell is used AI will be called to recompute play.
     * @param ai the game's AI
     * @param r row of cell to be changed
     * @param c column of cell to be changed
     */
    public void setCellToRed(ComputerAI ai, int r, int c){
        Cell thisCell;
        if(isValid(r,c)){
            if(isCellUsed(r,c) == false){
                thisCell = getCell(r,c);
                thisCell.setColor(2);
            }
            else if(totalCellsUsed() < 9){
                r = ai.recomputePlay();
                c = ai.recomputePlay();
                setCellToRed(ai, r, c);
            }
        }
    }

    /**
     * Sets all cells to default color and unused state
     */
    public void setAllCellsEmpty(){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                cells[r][c].setEmpty();
            }
        }
    }

    /**
     * sets all cells to used state without changing color.
     */
    public void setAllCellsUsed(){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                cells[r][c].setUsed();
            }   
        }
    }  

    /**
     * 
     * @param n int representing player to be checked. 1 for User, 2 for AI
     * @return returns n if player has won or -1 if no winner has been found.
     */
    public int checkWin(int n){
        int [][] winCheck;
        winCheck = new int [rows][cols];
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                winCheck[r][c] = getCurrentState(r,c);
            }
        }
        if(
            winCheck[0][0] == n && winCheck[0][1] == n && winCheck[0][2] == n || //top left to top right

            winCheck[0][0] == n && winCheck[1][0] == n && winCheck[2][0] == n || //top left to bottom right

            winCheck[1][0] == n && winCheck[1][1] == n && winCheck[1][2] == n || //middle left to middle right

            winCheck[2][0] == n && winCheck[2][1] == n && winCheck[2][2] == n || //bottom left to bottom right

            winCheck[0][1] == n && winCheck[1][1] == n && winCheck[2][1] == n || //top middle to bottom middle

            winCheck[0][2] == n && winCheck[1][2] == n && winCheck[2][2] == n || //top right to bottom right

            winCheck[0][0] == n && winCheck[1][1] == n && winCheck[2][2] == n || //diagonal, top left to bottom right
            
            winCheck[0][2] == n && winCheck[1][1] == n && winCheck[2][0] == n   //diagonal, bottom left to top right
        ){
            return n;
        }
        return -1;    
    }
}
