import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {
    //Making two buttons for the Main Menu screen
    private JButton newUserButton, existingUserButton;

    public MainMenu() {
        //Setting up the GUI box
        setTitle("Main Menu");
        setLayout(new GridLayout(2, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initializing the buttons
        newUserButton = new JButton("New User");
        existingUserButton = new JButton("Existing User");

        //Putting the buttons into the GUI box
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(newUserButton);
        buttonPanel.add(existingUserButton);

        add(buttonPanel);

        //Putting action listeners for the buttons since they lead to other forms
        newUserButton.addActionListener(this);
        existingUserButton.addActionListener(this);

        //Activating the GUI box
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newUserButton) {
            //Loading a new user form
            NewUserForm newUserForm = new NewUserForm(NewUserForm.players);
            newUserForm.setVisible(true);
        } else if (e.getSource() == existingUserButton) {
            //Loading an existing user form
            NewUserForm newUserForm = new NewUserForm(NewUserForm.players);
            newUserForm.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}


