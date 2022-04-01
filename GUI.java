import java.awt.*;
import javax.swing.JDialog;

/**
 * Main GUI for the game.
 * Creates and manages a 2D array of rectangles representing the cells of the board.
 * Updates frame and panels as plays are made.
 * Paints win, loss, and tie frames when conditions are met.
 */
public class GUI extends Canvas {
    
    public final int gridSize = 200;
    public final Color oSquare, xSquare;
    GameModel model1;
    GUI display1;
    public final int rows = 3;
    public final int cols = 3;
    Rectangle [][] gridArray;

    /**
     * Constructs GUI object.
     * sets the size of each cell on the grid.
     * @param model1 main model for the game that controls the grid.
     */
    public GUI(GameModel model1){
        this.oSquare = Color.blue;
        this.xSquare = Color.red;
        this.model1 = model1;
        setSize(3*gridSize, 3*gridSize);
        createGrid();
    }

    /**
     * creates 3 by 3 grid of rectangle to represent the cells and is displayed to user.
     */
    void createGrid(){
        int w,h,x,y;
        w = gridSize-1;
        h = gridSize-1;
        this.gridArray = new Rectangle[rows][cols];
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                x = c*gridSize;
                y = r*gridSize;
                Rectangle rectangle = new Rectangle(x,y,w,h);
                gridArray[r][c] = rectangle;
            }
        }
    }

    /**
     * Getter method to get rectangle representing a given cell.
     * @param r row for rectangle to be retrieved
     * @param c column for rectangle to be retrieved
     * @return returns rectangle at given position
     */
    public Rectangle getRect(int r, int c){
        return gridArray[r][c];
    }

    /**
     * Overrides paint method to paint color of the cell(s).
     */
    @Override
    public void paint(Graphics g){
        Rectangle rectangle;
        int state;
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                rectangle = getRect(r,c);
                state = model1.getCellState(r,c);
                switch(state){
                    case 1:
                        g.setColor(oSquare);
                    break;
                    case 2:
                        g.setColor(xSquare);
                    break;
                    default:
                        g.setColor(Color.gray);
                    break;
                }                          
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
    }
    
    /**
     * finds cell that was selected based on mouse click location.
     * @param p point where mouse click occurred
     * @return Returns point with row and column of selected cell.
     */
    public Point getCellAtLocation(Point p){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(getRect(r,c).contains(p)){
                    return new Point(r,c);
                }
            }
        }
    return null;
    }

    /**
     * paints new JDialog box alerting the user they have won
     */
    public void paintWinScreen(){
        JDialog winScreen;
        Frame frame2 = new Frame();
        winScreen = new JDialog(frame2, "Game Over!", true);
        winScreen.add(new Label("You won!"));
        winScreen.setSize(200,200);
        winScreen.setLayout(new FlowLayout());
        winScreen.setVisible(true);
    }

    /**
     * paints new JDialog box alerting the user they have lost.
     */
    public void paintLoseScreen(){
        JDialog winScreen;
        Frame frame2 = new Frame();
        winScreen = new JDialog(frame2, "Game Over!", true);
        winScreen.add(new Label("You lost!"));
        winScreen.setSize(200,200);
        winScreen.setLayout(new FlowLayout());
        winScreen.setVisible(true);
    }

    /**
     * paints new JDialog box alerting the user the game has tied.
     */
    public void paintTieScreen(){
        JDialog winScreen;
        Frame frame2 = new Frame();
        winScreen = new JDialog(frame2, "Game Over!", true);
        winScreen.add(new Label("You Tied!"));
        winScreen.setSize(200,200);
        winScreen.setLayout(new FlowLayout());
        winScreen.setVisible(true);
    }
}
