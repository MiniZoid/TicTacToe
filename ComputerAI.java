import java.util.Random;

/**
 * This class handles the AI computations and moves.
 */
public class ComputerAI {

    int num1,num2;
    Random rand = new Random();
    boolean computerPlaying = false;

    /**
     * AI constructor. 
     * Creates AI and calls first play to be calculated
     */
    public ComputerAI(){
         computePlay();
    }

    /**
     * Computes a row and column to be used for first play.
     */
    public void computePlay(){
        num1 = randNum(0,3); 
        num2 = randNum(0,3); 
    }

    /**
     * Computes a random number between 0 and 3. 
     * @return returns recomputed number
     */
    public int recomputePlay(){
        return randNum(0,3);        
    }

    /**
     * picks a random number between a given range.
     * @param min min value to be chosen
     * @param max max value -1 to be chosen
     * @return
     */
    public int randNum(int min, int max){
        return rand.nextInt(max - min) + min;
    }

    /**
     * Creates a new thread for the AI to process the next move to be played. 
     * Toggles computerPlaying to true disallowing the user from playing.
     * Choses a cell location to play.
     * Repaints GUI to update new play.
     * Checks if win for AI has occurred.
     * Prints Exception if thread is interrupted.
     * @param model1 model for the game
     * @param gui1 GUI for the game
     * @param ai AI for the game
     */
    public void makeMove(GameModel model1, GUI gui1, ComputerAI ai) {
        computerPlaying = true;
        Thread thread = new Thread(){
            public void run(){
                try{
                    Thread.sleep(randNum(500,1001));
                        if(model1.gameInProgress == true){
                        model1.toggleCellRed(ai, ai.num1, ai.num2);
                        gui1.repaint();
                        model1.checkGameOver(gui1);
                        computerPlaying = false;
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        };
        thread.start();
    }
}
