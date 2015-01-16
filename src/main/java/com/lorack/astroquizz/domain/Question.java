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

package com.lorack.astroquizz.domain;

public abstract class Question {
    // ----------------------------------------------------------------------
    // Attributs d'une Question
    // ----------------------------------------------------------------------

    private String[] choixReponse;
    private String picturePath;
    private String questionLabel;
    private int nbChoix;
    private char reponse;

    // ----------------------------------------------------------------------
    // Méthodes publique
    // ----------------------------------------------------------------------

    /**
     * @param questionLabel
     * @param choixReponse
     * @param nbChoix
     * @param reponse
     * @param picturePath
     */
    public Question(String questionLabel, String[] choixReponse, int nbChoix, char reponse, String picturePath) {

        setQuestionLabel(questionLabel);
        setNbChoix(nbChoix);
        setChoixReponse(choixReponse);
        setReponse(reponse);
        setPicturePath(picturePath);
    }

    /**
     * @return the choixReponse
     */
    public String[] getChoixReponse() {
        return choixReponse;
    }

    /**
     * @param choixReponse the choixReponse to set
     */
    public void setChoixReponse(String[] choixReponse) {

        this.choixReponse = new String[choixReponse.length];
        for (int i = 0; i < choixReponse.length; i++) {
            this.choixReponse[i] = choixReponse[i];
        }
    }

    /**
     * @return the nbChoix
     */
    public int getNbChoix() {
        return nbChoix;
    }

    /**
     * @param nbChoix the nbChoix to set
     */
    public void setNbChoix(int nbChoix) {
        this.nbChoix = nbChoix;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * @return the questionLabel
     */
    public String getQuestionLabel() {
        return questionLabel;
    }

    /**
     * @param questionLabel the questionLabel to set
     */
    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    /**
     * @return the reponse
     */
    public char getReponse() {
        return reponse;
    }

    /**
     * @param reponse the reponse to set
     */
    public void setReponse(char reponse) {
        this.reponse = reponse;
    }
}
