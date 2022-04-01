import java.awt.*;
import javax.swing.JFrame;

/**
 * GameController is the overhead of entire Tic Tac Toe game.
 * Initiates Model, GUI, and AI objects
 * Handles Button Presses and Displays game Frames.
 * 
 * @version 1.0 (3/31/2022)
 * @author Ryan Stroz
 * 
 */

public class GameController {
    JFrame frame1;
    GUI gui1;
    GameModel model1;
    ComputerAI ai;
    Button restartButton;

    /**
     * GameController constructor.
     * Creates GameModel to be used with GUI and other elements of the game.
     */
    public GameController(){
        model1 = new GameModel();
    }

    /**
     * Stars the game process.
     * Generates GUI and AI and allows the user to start playing
     */
    public void startGame(){
        gui1 = new GUI(model1);
        ai = new ComputerAI();        
        frame1 = new JFrame("Tic Tac Toe");
        frame1.setLayout(new FlowLayout());
        frame1.setBackground(Color.yellow);  
        GameListener listener1 = new GameListener(this);
        gui1.addMouseListener(listener1);
        frame1.add(gui1);
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        restartButton = new Button("RESTART GAME");
        restartButton.addActionListener(listener1);
        buttons.add(restartButton);
        frame1.add(buttons);
        frame1.pack();
        frame1.setVisible(true);
    }

    /**
     * Handles actions after mouse is clicked. 
     * When clicking on a valid, non-used cell the cell will be activated in the player color.
     * Also calls game AI to make a move.      
     * @param p Point on the frame where the mouse click occurred
     */
    public void mouseClick(Point p){
        model1.gameInProgress = true;
        Point point = gui1.getCellAtLocation(p);
        if(model1.isCellUsed(point.x, point.y) == false && ai.computerPlaying == false){
            model1.toggleCellBlue(point.x, point.y);
            gui1.repaint();
            model1.checkGameOver(gui1);
            ai.makeMove(model1, gui1, ai);
        }
    } 

    /**
     * Handles actions after restart button is pressed.
     * restarts game to original state allowing user to play again.     
     */
    public void restartButtonPress(){
        model1.toggleAllCellEmpty();
        model1.gameOver = false;
        model1.gameInProgress = false;
        ai.computerPlaying = false;
        gui1.repaint();
    }

    /**
     * Main method that constructs new GameController object and starts the program.
     *  
     */
    public static void main(String [] args){
        GameController controller = new GameController();
        controller.startGame();
    }
}
