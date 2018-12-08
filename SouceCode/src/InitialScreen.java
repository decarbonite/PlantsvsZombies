import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 07 December, 2018
 */
public class InitialScreen {
    private JFrame frame;
    protected JTextField playerName;
    private Game g;

    public InitialScreen(Game g) {
        this.g = g;
        frame = new JFrame("Welcome");
        frame.setSize(200, 200);

        JPanel statsPanel = new JPanel(new GridLayout(4,1,0,0));

        JLabel nameLabel = new JLabel("Please enter your name:");
        playerName = new JTextField(40);

        JButton storyMode = new JButton("Story Mode (XML level builder)");
        storyMode.addActionListener(new InitialScreenController(this));

        JButton regularMode = new JButton("Regular Game");
        regularMode.addActionListener(new InitialScreenController(this));

        statsPanel.add(nameLabel);
        statsPanel.add(playerName);
        statsPanel.add(regularMode);
        statsPanel.add(storyMode);

        frame.getRootPane().setDefaultButton(regularMode);
        frame.add(statsPanel, BorderLayout.CENTER);
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);      //show gui in the middle of screen
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void regularGame() {
        Object[] possibilities = {"Zombie1", "Zombie2"};
        String zombieType = (String)JOptionPane.showInputDialog(
                frame,
                "Chose type of zombies to spawn:\n",
                "Specify zombie type",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Zombie1");

        while(true) {
            String s = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter number of Zombies to spawn:\n",
                    "Number of zombies",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    15);

            if(s != null && s.length() > 0) {
                try {
                    int numberOfZombies = Integer.valueOf(s);
                    g.setPlayerName(playerName.getText());
                    Map<String, Integer> zombieScope = new HashMap<String, Integer>();
                    zombieScope.put(zombieType, numberOfZombies);
                    g.generateGame(zombieScope);
                    frame.dispose();
                    break;
                } catch (Exception e) {
                    System.out.println("Wrong number");
                }
            }
        }
    }

    public void storyMode() {
        g.generateGame(null);
    }

    public void playerNameNotSet() {
        JOptionPane.showMessageDialog(frame,
                "Please enter your name",
                "Inane error",
                JOptionPane.ERROR_MESSAGE);
    }
}
