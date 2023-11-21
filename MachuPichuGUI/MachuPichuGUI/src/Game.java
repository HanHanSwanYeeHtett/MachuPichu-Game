import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    public static final int MAX_TURN = 5;
    private static final int BASE = 6;
    private static final int MIDDLE = 3;
    private static final int TOP = 1;
    private Player currentPlayer;

    /**
     * Calculating the accurate score for each set of 5 dice
     * @param currentRoll
     * @param player
     */
    public static void checkMachuPichu(Integer[] currentRoll, Player player) {
        //Sort out the array
        Arrays.sort(currentRoll);
        //Starting the if statements according to users' mpStatus
        if (player.getMpStatus() != 3) {
            if (player.getMpStatus() == 0) {
                //Checking all dice
                for (int i = 0; i < MAX_TURN; i++) {
                    if (currentRoll[i] == BASE) {
                        player.setMpStatus(1);
                        currentRoll[i] = 0;
                    }
                }
            }
            if (player.getMpStatus() == 1) {
                for (int i = 0; i < MAX_TURN; i++) {
                    if (currentRoll[i] == MIDDLE) {
                        player.setMpStatus(2);
                        currentRoll[i] = 0;
                    }
                }
            }
            if (player.getMpStatus() == 2) {
                for (int i = 0; i < MAX_TURN; i++) {
                    if (currentRoll[i] == TOP) {
                        player.setMpStatus(3);
                        currentRoll[i] = 0;
                    }
                }
            }
        } else if (player.getMpStatus() == 3) {
            for (int i = 0; i < MAX_TURN; i++) {
                player.incScore(currentRoll[i]);
            }
        }
        //Setting the result with the biggest value as the high score
        player.setHighScore();
    }

    /**
     * Playing a single turn and printing out current player details while pausing
     * @param player
     */
    public void playTurn(Player player) {
        Dice rollDice = new Dice(); // Initializing the Dice class
        Integer[] dice = rollDice.getDiceList();
        System.out.println(player.getName() + " rolls " + Arrays.toString(dice) + ".");
        checkMachuPichu(dice, player);
        currentPlayer = player;
        System.out.println("Player: " + player.getName() + " - Score: " + player.getScore() + " - High Score: " + player.getHighScore());
    }

    /**
     * Setting up the player and computer using the getPlayer method
     * Using an appropriate loop until they no longer want to play the game
     * Playing an entire game between a human player and a computer player
     * Writing the players data and the player data to the data file
     */
    public void playGame() {
        //Read player data first to start the game
        ArrayList<Player> players = Players.readPlayerDataFromFile();

        //Playing each turn 5 times for each player
        Player humanPlayer = Players.getPlayer(players);
        for (int i = 0; i < MAX_TURN; i++) {
            playTurn(humanPlayer);
        }

        Player computerPlayer = new Player("CPU Opponent");
        for (int i = 0; i < MAX_TURN; i++) {
            playTurn(computerPlayer);
        }

        //Determining the winners and losers
        if (humanPlayer.getScore() > computerPlayer.getScore()) {
            System.out.println("You win!");
        } else if (humanPlayer.getScore() < computerPlayer.getScore()) {
            System.out.println("You lose!");
        }

        System.out.println("Current score: " + humanPlayer.toCSVString());

        Players.writePlayerDataToFile(humanPlayer, players);
    }
}




