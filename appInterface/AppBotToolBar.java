package appInterface;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AppBotToolBar extends JPanel {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    public JButton            nextButton       = new JButton();
    public JButton            previousButton   = new JButton();

    public AppBotToolBar() {
        this.nextButton.setText("Question Suivante");
        this.previousButton.setText("Question Précédente");
        setBackground(Color.LIGHT_GRAY);

        add(this.previousButton);
        add(this.nextButton);
    }

    public void disableNextButton(boolean ver) {
        if (ver) {
            this.nextButton.setEnabled(false);
        }
        else {
            this.nextButton.setEnabled(true);
        }
    }

    public void disablePreviousButton(boolean ver) {
        if (ver) {
            this.previousButton.setEnabled(false);
        }
        else {
            this.previousButton.setEnabled(true);
        }
    }
}
