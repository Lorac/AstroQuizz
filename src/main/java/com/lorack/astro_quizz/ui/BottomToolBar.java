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

@SuppressWarnings("serial")
public class BottomToolBar
        extends JPanel {
    // ----------------------------------------------------------------------
    // Attributs de la BottomToolBar
    // ----------------------------------------------------------------------
    public JButton nextButton = new JButton();

    public JButton previousButton = new JButton();


    // ----------------------------------------------------------------------
    // Méthodes publique
    // ----------------------------------------------------------------------

    public BottomToolBar() {
        nextButton.setText("Question Suivante");
        previousButton.setText("Question Précédente");
        setBackground(Color.LIGHT_GRAY);

        add(previousButton);
        add(nextButton);
    }

}
