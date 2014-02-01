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

package appStructure;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang3.text.WordUtils;

import appInterface.AppFrame;

public abstract class MainApp {
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
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel:" + "javax.swing.plaf.metal.MetalLookAndFeel");
            System.err.println("Did you include the L&F library in the class path?");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel (" + "javax.swing.plaf.metal.MetalLookAndFeel" + ") on this platform.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel (" + "javax.swing.plaf.metal.MetalLookAndFeel" + "), for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param questionnaires
     */
    private static void creerQuestionaires(Map<String, Module> questionnaires) {

        File folder = new File("./Ressources");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf("."));

                questionnaires.put(WordUtils.uncapitalize(fileName), new Module(fileName));

            }
        }

    }
}
