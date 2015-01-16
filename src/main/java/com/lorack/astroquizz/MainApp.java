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

package com.lorack.astroquizz;

import com.lorack.astroquizz.domain.Module;
import com.lorack.astroquizz.ui.AppFrame;

import javax.swing.*;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MainApp {
    private static final Logger mainAppLogger = Logger.getLogger(MainApp.class.getName());

    public static void main(String[] args) {
        setLookAndFeel();
        Map<String, Module> questionnaires = new TreeMap<String, Module>();
        creerQuestionaires(questionnaires);

        AppFrame AstroQuizz = new AppFrame(questionnaires);

        AstroQuizz.setVisible(true);
    }

    /**
     *
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            mainAppLogger.log(Level.SEVERE, "Couldn't find class for specified look and feel:" + UIManager.getSystemLookAndFeelClassName());
            mainAppLogger.log(Level.SEVERE, "Did you include the L&F library in the class path?");
            mainAppLogger.log(Level.SEVERE, "Using the default look and feel.");
            mainAppLogger.log(Level.WARNING, e.getMessage());

        } catch (UnsupportedLookAndFeelException e) {
            mainAppLogger.log(Level.SEVERE, "Can't use the specified look and feel (" + UIManager.getSystemLookAndFeelClassName()
                    + ") on this platform.");
            mainAppLogger.log(Level.SEVERE, "Using the default look and feel.");
            mainAppLogger.log(Level.WARNING, e.getMessage());

        } catch (Exception e) {
            mainAppLogger.log(Level.SEVERE, "Couldn't get specified look and feel (" + UIManager.getSystemLookAndFeelClassName()
                    + "), for some reason.");
            mainAppLogger.log(Level.SEVERE, "Using the default look and feel.");
            mainAppLogger.log(Level.WARNING, e.getMessage());

        }
    }


    /**
     * Création de toutes les questionnaires
     *
     * @param questionnaires
     */
    private static void creerQuestionaires(Map<String, Module> questionnaires) {
        File folder = new File("./Ressources");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf("."));

                questionnaires.put(fileName.toLowerCase(), new Module(fileName));

            }
        }

    }
}
