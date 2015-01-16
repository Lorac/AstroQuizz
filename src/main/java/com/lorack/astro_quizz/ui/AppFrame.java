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

package com.lorack.astro_quizz.ui;

import com.lorack.astro_quizz.domain.Module;

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

    private static Logger appFrameLogger;
    public Map<String, Module> modules;
    private BottomToolBar botToolBar = new BottomToolBar();
    private JPanel container = new JPanel();
    private JPanel top = new JPanel();
    private TopToolBar mainToolBar = new TopToolBar();
    private String moduleName = "";
    private AppCenter questionArea;


    // ----------------------------------------------------------------------
    // Méthodes publique
    // ----------------------------------------------------------------------

    /**
     * Makes the whole frame
     *
     * @param questionnaires
     */
    public AppFrame(Map<String, Module> questionnaires) {
        modules = questionnaires;
        moduleName = mainToolBar.moduleComboBox.getSelectedItem().toString();

        String moduleAsKey = getModuleAsKey();

        questionArea = new AppCenter(modules.get(moduleAsKey), 0, getNumberOfPossibleChoicesOfAQuestion(moduleAsKey, 0));


        setWindowsProperty();
        setActionListener();

    }

    /**
     * Any action performed on any button is here
     */
    public void actionPerformed(ActionEvent evt) {

        String moduleChanged = getModuleAsKey();

        int nextQuestion = questionArea.getCurrentQuestion() + 1;
        int previousQuestion = questionArea.getCurrentQuestion() - 1;

        if (evt.getSource() == mainToolBar.randomButton) {
            // Need to disable the ActionListener to not call initQuestion twice
            this.mainToolBar.moduleComboBox.removeActionListener(this);
            chooseRandomQuestion();
            this.mainToolBar.moduleComboBox.addActionListener(this);
        } else if (evt.getSource() == botToolBar.nextButton) {

            initQuestion(moduleChanged, nextQuestion);

        } else if (evt.getSource() == botToolBar.previousButton) {

            initQuestion(moduleChanged, previousQuestion);

        } else if (evt.getSource() == mainToolBar.moduleComboBox) {
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
            appFrameLogger.log(Level.SEVERE, "Erreur: chooseRandomQuestion, le module : " + module.getName());
        }

        try {
            initQuestion(module.getName().toLowerCase(), randomQuestionNumber);
        } catch (NullPointerException e) {
            appFrameLogger.log(Level.SEVERE, "Le module est invalide, null");
        } catch (Exception e) {
            appFrameLogger.log(Level.SEVERE, "Erreur majeur : initQuestion");
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

        mainToolBar.moduleComboBox.setSelectedIndex(keys.indexOf(randomKey));

        Module module = null;
        try {
            module = modules.get(randomKey);
        } catch (NullPointerException e) {
            appFrameLogger.log(Level.SEVERE, "Erreur, getRandomModule : impossible de retrouver le module : " + randomKey);
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

        botToolBar.previousButton.setEnabled(question > 0);
        botToolBar.nextButton.setEnabled(nbQuestion - question - 1 > 0);

        try {
            questionArea =
                    new AppCenter(modules.get(module), question,
                            getNumberOfPossibleChoicesOfAQuestion(module, question));
        } catch (ClassCastException e) {
            appFrameLogger.log(Level.SEVERE, "The key is of an inappropriate type for this map, Key :" + module);

        } catch (NullPointerException e) {
            appFrameLogger.log(Level.SEVERE, "initQuestion : The specified key is null and this map does not permit null keys");
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
        moduleName = mainToolBar.moduleComboBox.getSelectedItem().toString();
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
            appFrameLogger.log(Level.SEVERE, "The key is of an inappropriate type for this map" + module);

        } catch (NullPointerException e) {
            appFrameLogger.log(Level.SEVERE, "The specified key is null and this map does not permit null keys");
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
            appFrameLogger.log(Level.SEVERE, "The key is of an inappropriate type for this map :" + module);

        } catch (NullPointerException e) {
            appFrameLogger.log(Level.SEVERE, "The specified key is null and this map does not permit null keys");
        }
        return size;
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
        top.add(mainToolBar.randomButton, BorderLayout.WEST);

        botToolBar.previousButton.setEnabled(false);

        setContentPane(container);

    }
}
