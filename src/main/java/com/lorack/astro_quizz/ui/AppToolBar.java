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

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class AppToolBar extends JToolBar {

    private static final long serialVersionUID = 1L;

    public JComboBox          moduleComboBox   = new JComboBox();
    public JButton            randomButton     = new JButton();

    private JLabel            _moduleLabel     = new JLabel();
    private File              _ressources;

    public AppToolBar() {
        _ressources = new File("./Ressources/");
        setFloatable(false);
        setListFiles(_ressources);
        setBackground(Color.LIGHT_GRAY);
        setBorderPainted(false);

        randomButton.setFocusable(false);

        _moduleLabel.setText("Module :  ");
        randomButton.setText("Choisir une question aléatoirement");

        add(_moduleLabel);
        add(moduleComboBox);

    }

    private void setListFiles( File Ressources ) {
        if ( Ressources.exists() && Ressources.isDirectory() ) {
            List<File> list = new ArrayList<File>();

            for (File file : Ressources.listFiles()) {
                if ( file.getName().endsWith(".txt") ) {
                    list.add(file);
                }
            }

            if ( list.size() != 0 ) {
                Collections.sort(list);
                for (int i = 0; i < list.size(); i++) {
                    String moduleName = (String) list.get(i).getName()
                            .subSequence(0, list.get(i).getName().length() - 4);
                    moduleName = moduleName.replace('_', ' ');
                    moduleComboBox.addItem(moduleName);
                }
            }

        } else {
            System.out.println("Répertoire non trouver");
        }

    }
}
