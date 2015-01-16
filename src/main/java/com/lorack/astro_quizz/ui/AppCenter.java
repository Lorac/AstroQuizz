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
import com.lorack.astro_quizz.domain.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class AppCenter extends JPanel {
    private static final String IMAGEPATH = "./Ressources/images/";
    private static final String NOPICTURE = "NO PICTURE";
    private final List<String> possibleChoices = Arrays.asList("A. ", "B. ", "C. ", "D. ", "E. ");
    private JEditorPane question = new JEditorPane();
    private JLabel picture = new JLabel();
    private JPanel reponse = new JPanel();
    private List<JButton> answers = new ArrayList<JButton>();
    public List<String> labels = new ArrayList<String>();
    private String picturePath = "";
    private String questionlabel = "";

    private int questionNumber = 0;

    /**
     * Create the app Center
     *
     * @param module          The module to create with
     * @param currentQuestion the question to get
     * @param numberOfChoices Number of possible choices for that question
     */
    public AppCenter(final Module module, final int currentQuestion, int numberOfChoices) {

        questionNumber = currentQuestion;

        answers.clear();
        labels.clear();

        reponse.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < numberOfChoices; i++) {
            answers.add(new JButton());
            answers.get(i).setFocusable(false);
        }

        setListenerOnJButton(module, currentQuestion, numberOfChoices);
        setLayout(new BorderLayout());

        reponse.setLayout(new GridLayout(numberOfChoices, 1));

        for (int i = 0; i < numberOfChoices; i++) {

            answers.get(i).setBackground(Color.LIGHT_GRAY);
            answers.get(i).setBorderPainted(false);

        }

        printQuestion(module.getQuestions().get(questionNumber), numberOfChoices);
    }

    /**
     * @return int number of question
     */
    public int getCurrentQuestion() {
        return questionNumber;
    }

    /**
     * Make the question appear on the frame
     *
     * @param theQuestion     The question to make appear
     * @param numberOfChoices Number of choices of that question
     */
    private void printQuestion(Question theQuestion, int numberOfChoices) {
        String[] lesChoixPossible = null;

        questionlabel = theQuestion.getQuestionLabel();
        lesChoixPossible = theQuestion.getChoixReponse();

        for (int i = 0; i < numberOfChoices; i++) {

            labels.add(lesChoixPossible[i].trim());
            answers.get(i).setText(possibleChoices.get(i) + labels.get(i));
            reponse.add(answers.get(i));

            if (labels.get(i).isEmpty()) {
                answers.get(i).setVisible(false);
            } else {
                answers.get(i).setVisible(true);
            }

        }
        theQuestion.getReponse();
        picturePath = theQuestion.getPicturePath();

        setQuestionLabelOnFrame();
        setImageOnFrame();

        setVisible(true);

    }

    /**
     * Set the picture on the frame
     */
    private void setImageOnFrame() {
        if (picturePath.equals(NOPICTURE)) {
            picture.setVisible(false);
        } else {
            ImageIcon image = new ImageIcon(IMAGEPATH + picturePath);
            Dimension imageSize = new Dimension(image.getIconWidth(), image.getIconHeight());

            picture.setIcon(image);
            picture.setPreferredSize(imageSize);
            picture.setBackground(Color.LIGHT_GRAY);
            picture.setOpaque(true);

            add(picture, BorderLayout.EAST);
            picture.setVisible(true);
        }
    }

    /**
     * Set Listener on each JButton of possible answers
     *
     * @param module          The current module
     * @param currentQuestion The current question
     * @param numberOfChoices The number of Choices for that question
     */
    private void setListenerOnJButton(final Module module, final int currentQuestion, int numberOfChoices) {
        for (int i = 0; i < numberOfChoices; i++) {
            answers.get(i).setName(possibleChoices.get(i));
            answers.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    e.getComponent().setEnabled(false);
                    if (module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName().charAt(0)) {
                        e.getComponent().setBackground(Color.GREEN);

                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }
                    ((JComponent) e.getComponent()).setOpaque(true);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!(e.getComponent().getBackground() != Color.GREEN || e.getComponent().getBackground() != Color.RED)) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }
                    ((JComponent) e.getComponent()).setOpaque(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!(e.getComponent().getBackground() == Color.GREEN || e.getComponent().getBackground() == Color.RED)) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }
                    ((JComponent) e.getComponent()).setOpaque(true);

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    e.getComponent().setEnabled(false);
                    if (module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName().charAt(0)) {
                        e.getComponent().setBackground(Color.GREEN);
                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }
                    ((JComponent) e.getComponent()).setOpaque(true);

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }
            });
        }
    }

    /**
     * \brief Set the question label on the frame
     */
    private void setQuestionLabelOnFrame() {
        question.setContentType("text/html");
        question.setText("<b>Question #" + (questionNumber + 1) + "</b>:  " + questionlabel);
        question.setEditable(false);
        question.setBackground(Color.LIGHT_GRAY);
        question.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(question, BorderLayout.NORTH);
        add(reponse, BorderLayout.CENTER);
    }

}
