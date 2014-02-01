package appInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AppFrame extends JFrame implements ActionListener {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private String            module           = "";
    private AppToolBar        mainToolBar      = new AppToolBar();
    private JPanel            container        = new JPanel();
    private AppCenter         QuestionArea;
    private JPanel            top              = new JPanel();
    private AppBotToolBar     botToolBar       = new AppBotToolBar();

    /**
	 * 
	 */
    public AppFrame() {
        setWindowsProperty();
    }

    /**
	 * 
	 */
    private void setWindowsProperty() {
        setSize(1024, 600);
        setTitle("Astro Quizz");
        setDefaultCloseOperation(3);

        this.container.setBackground(Color.LIGHT_GRAY);
        this.container.setLayout(new BorderLayout());

        this.top.setBackground(Color.LIGHT_GRAY);
        this.top.setLayout(new BorderLayout());
        this.top.add(this.mainToolBar, "East");

        this.module = this.mainToolBar.Module.getSelectedItem().toString();

        this.QuestionArea = new AppCenter(this.module, 0);

        initialModuleQuestion();

        this.container.add(this.botToolBar, "South");

        setContentPane(this.container);
        setActionListener();
    }

    /**
	 * 
	 */
    private void setActionListener() {
        this.mainToolBar.Module.addActionListener(this);
        this.botToolBar.nextButton.addActionListener(this);
        this.botToolBar.previousButton.addActionListener(this);

    }

    /**
	 * 
	 */
    private void initialModuleQuestion() {
        this.top.setLayout(new BorderLayout());
        this.top.add(this.mainToolBar, "East");

        this.module = this.mainToolBar.Module.getSelectedItem().toString();

        int numberOfQuestion = this.QuestionArea.getNumberOfQuestion(this.module);

        this.botToolBar.disablePreviousButton(true);
        this.botToolBar.disableNextButton(false);

        if (numberOfQuestion == 1) {
            this.botToolBar.disableNextButton(true);
        }

        this.container.add(this.top, "North");
        this.container.add(this.QuestionArea, "Center");

        this.QuestionArea.setBackgroundColor();
        this.QuestionArea.setVisible(true);

        setContentPane(this.container);
    }

    /**
	 * 
	 */
    public void actionPerformed(ActionEvent evt) {

        int numberOfQuestion = this.QuestionArea.getNumberOfQuestion(this.module);
        
        if (numberOfQuestion == 1) {
            this.botToolBar.previousButton.setEnabled(false);
            this.botToolBar.nextButton.setEnabled(false);
        }

        if (evt.getSource() == this.botToolBar.nextButton) {
            if (!this.botToolBar.previousButton.isEnabled()) {
                this.botToolBar.previousButton.setEnabled(true);
            }

            this.module = this.mainToolBar.Module.getSelectedItem().toString();

            int question = this.QuestionArea.getCurrentQuestion() + 1;

            if (question == numberOfQuestion) {
                this.botToolBar.nextButton.setEnabled(false);
            }

            this.QuestionArea.newQuestion(this.module, question);
            this.QuestionArea.setCurrentQuestion(question);

        }
        else if (evt.getSource() == this.mainToolBar.Module) {

            initialModuleQuestion();

        }
        else if (evt.getSource() == this.botToolBar.previousButton) {

            if (!this.botToolBar.nextButton.isEnabled()) {

                this.botToolBar.nextButton.setEnabled(true);

            }
            this.module = this.mainToolBar.Module.getSelectedItem().toString();

            int question = this.QuestionArea.getCurrentQuestion() - 1;

            this.QuestionArea.newQuestion(this.module, question);
            this.QuestionArea.setCurrentQuestion(question);

            if (question == 0) {
                this.botToolBar.previousButton.setEnabled(false);
            }

            for (int j = 0; j < this.QuestionArea.Answers.size(); j++) {
                if (this.QuestionArea.Answers.get(j) == evt.getSource()) {
                    char answer = this.QuestionArea.getGoodAnswer();
                    if (true) {
                        this.QuestionArea.Answers.get(j).setBackground(Color.green);
                    }
                    else {
                        this.QuestionArea.Answers.get(j).setBackground(Color.red);
                    }

                }

            }
        }
        this.QuestionArea.setBackgroundColor();
    }
}
