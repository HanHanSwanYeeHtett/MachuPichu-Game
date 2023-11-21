import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserForm extends JFrame implements ActionListener {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton enterButton, clearButton;
    private JCheckBox showPasswordBox;
    public static ArrayList<Player> players;

    public NewUserForm(ArrayList<Player> players) {
        if  (players == null) {
            players = new ArrayList<>();
        }
        //Setting up the GUI box
        this.players = players;
        setTitle("New User Form");
        setSize(300, 200);
        setLayout(new GridLayout(4, 3));

        nameLabel = new JLabel("Username");
        add(nameLabel);
        nameField = new JTextField();
        add(nameField);

        passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add (passwordField);

        showPasswordBox = new JCheckBox("Show password");
        showPasswordBox.addActionListener(this);
        add(showPasswordBox);

        enterButton = new JButton("Enter:");
        enterButton.addActionListener(this);
        add (enterButton);

        clearButton = new JButton("Clear:");
        clearButton.addActionListener(this);
        add (clearButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Taking in the data from the boxes from the user
        if (e.getSource() == enterButton) {
            String username = nameField.getText();
            String password = new String(passwordField.getPassword());

            //Checking if the usernames are unique
            boolean isUnique = true;
            for (Player player : players) {
                if (player.getName().equals(username)) {
                    isUnique = false;
                    break;
                }
            }
            if (!isUnique) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
                return;
            }

            //Checking if the password meets the requirements
            Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[$&+,:;=?@#|'<>.^*()%!-]).{8,}$");
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "Invalid password. Please have at least one upper case, numerical and special character");
                return;
            }

            Player newPlayer = new Player(username);
            Players.writePlayerDataToFile(newPlayer, players);

            //Loading the next (Game) form
            GameForm gameForm = new GameForm();
            gameForm.setVisible(true);
            dispose();

        } else if (e.getSource() == clearButton) {
            //Clearing text fields when the Clear button is clicked
            nameField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == showPasswordBox) {
            passwordField.setEchoChar(showPasswordBox.isSelected() ? '\u0000' : '*');
        }
    }
}
