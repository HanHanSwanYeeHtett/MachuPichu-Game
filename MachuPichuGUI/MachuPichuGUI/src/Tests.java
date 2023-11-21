public class Tests {

    private static void runTest(String testNo, int correctScore, Integer[][] testDice) {
        Player testPlayer = new Player("Test");
        for (int row = 0; row < testDice.length; row++) {
            Game.checkMachuPichu(testDice[row], testPlayer);
        }
        if (testPlayer.getScore() == correctScore)
            System.out.println(testNo + " passed. Score is " + testPlayer.getScore());
        else
            System.out.println(testNo + " failed. Score is " + testPlayer.getScore());
    }
    public static void testOne() {
        final int CORRECT_SCORE = 47;
        Integer[][] testDice = {
                {5,4,3,4,2},
                {4,6,4,2,1},
                {3,5,4,2,1},
                {6,3,5,3,2},
                {1,5,6,2,3}};
        runTest("Test One", CORRECT_SCORE, testDice);
    }



    public static void testTwo() {
        final int CORRECT_SCORE = 48;
        final Integer[][] testDice = {
                {5,5,4,2,2},
                {6,5,3,2,2},
                {6,4,4,2,1},
                {5,2,1,1,1},
                {6,6,4,3,3}};
        runTest("Test Two", CORRECT_SCORE, testDice);
    }

    public static void testThree() {
        final int CORRECT_SCORE = 48;
        final Integer[][] testDice = {
                {3,1,5,3,4},
                {1,3,6,2,2},
                {5,4,6,3,1},
                {5,2,5,4,1},
                {2,3,5,6,3}};
        runTest("Test Three", CORRECT_SCORE, testDice);
    }

    public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
    }
}
