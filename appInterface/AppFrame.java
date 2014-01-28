package appInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.lang3.text.WordUtils;

import appStructure.Module;
import appStructure.Question;

public class AppFrame extends JFrame implements ActionListener {
    /**
	 *
	 */
    private static final long  serialVersionUID = 1L;
    private String             module           = "";
    private AppToolBar         mainToolBar      = new AppToolBar();
    private AppBotToolBar      botToolBar       = new AppBotToolBar();
    private JPanel             container        = new JPanel();
    private JPanel             top              = new JPanel();
    private AppCenter          QuestionArea;

    public Map<String, Module> Questionnaires;

    /**
	 *
	 */
    public AppFrame(Map<String, Module> questionnaires) {
        this.Questionnaires = questionnaires;
        this.module = this.mainToolBar.Module.getSelectedItem().toString();
        setWindowsProperty();
        initModule();
        setActionListener();

    }

    /**
     *
     * @param module
     * @return
     */
    private int getNumberOfQuestion(String module) {
        return this.Questionnaires.get(module).getSize();
    }

    /**
     *
     * @param module
     * @return
     */
    private int getNumberOfPossibleChoicesOfAQuestion(String module, int currentQuestion) {
        return this.Questionnaires.get(module).getQuestions().get(currentQuestion).getNbChoix();
    }

    /**
	 *
	 */
    private void setWindowsProperty() {

        setSize(1024, 600);
        setTitle("Astro Quizz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.container.setBackground(Color.LIGHT_GRAY);
        this.container.setLayout(new BorderLayout());

        this.top.setBackground(Color.LIGHT_GRAY);
        this.top.setLayout(new BorderLayout());
        this.top.add(this.mainToolBar, "East");

        this.container.add(this.botToolBar, "South");

        setContentPane(this.container);

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
    private void initModule() {

        String moduleChanged = getModuleAsKey();

        this.QuestionArea = new AppCenter(this.Questionnaires.get(moduleChanged), 0,
                getNumberOfPossibleChoicesOfAQuestion(moduleChanged, 0));

        int numberOfQuestion = getNumberOfQuestion(moduleChanged);

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
     * @return
     */
    private String getModuleAsKey() {
        this.module = this.mainToolBar.Module.getSelectedItem().toString();
        String moduleChanged = WordUtils.uncapitalize(this.module);
        moduleChanged = moduleChanged.trim().replace(' ', '_');

        return moduleChanged;
    }

    private void initQuestion() {
    	this.container.add(this.QuestionArea, "Center");
    	this.QuestionArea.setVisible(true);
    	setContentPane(this.container);
    }

    /**
	 *
	 */
    public void actionPerformed(ActionEvent evt) {

        this.QuestionArea.picture.removeAll();
        this.QuestionArea.question.removeAll();
        this.QuestionArea.reponse.removeAll();
        this.QuestionArea.Answers.clear();
        this.QuestionArea.Labels.clear();

        String moduleChanged = getModuleAsKey();
        Module leModule = this.Questionnaires.get(moduleChanged);

        int numberOfQuestion = getNumberOfQuestion(moduleChanged) - 1;
        //TRACE
        System.out.println(numberOfQuestion);

        if (numberOfQuestion == 1) {
            this.botToolBar.previousButton.setEnabled(false);
            this.botToolBar.nextButton.setEnabled(false);
        }

        if (evt.getSource() == this.botToolBar.nextButton) {
            if (!this.botToolBar.previousButton.isEnabled()) {
                this.botToolBar.previousButton.setEnabled(true);
            }

            int question = this.QuestionArea.getCurrentQuestion() + 1;

            if (question == numberOfQuestion) {
                this.botToolBar.nextButton.setEnabled(false);
            }

            this.QuestionArea = new AppCenter(leModule, question, getNumberOfPossibleChoicesOfAQuestion(moduleChanged,
                    question));

            initQuestion();

        } else if (evt.getSource() == this.mainToolBar.Module) {

            initModule();

        } else if (evt.getSource() == this.botToolBar.previousButton) {
            if (!this.botToolBar.nextButton.isEnabled()) {
                this.botToolBar.nextButton.setEnabled(true);
            }

            int question = this.QuestionArea.getCurrentQuestion() - 1;

            if (question == 0) {
                this.botToolBar.previousButton.setEnabled(false);
            }
            
            this.QuestionArea = new AppCenter(this.Questionnaires.get(moduleChanged), question,
                    getNumberOfPossibleChoicesOfAQuestion(moduleChanged, question));
            initQuestion();
            /*
            for (int j = 0; j < this.QuestionArea.Answers.size(); j++) {
                if (this.QuestionArea.Answers.get(j) == evt.getSource()) {
                    char answer = this.QuestionArea.getGoodAnswer();
                    if (true) {
                        this.QuestionArea.Answers.get(j).setBackground(Color.green);
                    } else {
                        this.QuestionArea.Answers.get(j).setBackground(Color.red);
                    }

                }

            }*/
        }
        this.QuestionArea.setBackgroundColor();

    }
}
