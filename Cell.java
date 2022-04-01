/**
 * One cell of the tic tac toe grid.
 * Cell only knows its own state and does not interact with any other cells next to it
 */
public class Cell {

    public static final String[] colorString = {"empty","Blue","Red"};
    int color;
    boolean isUsed;

    /**
     * Constructs cell and sets values to default color and unused state.
     */
    public Cell(){
        color = 0;
        isUsed = false;
    }

    /**
     * Changes color of the cell and sets the state to used.
     * @param newColor color for the cell to be changed to. 0 for default, 1 for blue, 2 for red.
     */
    public void setColor(int newColor){
        if(isUsed == false){
            isUsed = true;
            color = newColor;
        }
    }

    /**
     * Sets cell color to default color and sets cell to unused state.
     */
    public void setEmpty(){
        color = 0;
        isUsed = false;
    }

    /**
     * Sets cell to used state without changing color
     */
    public void setUsed(){
        isUsed = true;
    }

    /**
     * Getter method to find color of cell
     * @return returns 0 for default, 1 for blue, 2 for red.
     */
    public int getColor(){
        if(isUsed == true){
            return color;
        }
        return -1;
    }

    /**
     * Converts cell color to a string variable.
     */
    public String toString(){
        return colorString[color];
    }
}
