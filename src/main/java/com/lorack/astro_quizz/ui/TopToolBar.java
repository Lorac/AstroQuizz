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

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class TopToolBar extends JToolBar {

    private static Logger topToolBar;
    // ----------------------------------------------------------------------
    // Attributs de la TopToolBar
    // ----------------------------------------------------------------------
    private JComboBox moduleComboBox = new JComboBox();
    private JButton randomButton = new JButton();
    private JLabel moduleLabel = new JLabel();
    private File ressources;

    /**
     *
     */
    public TopToolBar() {
        ressources = new File("./Ressources/");
        setFloatable(false);
        setListFiles(ressources);
        setBackground(Color.LIGHT_GRAY);
        setBorderPainted(false);

        randomButton.setFocusable(false);

        moduleLabel.setText("Module :  ");
        randomButton.setText("Choisir une question aléatoirement");

        add(moduleLabel);
        add(moduleComboBox);

    }

    public JComboBox getModuleComboBox() {
        return moduleComboBox;
    }

    public JButton getRandomButton() {
        return randomButton;
    }

    public JLabel getModuleLabel() {
        return moduleLabel;
    }

    public File getRessources() {
        return ressources;
    }

    /**
     * @param Ressources
     */
    private void setListFiles(File Ressources) {
        if (Ressources.exists() && Ressources.isDirectory()) {
            List<File> list = new ArrayList<File>();

            for (File file : Ressources.listFiles()) {
                if (file.getName().endsWith(".txt")) {
                    list.add(file);
                }
            }

            if (list.size() != 0) {
                Collections.sort(list);
                for (int i = 0; i < list.size(); i++) {
                    String moduleName =
                            (String) list.get(i).getName().subSequence(0, list.get(i).getName().length() - 4);
                    moduleName = moduleName.replace('_', ' ');
                    moduleComboBox.addItem(moduleName);
                }
            }

        } else {
            topToolBar.log(Level.SEVERE, "Répertoire non trouver");
        }

    }
}
