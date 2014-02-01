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
    public Map<String, Module> modules;

    private AppBotToolBar      _botToolBar      = new AppBotToolBar();

    private JPanel             _container       = new JPanel();

    private AppToolBar         _mainToolBar     = new AppToolBar();
    private String             _moduleName      = "";

    private AppCenter          _questionArea;

    private JPanel             _top             = new JPanel();

    /**
     * 
     * @param questionnaires
     */
    public AppFrame(Map<String, Module> questionnaires) {
        modules = questionnaires;
        _moduleName = _mainToolBar.moduleComboBox.getSelectedItem().toString();

        String moduleAsKey = getModuleAsKey();

        _questionArea = new AppCenter(modules.get(moduleAsKey), 0, getNumberOfPossibleChoicesOfAQuestion(moduleAsKey, 0));

        setWindowsProperty();
        setActionListener();

    }

    /**
	 *
	 */
    @Override
    public void actionPerformed(ActionEvent evt) {

        String moduleChanged = getModuleAsKey();

        int nextQuestion = _questionArea.getCurrentQuestion() + 1;
        int previousQuestion = _questionArea.getCurrentQuestion() - 1;

        if (evt.getSource() == _mainToolBar.randomButton) {
            // Need to disable the ActionListener to not call initQuestion twice
            this._mainToolBar.moduleComboBox.removeActionListener(this);
            chooseRandomQuestion();
            this._mainToolBar.moduleComboBox.addActionListener(this);
        }
        else if (evt.getSource() == _botToolBar.nextButton) {

            initQuestion(moduleChanged, nextQuestion);

        }
        else if (evt.getSource() == _botToolBar.previousButton) {

            initQuestion(moduleChanged, previousQuestion);

        }
        else if (evt.getSource() == _mainToolBar.moduleComboBox) {
            initQuestion(moduleChanged, 0);

        }
    }

    /**
     * It chooses a random Question from a module
     */
    public void chooseRandomQuestion() {

        Module module = getRandomModule();
        Random random = new Random();
        int randomQuestionNumber = random.nextInt(module.getSize());

        initQuestion(module.getName(), randomQuestionNumber);

    }

    /**
     * It chooses a random Module from all the modules
     * 
     * @return the choosen Module
     */
    public Module getRandomModule() {
        Random random = new Random();
        List<String> keys = new ArrayList<String>(modules.keySet());
        String randomKey = keys.get(random.nextInt(keys.size()));

        _mainToolBar.moduleComboBox.setSelectedIndex(keys.indexOf(randomKey));

        Module module = modules.get(randomKey);

        return module;
    }

    /**
     * 
     * @param module
     * @param question
     */
    public void initQuestion(String module, int question) {
        _container.remove(_questionArea);
        int nbQuestion = getNumberOfQuestion(module);

        _botToolBar.previousButton.setEnabled(question > 0);
        _botToolBar.nextButton.setEnabled(nbQuestion - question - 1 > 0);

        try {
            _questionArea = new AppCenter(modules.get(module), question, getNumberOfPossibleChoicesOfAQuestion(module, question));
        } catch (ClassCastException e) {
            System.err.println("The key is of an inappropriate type for this map, Key :" + module);

        } catch (NullPointerException e) {
            System.err.println("initQuestion : The specified key is null and this map does not permit null keys");
        }

        _container.add(_questionArea, "Center");
        _top.add(_mainToolBar, "East");
        _container.add(_top, "North");

        _questionArea.setVisible(true);

        setContentPane(_container);
    }

    /**
     * @return string as key for the map
     */
    private String getModuleAsKey() {
        _moduleName = _mainToolBar.moduleComboBox.getSelectedItem().toString();
        String moduleChanged = WordUtils.uncapitalize(_moduleName);
        moduleChanged = moduleChanged.trim().replace(' ', '_');

        return moduleChanged;
    }

    /**
     * 
     * @param module
     * @return int number of Possible Choices in a question
     */
    private int getNumberOfPossibleChoicesOfAQuestion(String module, int currentQuestion) {
        int NbChoix = 0;
        try {
            NbChoix = modules.get(module).getQuestions().get(currentQuestion).getNbChoix();
        } catch (ClassCastException e) {
            System.err.println("The key is of an inappropriate type for this map" + module);

        } catch (NullPointerException e) {
            System.err.println("The specified key is null and this map does not permit null keys");
        }
        return NbChoix;
    }

    /**
     * 
     * @param module
     * @return int Number of questions for a Module
     */
    private int getNumberOfQuestion(String module) {
        int size = 0;
        try {
            size = modules.get(module).getSize();
        } catch (ClassCastException e) {
            System.err.println("The key is of an inappropriate type for this map (optional)");

        } catch (NullPointerException e) {
            System.err.println("The specified key is null and this map does not permit null keys");
        }
        return size;
    }

    /**
	 *
	 */
    private void setActionListener() {
        _mainToolBar.moduleComboBox.addActionListener(this);
        _mainToolBar.randomButton.addActionListener(this);
        _botToolBar.nextButton.addActionListener(this);
        _botToolBar.previousButton.addActionListener(this);
    }

    /**
	 *
	 */
    private void setWindowsProperty() {

        setSize(1024, 600);
        setTitle("Astro Quizz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        _container.setBackground(Color.LIGHT_GRAY);
        _container.setLayout(new BorderLayout());

        _top.setBackground(Color.LIGHT_GRAY);
        _top.setLayout(new BorderLayout());
        _top.add(_mainToolBar, "East");
        _top.add(_mainToolBar.randomButton, "West");

        _container.add(_top, "North");
        _container.add(_botToolBar, "South");
        _container.add(_questionArea, "Center");
        _botToolBar.previousButton.setEnabled(false);
        setContentPane(_container);

    }
}
