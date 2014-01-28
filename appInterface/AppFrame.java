package appInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.lang3.text.WordUtils;

import appStructure.Module;

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

        String moduleAsKey = getModuleAsKey();

        this.QuestionArea = new AppCenter(this.Questionnaires.get(moduleAsKey), 0,
                getNumberOfPossibleChoicesOfAQuestion(moduleAsKey, 0));

        setWindowsProperty();
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

        this.container.add(this.top, "North");
        this.container.add(this.botToolBar, "South");
        this.container.add(this.QuestionArea, "Center");
        this.botToolBar.previousButton.setEnabled(false);
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
     * @return
     */
    private String getModuleAsKey() {
        this.module = this.mainToolBar.Module.getSelectedItem().toString();
        String moduleChanged = WordUtils.uncapitalize(this.module);
        moduleChanged = moduleChanged.trim().replace(' ', '_');

        return moduleChanged;
    }

    private void initQuestion(String module, int question) {
        this.container.remove(this.QuestionArea);
        int nbQuestion = getNumberOfQuestion(module);

        botToolBar.previousButton.setEnabled(question > 0);
        botToolBar.nextButton.setEnabled(nbQuestion - question - 1 > 0);

        this.QuestionArea = new AppCenter(this.Questionnaires.get(module), question,
                getNumberOfPossibleChoicesOfAQuestion(module, question));

        this.container.add(this.QuestionArea, "Center");
        this.container.add(this.top, "North");
        this.QuestionArea.setVisible(true);

        setContentPane(this.container);
    }

    /**
	 *
	 */
    public void actionPerformed(ActionEvent evt) {

        String moduleChanged = getModuleAsKey();

        int nextQuestion = this.QuestionArea.getCurrentQuestion() + 1;
        int previousQuestion = this.QuestionArea.getCurrentQuestion() - 1;

        if (evt.getSource() == this.botToolBar.nextButton) {

            initQuestion(moduleChanged, nextQuestion);

        } else if (evt.getSource() == this.botToolBar.previousButton) {

            initQuestion(moduleChanged, previousQuestion);
        } else if (evt.getSource() == this.mainToolBar.Module) {

            initQuestion(moduleChanged, 0);

        }
    }
}
