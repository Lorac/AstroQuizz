package com.lorack.astroquizz.ui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class SwingInterface {
    private JMenuBar mainMenuBar;
    private JPanel mainPanel = new JPanel();

    public SwingInterface() {
        mainMenuBar = new JMenuBar();
        setUpMainMenu();
    }

    private void setUpMainMenu() {
        JMenu fileMenu = new JMenu("A Menu");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        mainMenuBar.add(fileMenu);
    }
}
