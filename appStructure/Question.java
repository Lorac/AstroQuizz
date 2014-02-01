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

public class Question {

    private String[] _choixReponse;
    private String   _picturePath;
    private String   _questionLabel;

    private int      _nbChoix;

    private char     _reponse;

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
        return _choixReponse;
    }

    /**
     * @return the nbChoix
     */
    public int getNbChoix() {
        return _nbChoix;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return _picturePath;
    }

    /**
     * @return the questionLabel
     */
    public String getQuestionLabel() {
        return _questionLabel;
    }

    /**
     * @return the reponse
     */
    public char getReponse() {
        return _reponse;
    }

    /**
     * @param choixReponse
     *            the choixReponse to set
     */
    public void setChoixReponse(String[] choixReponse) {

        this._choixReponse = new String[choixReponse.length];
        for (int i = 0; i < choixReponse.length; i++) {
            this._choixReponse[i] = choixReponse[i];
        }
    }

    /**
     * @param nbChoix
     *            the nbChoix to set
     */
    public void setNbChoix(int nbChoix) {
        this._nbChoix = nbChoix;
    }

    /**
     * @param picturePath
     *            the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this._picturePath = picturePath;
    }

    /**
     * @param questionLabel
     *            the questionLabel to set
     */
    public void setQuestionLabel(String questionLabel) {
        this._questionLabel = questionLabel;
    }

    /**
     * @param reponse
     *            the reponse to set
     */
    public void setReponse(char reponse) {
        this._reponse = reponse;
    }
}
