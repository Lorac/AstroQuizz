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
 *     Jean Lalande - Helped on the MAC Integretion
 *******************************************************************************/

package com.lorack.astroquizz.ui;

import com.lorack.astroquizz.domain.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class AppFrame extends JFrame implements ActionListener {
    // ----------------------------------------------------------------------
    // Attributs du AppFrame
    // ----------------------------------------------------------------------

    private static final Logger appFrameLOGGER = Logger.getLogger(AppFrame.class.getName());
    private Map<String, Module> modules;
    private BottomToolBar botToolBar = new BottomToolBar();
    private JPanel container = new JPanel();
    private JPanel top = new JPanel();
    private TopToolBar mainToolBar = new TopToolBar();
    private String moduleName = "";
    private AppCenter questionArea;

    /**
     * Makes the whole frame
     *
     * @param questionnaires
     */
    public AppFrame(Map<String, Module> questionnaires) {
        modules = questionnaires;
        moduleName = mainToolBar.getModuleComboBox().getSelectedItem().toString();

        String moduleAsKey = getModuleAsKey();

        questionArea = new AppCenter(modules.get(moduleAsKey), 0, getNumberOfPossibleChoicesOfAQuestion(moduleAsKey, 0));


        setWindowsProperty();
        setActionListener();

    }


    // ----------------------------------------------------------------------
    // Méthodes publique
    // ----------------------------------------------------------------------

    public Map<String, Module> getModules() {
        return modules;
    }

    public BottomToolBar getBotToolBar() {
        return botToolBar;
    }

    public JPanel getContainer() {
        return container;
    }

    public JPanel getTop() {
        return top;
    }

    public TopToolBar getMainToolBar() {
        return mainToolBar;
    }

    public String getModuleName() {
        return moduleName;
    }

    public AppCenter getQuestionArea() {
        return questionArea;
    }

    /**
     * Any action performed on any button is here
     */
    public void actionPerformed(ActionEvent evt) {

        String moduleChanged = getModuleAsKey();

        int nextQuestion = questionArea.getCurrentQuestion() + 1;
        int previousQuestion = questionArea.getCurrentQuestion() - 1;

        if (evt.getSource() == mainToolBar.getRandomButton()) {
            // Need to disable the ActionListener to not call initQuestion twice
            this.mainToolBar.getModuleComboBox().removeActionListener(this);
            chooseRandomQuestion();
            this.mainToolBar.getModuleComboBox().addActionListener(this);
        } else if (evt.getSource() == botToolBar.getNextButton()) {

            initQuestion(moduleChanged, nextQuestion);

        } else if (evt.getSource() == botToolBar.getPreviousButton()) {

            initQuestion(moduleChanged, previousQuestion);

        } else if (evt.getSource() == mainToolBar.getModuleComboBox()) {
            initQuestion(moduleChanged, 0);

        }
    }

    /**
     * It chooses a random Question from a module
     */
    public void chooseRandomQuestion() {

        Module module = getRandomModule();
        Random random = new Random();
        int randomQuestionNumber = 0;
        try {
            randomQuestionNumber = random.nextInt(module.getSize());
        } catch (IllegalArgumentException e) {
            appFrameLOGGER.log(Level.SEVERE, "Erreur: chooseRandomQuestion, le module : " + module.getName());
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }

        try {
            initQuestion(module.getName().toLowerCase(), randomQuestionNumber);
        } catch (NullPointerException e) {
            appFrameLOGGER.log(Level.SEVERE, "Le module est invalide, null");
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            appFrameLOGGER.log(Level.SEVERE, "Erreur majeur : initQuestion");
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }

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

        mainToolBar.getModuleComboBox().setSelectedIndex(keys.indexOf(randomKey));

        Module module = null;
        try {
            module = modules.get(randomKey);
        } catch (NullPointerException e) {
            appFrameLOGGER.log(Level.SEVERE, "Erreur, getRandomModule : impossible de retrouver le module : " + randomKey);
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }

        return module;
    }

    /**
     * Initialiase a question
     *
     * @param module
     * @param question
     */
    public void initQuestion(String module, int question) {
        container.remove(questionArea);
        int nbQuestion = getNumberOfQuestion(module);

        botToolBar.getPreviousButton().setEnabled(question > 0);
        botToolBar.getNextButton().setEnabled(nbQuestion - question - 1 > 0);

        try {
            questionArea = new AppCenter(modules.get(module), question,
                    getNumberOfPossibleChoicesOfAQuestion(module, question));
        } catch (ClassCastException e) {
            appFrameLOGGER.log(Level.SEVERE, "The key is of an inappropriate type for this map, Key :" + module);
            appFrameLOGGER.log(Level.WARNING, e.getMessage());

        } catch (NullPointerException e) {
            appFrameLOGGER.log(Level.SEVERE, "initQuestion : The specified key is null and this map does not permit null keys");
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }

        container.add(questionArea, BorderLayout.CENTER);
        top.add(mainToolBar, BorderLayout.EAST);
        container.add(top, BorderLayout.NORTH);

        questionArea.setVisible(true);

        setContentPane(container);
    }

    // ----------------------------------------------------------------------
    // Méthodes privées
    // ----------------------------------------------------------------------

    /**
     * Get a module as a Key for the Map
     *
     * @return string as key for the map
     */
    private String getModuleAsKey() {
        moduleName = mainToolBar.getModuleComboBox().getSelectedItem().toString();
        String moduleChanged = moduleName.toLowerCase();
        moduleChanged = moduleChanged.trim().replace(' ', '_');
        return moduleChanged;
    }

    /**
     * @param module
     * @param currentQuestion
     * @return the number of possible choices for a question
     */
    private int getNumberOfPossibleChoicesOfAQuestion(String module, int currentQuestion) {
        int NbChoix = 0;
        try {
            NbChoix = modules.get(module).getQuestions().get(currentQuestion).getNbChoix();
        } catch (ClassCastException e) {
            appFrameLOGGER.log(Level.SEVERE, "The key is of an inappropriate type for this map" + module);
            appFrameLOGGER.log(Level.WARNING, e.getMessage());

        } catch (NullPointerException e) {
            appFrameLOGGER.log(Level.SEVERE, "The specified key is null and this map does not permit null keys");
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }
        return NbChoix;
    }

    /**
     * @param module
     * @return int Number of questions for a Module
     */
    private int getNumberOfQuestion(String module) {
        int size = 0;
        try {
            size = modules.get(module).getSize();
        } catch (ClassCastException e) {
            appFrameLOGGER.log(Level.SEVERE, "The key is of an inappropriate type for this map :" + module);
            appFrameLOGGER.log(Level.WARNING, e.getMessage());

        } catch (NullPointerException e) {
            appFrameLOGGER.log(Level.SEVERE, "The specified key is null and this map does not permit null keys");
            appFrameLOGGER.log(Level.WARNING, e.getMessage());
        }
        return size;
    }

    /**
     *
     */
    private void setActionListener() {
        mainToolBar.getModuleComboBox().addActionListener(this);
        mainToolBar.getRandomButton().addActionListener(this);
        botToolBar.getNextButton().addActionListener(this);
        botToolBar.getPreviousButton().addActionListener(this);
    }

    /**
     *
     */
    private void setWindowsProperty() {

        setSize(1024, 600);
        setTitle("Astro Quizz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container.setLayout(new BorderLayout());
        container.setBackground(Color.LIGHT_GRAY);
        container.add(top, BorderLayout.NORTH);
        container.add(botToolBar, BorderLayout.SOUTH);
        container.add(questionArea, BorderLayout.CENTER);

        top.setLayout(new BorderLayout());
        top.setBackground(Color.LIGHT_GRAY);
        top.add(mainToolBar, BorderLayout.EAST);
        top.add(mainToolBar.getRandomButton(), BorderLayout.WEST);

        botToolBar.getPreviousButton().setEnabled(false);

        setContentPane(container);

    }
}
