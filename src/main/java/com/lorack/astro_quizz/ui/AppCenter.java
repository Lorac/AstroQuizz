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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lorack.astro_quizz.domain.Module;
import com.lorack.astro_quizz.domain.Question;

public class AppCenter extends JPanel {
    private static final String IMAGEPATH        = "./Ressources/images/";

    private static final String NOPICTURE        = "NO PICTURE";

    private static final long   serialVersionUID = 1L;

    public JLabel               _picture         = new JLabel();

    public JEditorPane          _question        = new JEditorPane();
    public JPanel               _reponse         = new JPanel();

    public List<JButton>        answers          = new ArrayList<JButton>();

    public List<String>         labels           = new ArrayList<String>();

    private String              _picturePath     = "";
    private final List<String>  _possibleChoices = Arrays.asList("A. ", "B. ", "C. ", "D. ", "E. ");

    private String              _questionlabel   = "";
    private int                 _questionNumber  = 0;

    /**
     * Create the app Center
     *
     * @param module
     *            The module to create with
     * @param currentQuestion
     *            the question to get
     * @param numberOfChoices
     *            Number of possible choices for that question
     */
    public AppCenter( final Module module, final int currentQuestion, int numberOfChoices ) {

        _questionNumber = currentQuestion;

        answers.clear();
        labels.clear();

        _reponse.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < numberOfChoices; i++) {
            answers.add(new JButton());
            answers.get(i).setFocusable(false);
        }

        setListenerOnJButton(module, currentQuestion, numberOfChoices);
        setLayout(new BorderLayout());

        _reponse.setLayout(new GridLayout(numberOfChoices, 1));

        for (int i = 0; i < numberOfChoices; i++) {

            answers.get(i).setBackground(Color.LIGHT_GRAY);
            answers.get(i).setBorderPainted(false);

        }

        printQuestion(module.getQuestions().get(_questionNumber), numberOfChoices);
    }

    /**
     *
     * @return int number of question
     */
    public int getCurrentQuestion() {
        return _questionNumber;
    }

    /**
     * Make the question appear on the frame
     *
     * @param theQuestion
     *            The question to make appear
     * @param numberOfChoices
     *            Number of choices of that question
     */
    private void printQuestion( Question theQuestion, int numberOfChoices ) {
        String[] lesChoixPossible = null;

        _questionlabel = theQuestion.getQuestionLabel();
        lesChoixPossible = theQuestion.getChoixReponse();

        for (int i = 0; i < numberOfChoices; i++) {

            labels.add(lesChoixPossible[i].trim());
            answers.get(i).setText(_possibleChoices.get(i) + labels.get(i));
            _reponse.add(answers.get(i));

            if ( labels.get(i).isEmpty() ) {
                answers.get(i).setVisible(false);
            } else {
                answers.get(i).setVisible(true);
            }

        }
        theQuestion.getReponse();
        _picturePath = theQuestion.getPicturePath();

        setQuestionLabelOnFrame();
        setImageOnFrame();

        setVisible(true);

    }

    /**
     * Set the picture on the frame
     *
     */
    private void setImageOnFrame() {
        if ( _picturePath.equals(NOPICTURE) ) {
            _picture.setVisible(false);
        } else {
            ImageIcon image = new ImageIcon(IMAGEPATH + _picturePath);
            Dimension imageSize = new Dimension(image.getIconWidth(), image.getIconHeight());

            _picture.setIcon(image);
            _picture.setPreferredSize(imageSize);
            _picture.setBackground(Color.LIGHT_GRAY);
            _picture.setOpaque(true);

            add(_picture, BorderLayout.EAST);
            _picture.setVisible(true);
        }
    }

    /**
     * Set Listener on each JButton of possible answers
     *
     * @param module
     *            The current module
     * @param currentQuestion
     *            The current question
     * @param numberOfChoices
     *            The number of Choices for that question
     */
    private void setListenerOnJButton( final Module module, final int currentQuestion, int numberOfChoices ) {
        for (int i = 0; i < numberOfChoices; i++) {
            answers.get(i).setName(_possibleChoices.get(i));
            answers.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked( MouseEvent e ) {
                    e.getComponent().setEnabled(false);
                    if ( module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName()
                            .charAt(0) ) {
                        e.getComponent().setBackground(Color.GREEN);

                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }

                }

                @Override
                public void mouseEntered( MouseEvent e ) {
                    if ( !(e.getComponent().getBackground() != Color.GREEN || e.getComponent().getBackground() != Color.RED) ) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }
                }

                @Override
                public void mouseExited( MouseEvent e ) {
                    if ( !(e.getComponent().getBackground() == Color.GREEN || e.getComponent().getBackground() == Color.RED) ) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }

                }

                @Override
                public void mousePressed( MouseEvent e ) {
                    e.getComponent().setEnabled(false);
                    if ( module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName()
                            .charAt(0) ) {
                        e.getComponent().setBackground(Color.GREEN);
                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }

                }

                @Override
                public void mouseReleased( MouseEvent e ) {

                }
            });
        }
    }

    /**
     * \brief Set the question label on the frame
     *
     */
    private void setQuestionLabelOnFrame() {
        _question.setContentType("text/html");
        _question.setText("<b>Question #" + (_questionNumber + 1) + "</b>:  " + _questionlabel);
        _question.setEditable(false);
        _question.setBackground(Color.LIGHT_GRAY);

        add(_question, BorderLayout.NORTH);
        add(_reponse, BorderLayout.CENTER);
    }

}
