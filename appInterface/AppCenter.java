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
package appInterface;

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
import appStructure.Module;
import appStructure.Question;

public class AppCenter extends JPanel {
    /**
	 *
	 */
    private static final long         serialVersionUID = 1L;
    private static final String       IMAGEPATH        = "./Ressources/images/";
    private static final String       NOPICTURE        = "NO PICTURE";

    public JEditorPane                question         = new JEditorPane();
    public JPanel                     reponse          = new JPanel();
    public JLabel                     picture          = new JLabel();

    public List<String>               Labels           = new ArrayList<String>();
    public List<JButton>              Answers          = new ArrayList<JButton>();

    private final List<String> possibleChoices  = Arrays.asList("A. ", "B. ", "C. ", "D. ", "E. ");

    private String                    Questionlabel    = "";
    private String                    PicturePath      = "";
    private char                      GoodAnswer;
    private int                       questionNumber   = 0;

    /**
     *
     * Create the AppCenter
     *
     * @param moduleComboBox
     * @param currentQuestion
     */
    public AppCenter(final Module module, final int currentQuestion, int numberOfChoices) {

        questionNumber = currentQuestion;

        Answers.clear();
        Labels.clear();

        if (picture.getComponentCount() > 0) {
            picture.removeAll();
        }
        reponse.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < numberOfChoices; i++) {
            Answers.add(new JButton());
            Answers.get(i).setFocusable(false);
        }

        setListenerOnJButton(module, currentQuestion, numberOfChoices);
        setLayout(new BorderLayout());

        reponse.setLayout(new GridLayout(numberOfChoices, 1));

        for (int i = 0; i < numberOfChoices; i++) {

            Answers.get(i).setBackground(Color.LIGHT_GRAY);
            Answers.get(i).setBorderPainted(false);

        }

        afficherLaQuestion(module, module.getQuestions().get(questionNumber), numberOfChoices);
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
    private void setListenerOnJButton(final Module module, final int currentQuestion, int numberOfChoices) {
        for (int i = 0; i < numberOfChoices; i++) {
            Answers.get(i).setName(possibleChoices.get(i));
            Answers.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName().charAt(0)) {
                        e.getComponent().setBackground(Color.GREEN);

                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!(e.getComponent().getBackground() != Color.GREEN || e.getComponent().getBackground() != Color.RED)) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!(e.getComponent().getBackground() == Color.GREEN || e.getComponent().getBackground() == Color.RED)) {
                        e.getComponent().setBackground(Color.LIGHT_GRAY);
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    e.getComponent().setEnabled(false);
                    if (module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName().charAt(0)) {
                        e.getComponent().setBackground(Color.GREEN);
                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (module.getQuestions().get(currentQuestion).getReponse() == e.getComponent().getName().charAt(0)) {
                        e.getComponent().setBackground(Color.GREEN);
                    } else {
                        e.getComponent().setBackground(Color.RED);
                    }

                }
            });
        }
    }

    /**
     * afficherLaQuestion
     *
     * @param moduleComboBox
     * @param currentQuestion
     */
    private void afficherLaQuestion(Module module, Question theQuestion, int numberOfChoices) {
        String[] lesChoixPossible = null;

        Questionlabel = theQuestion.getQuestionLabel();
        lesChoixPossible = theQuestion.getChoixReponse();

        for (int i = 0; i < numberOfChoices; i++) {

            Labels.add(lesChoixPossible[i].trim());
            Answers.get(i).setText(possibleChoices.get(i) + Labels.get(i));
            reponse.add(Answers.get(i));

            if (Labels.get(i).isEmpty()) {
                Answers.get(i).setVisible(false);
            } else {
                Answers.get(i).setVisible(true);
            }

        }
        GoodAnswer = theQuestion.getReponse();
        PicturePath = theQuestion.getPicturePath();

        setQuestionLabelOnFrame();
        setImageOnFrame();

        setVisible(true);

    }

    /**
     *
     */
    private void setImageOnFrame() {
        if (PicturePath.equals(NOPICTURE)) {
            picture.setVisible(false);
        } else {
            ImageIcon image = new ImageIcon(IMAGEPATH + PicturePath);
            Dimension imageSize = new Dimension(image.getIconWidth(), image.getIconHeight());

            picture.setIcon(image);
            picture.setPreferredSize(imageSize);
            picture.setBackground(Color.LIGHT_GRAY);
            picture.setOpaque(true);

            add(picture, "East");
            picture.setVisible(true);
        }
    }

    /**
     *
     */
    private void setQuestionLabelOnFrame() {
        question.setContentType("text/html");
        question.setText("<b>Question #" + (questionNumber + 1) + "</b>:  " + Questionlabel);
        question.setEditable(false);
        question.setBackground(Color.LIGHT_GRAY);

        add(question, "North");
        add(reponse, "Center");
    }

    public char getGoodAnswer() {
        return GoodAnswer;
    }

    public void setBackgroundColor() {

        for (int i = 0; i < Answers.size(); i++) {
            Answers.get(i).setBackground(Color.LIGHT_GRAY);
        }

    }

    public int getCurrentQuestion() {
        return questionNumber;
    }

    public void setCurrentQuestion(int question) {
        questionNumber = question;
    }

}
