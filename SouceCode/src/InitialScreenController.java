import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the initial screen to take information from user.
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 07 December, 2018
 */
public class InitialScreenController implements ActionListener {
    private InitialScreen is;

    /**
     * Default constructor
     * @param is InitialScreen object in order to perform actionListener
     */
    public InitialScreenController(InitialScreen is) {
        this.is = is;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            String choice = btn.getText();

            if(!is.playerName.getText().equals("")) {
                if (choice.equals("Regular Game")) {
                    is.regularGame();
                }

                if (choice.equals("Story Mode (XML level builder)")) {
                    is.storyMode();
                }
            } else {
                is.playerNameNotSet();
            }
        }
    }
}
