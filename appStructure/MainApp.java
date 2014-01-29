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

import appInterface.AppFrame;
import appStructure.Module;

import org.apache.commons.lang3.text.WordUtils;

public class MainApp {
    public static void main(String[] args) {
    	Map<String, Module> questionnaires = new TreeMap<String, Module>();
    	creerQuestionaires(questionnaires);

		AppFrame AstroQuizz = new AppFrame(questionnaires);
	      try {
	            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (InstantiationException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (UnsupportedLookAndFeelException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
        AstroQuizz.setVisible(true);
    }

    /**
	 *
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
