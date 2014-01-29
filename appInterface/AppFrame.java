/*******************************************************************************
 * Copyright (c) 2014
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Unknown - initial API and implementation
 *     Maxime Roussin-Bélanger - Huge refactor
 *     Simon Gamache-Poirer - Helped the huge refactor
 *******************************************************************************/

package appInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
     * @param questionnaires
     */
    public AppFrame(Map<String, Module> questionnaires) {
        Questionnaires = questionnaires;
        module = mainToolBar.moduleComboBox.getSelectedItem().toString();

        String moduleAsKey = getModuleAsKey();

        QuestionArea = new AppCenter(Questionnaires.get(moduleAsKey), 0,
                getNumberOfPossibleChoicesOfAQuestion(moduleAsKey, 0));

        setWindowsProperty();
        setActionListener();

    }

    /**
     *
     * @param module
     * @return int Number of questions for a Module
     */
    private int getNumberOfQuestion(String module) {
        return Questionnaires.get(module).getSize();
    }

    /**
     *
     * @param module
     * @return int number of Possible Choices in a question
     */
    private int getNumberOfPossibleChoicesOfAQuestion(String module, int currentQuestion) {
        return Questionnaires.get(module).getQuestions().get(currentQuestion).getNbChoix();
    }

    /**
	 *
	 */
    private void setWindowsProperty() {

        setSize(1024, 600);
        setTitle("Astro Quizz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new BorderLayout());

        top.setBackground(Color.LIGHT_GRAY);
        top.setLayout(new BorderLayout());
        top.add(mainToolBar, "East");
        top.add(mainToolBar.randomButton, "West");

        container.add(top, "North");
        container.add(botToolBar, "South");
        container.add(QuestionArea, "Center");
        botToolBar.previousButton.setEnabled(false);
        setContentPane(container);

    }

    /**
	 *
	 */
    private void setActionListener() {
        mainToolBar.moduleComboBox.addActionListener(this);
        mainToolBar.randomButton.addActionListener(this);
        botToolBar.nextButton.addActionListener(this);
        botToolBar.previousButton.addActionListener(this);

    }

    /**
     * @return string as key for the map
     */
    private String getModuleAsKey() {
        module = mainToolBar.moduleComboBox.getSelectedItem().toString();
        String moduleChanged = WordUtils.uncapitalize(module);
        moduleChanged = moduleChanged.trim().replace(' ', '_');

        return moduleChanged;
    }

    public void chooseRandomQuestion() {

        Module module = getRandomModule();
        Random random = new Random();
        int randomQuestionNumber = random.nextInt(module.getSize());

        initQuestion(module.getName(), randomQuestionNumber);

    }

    /**
     * @return
     */
    public Module getRandomModule() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(Questionnaires.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));

        mainToolBar.moduleComboBox.setSelectedIndex(keys.indexOf(randomKey));

        Module module = Questionnaires.get(randomKey);

        return module;
    }

    public void initQuestion(String module, int question) {
        container.remove(QuestionArea);
        int nbQuestion = getNumberOfQuestion(module);

        botToolBar.previousButton.setEnabled(question > 0);
        botToolBar.nextButton.setEnabled(nbQuestion - question - 1 > 0);

        QuestionArea = new AppCenter(Questionnaires.get(module), question,
                getNumberOfPossibleChoicesOfAQuestion(module, question));

        container.add(QuestionArea, "Center");
        top.add(mainToolBar, "East");
        container.add(top, "North");

        QuestionArea.setVisible(true);

        setContentPane(container);
    }

    /**
	 *
	 */
    public void actionPerformed(ActionEvent evt) {

        String moduleChanged = getModuleAsKey();

        int nextQuestion = QuestionArea.getCurrentQuestion() + 1;
        int previousQuestion = QuestionArea.getCurrentQuestion() - 1;

        if (evt.getSource() == mainToolBar.randomButton) {
            chooseRandomQuestion();
        }

        if (evt.getSource() == botToolBar.nextButton) {

            initQuestion(moduleChanged, nextQuestion);

        } else if (evt.getSource() == botToolBar.previousButton) {

            initQuestion(moduleChanged, previousQuestion);

        } else if (evt.getSource() == mainToolBar.moduleComboBox) {

            initQuestion(moduleChanged, 0);

        }
    }
}
