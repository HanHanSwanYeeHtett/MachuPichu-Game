import java.util.ArrayList;
import java.util.Scanner;

public class Players {
    private static final String DATA_FILE = "data/data.csv";

    //This method will be used to read data from player file
    public static ArrayList<Player> readPlayerDataFromFile() {
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<String> playerStrs = FileIO.readDataFromFile(DATA_FILE);
        //Adding a new player
        for (String player : playerStrs) {
            players.add(new Player(player));
        }
        return players;
    }

    /**
     * This method will be used to write data
     * If a name entered by the user is the same as a name from a save file, load the file.
     *
     * @param player
     * @param players
     */
    public static void writePlayerDataToFile(Player player, ArrayList<Player> players) {
        //Instead of a boolean, a temporary index is implemented
        int index = -1;

        //Goes through the player data to find an existing username
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(player.getName())) {
                index = i;
                break;
            }
        }
        //A new player data will be added for unique usernames
        if (index == -1) {
            players.add(player);
        } else {
            players.set(index, player);
        }
        //This ArrayList will store all players' stats
        ArrayList<String> dataList = new ArrayList<>();
        for (Player playerOne : players) {
            dataList.add(playerOne.toCSVString());
        }
        FileIO.writeDataToFile(DATA_FILE, dataList);
    }

    /**
     * Checking if the player is currently in the list
     * @param players
     * @param name
     * @return Return the player object if the player exists in the ArrayList or null if not found
     */
    public static Player checkNameExists(ArrayList<Player> players, String name) {
        //Looping to see all the player names from the ArrayList Player
        for (Player playerName : players) {
            if (playerName.getName().equals(name)) {
                return playerName;
            }
        }
        return null;
    }

    /**
     * If the name is found in the checkNameExists method, ask the user if they want to load the existing data
     * The user can either load the data and return the relevant player object or return a new Player object
     *
     * @param players
     * @return
     */
    public static Player getPlayer(ArrayList<Player> players) {
        //Asking player name
        String name = null;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Let the MachuPichu games COMMENCE!!");
            System.out.println("Please enter a new or existing username:");
            name = scanner.nextLine();
            Player player = checkNameExists(players, name);
            if (player != null) {
                if (Utils.getChoice("We already have a saved data file from " + name + ". Load player data? (Y/N)?")); {
                return player;
                }
            }
        }
        return new Player(name);
    }
}
