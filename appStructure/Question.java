package appStructure;

public class Question {

    private String   questionLabel;
    private String[] choixReponse;
    private int      nbChoix;
    private char     reponse;
    private String   picturePath;

    public Question(String questionLabel, String[] choixReponse, int nbChoix, char reponse, String picturePath) {

        this.setQuestionLabel(questionLabel);
        this.setNbChoix(nbChoix);
        this.setChoixReponse(choixReponse);
        this.setReponse(reponse);
        this.setPicturePath(picturePath);
    }

    /**
     * @return the questionLabel
     */
    public String getQuestionLabel() {
        return questionLabel;
    }

    /**
     * @param questionLabel
     *            the questionLabel to set
     */
    public void setQuestionLabel(String questionLabel) {
        this.questionLabel = questionLabel;
    }

    /**
     * @return the choixReponse
     */
    public String[] getChoixReponse() {
        return choixReponse;
    }

    /**
     * @param choixReponse
     *            the choixReponse to set
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
     * @param nbChoix
     *            the nbChoix to set
     */
    public void setNbChoix(int nbChoix) {
        this.nbChoix = nbChoix;
    }

    /**
     * @return the reponse
     */
    public char getReponse() {
        return reponse;
    }

    /**
     * @param reponse
     *            the reponse to set
     */
    public void setReponse(char reponse) {
        this.reponse = reponse;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath
     *            the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
