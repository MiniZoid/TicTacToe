import java.awt.*;
import java.awt.event.*;

/**
 * Tracks and creates actions based on mouse clicks on the screen.
 */
public class GameListener extends MouseAdapter implements ActionListener {
    private GameController controller;

    /**
     * Constructor for GameListener.
     * @param controller main controller for the game
     */
    public GameListener(GameController controller){
        this.controller = controller;        
    }

    /**
     * Tracks when and where a mouse click occurred and starts actions based on the event.
     */
    public void mouseClicked(MouseEvent event){
        Point p = event.getPoint();
        controller.mouseClick(p);
    }

    /**
     * Tracks when a button click has occurred and starts actions based on event.
     */
    public void actionPerformed(ActionEvent event){
        controller.restartButtonPress();
    }
}
