import java.util.Arrays;
import java.util.Random;

public class Dice {
    public static final int MAX_DICE = 5;
    private int rollDice() {
        Random random = new Random();
        return (random.nextInt(6) + 1);
    }


    public Integer[] getDiceList() {
        Integer[] dice = new Integer[MAX_DICE];
        for (int i = 0; i < MAX_DICE; i++) {
            dice[i] = rollDice();
        }
        return dice;
    }
}
