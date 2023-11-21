public class Player {
    public static final int NO_ELEMENTS = 0;
    public static final int HAS_BASE = 1;
    public static final int HAS_MIDDLE = 2;
    public static final int HAS_TOP = 3;
    private String name;
    private int score;
    private int mpStatus;
    private int highScore;

    public Player(String data) {
        String[] playerData = data.split(",");
        name = playerData[0];
        score = 0;
        highScore = 0;

        if (playerData.length == 2) {
            Integer hs = Utils.checkValidInt(playerData[1]);
            if (hs != null) {
                highScore = hs;
            }
            mpStatus = 0;
        }
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void incScore(int score) {
        this.score += score;
    }

    public int getMpStatus() {
        return mpStatus;
    }

    public void setMpStatus(int mpStatus) {
        this.mpStatus = mpStatus;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

    private String getStatusStr() {
        String status = "";
        switch (mpStatus) {
            case NO_ELEMENTS -> status = "No elements";
            case HAS_BASE -> status = "Has base";
            case HAS_MIDDLE -> status = "Has base. Has middle";
            case HAS_TOP -> status = "Has all elements";
        }
        return status;
    }

    public String toCSVString() {
        return name + "," + highScore;
    }
    @Override
    public String toString() {
        return "Player: " + name + ". Score: " + score + ". " + getStatusStr() +
                ". High score: " + highScore;
    }
}
