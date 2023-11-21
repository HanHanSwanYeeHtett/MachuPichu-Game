import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameForm extends JFrame {
    private Game game;
    private Player humanPlayer;
    private Player computerPlayer;
    private int turnCount;
    private int diceRoll;
    private JLabel turnLabel;
    private JLabel humanScoreLabel;
    private JLabel computerScoreLabel;
    private JButton rollButton;
    private JButton endButton;
    private JLabel usernameLabel;
    private JLabel highScoreLabel;
    private JLabel mpStatusLabel;

    public GameForm() {
        //Setting up the game
        game = new Game();
        humanPlayer = Players.getPlayer(Players.readPlayerDataFromFile());
        computerPlayer = new Player("CPU Opponent");
        turnCount = 0;

        //Setting up the GUI components
        usernameLabel = new JLabel("Player: " + humanPlayer.getName());
        highScoreLabel = new JLabel("HighScore: " + humanPlayer.getHighScore());
        mpStatusLabel = new JLabel("MPStatus: " + humanPlayer.getMpStatus());
        turnLabel = new JLabel("Turn " + (turnCount) + " of " + Game.MAX_TURN);
        humanScoreLabel = new JLabel("Your Score: " + humanPlayer.getScore());
        computerScoreLabel = new JLabel("Computer Score: " + computerPlayer.getScore());
        rollButton = new JButton("Roll");
        endButton = new JButton("End Game");

        //Setting up the layout
        setLayout(new GridLayout(4, 1));
        add(usernameLabel);
        add(highScoreLabel);
        add(mpStatusLabel);
        add(turnLabel);
        add(humanScoreLabel);
        add(computerScoreLabel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(rollButton);
        buttonPanel.add(endButton);
        add(buttonPanel);

        //Adding in the event listeners
        rollButton.addActionListener(new RollButtonListener());
        endButton.addActionListener(new EndButtonListener());

        //Setting up the frame
        setTitle("Machu Pichu Dice Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Start playing the game
    private void playTurn(Player player) {
        game.playTurn(player);
        humanScoreLabel.setText("Your Score: " + humanPlayer.getScore());
        computerScoreLabel.setText("Computer Score: " + computerPlayer.getScore());
        mpStatusLabel.setText(humanPlayer.getName() + "'s MPStatus: " + humanPlayer.getMpStatus());
        highScoreLabel.setText(humanPlayer.getName() + "'s HighScore: " + humanPlayer.getHighScore());
        turnLabel.setText("Turn " + (turnCount + 1) + " of " + Game.MAX_TURN);
    }

    //When users click the roll button
    private class RollButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < 5; i++) {
                playTurn(humanPlayer);
                playTurn(computerPlayer);
                if (i == 4) {
                    int humanScore = humanPlayer.getScore();
                    int computerScore = computerPlayer.getScore();
                    if (humanScore > computerScore) {
                        JOptionPane.showMessageDialog(GameForm.this, "You win!");
                    } else if (humanScore < computerScore) {
                        JOptionPane.showMessageDialog(GameForm.this, "You lose!");
                    } else {
                        JOptionPane.showMessageDialog(GameForm.this, "Tie game!");
                    }
                }
            }
        }
    }

    //When users click the end button
        private class EndButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                Players.writePlayerDataToFile(humanPlayer, Players.readPlayerDataFromFile());
                System.exit(0);
            }
        }
    }