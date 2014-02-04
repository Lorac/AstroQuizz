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

import javax.swing.JButton;
import javax.swing.JPanel;

public class AppBotToolBar extends JPanel {
    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    public JButton            nextButton       = new JButton();
    public JButton            previousButton   = new JButton();

    public AppBotToolBar() {
        nextButton.setText("Question Suivante");
        previousButton.setText("Question Précédente");
        setBackground(Color.LIGHT_GRAY);

        add(previousButton);
        add(nextButton);
    }

}
