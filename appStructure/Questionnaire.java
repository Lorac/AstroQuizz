package appStructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Questionnaire {

    private ArrayList<Question> Questions;

    public Questionnaire() {

    }

    public Questionnaire(String module) {
        this.Questions = new ArrayList<Question>();
        construireQuestionnaire(module);
    }

    /**
     * @return the list of questions
     */
    public ArrayList<Question> getQuestions() {
        return Questions;
    }

    /**
     * @param questions
     *            the questions to set
     */
    public void setQuestions(ArrayList<Question> questions) {
        Questions = questions;
    }

    /**
     * 
     * @return
     */
    public int getSizeQuestionnaire() {
        return this.Questions.size();
    }

    public void construireQuestionnaire(String module) {
        BufferedReader fluxEntree = null;
        String questionLabel;
        String[] choixReponse = new String[5];
        int nbChoix = 0;

        char reponse;
        String LigneLue;
        String picturePath;

        int i = 0;
        if (module != "") {
            try {
                fluxEntree = new BufferedReader(new InputStreamReader(new FileInputStream("./Ressources/" + module + ".txt"), "UTF-8"));
                do {

                    questionLabel = fluxEntree.readLine();
                    if (questionLabel == null) {
                        break;
                    }
                    for (i = 0; i < 5; i++) {
                        LigneLue = fluxEntree.readLine();
                        if (!LigneLue.isEmpty()) {
                            choixReponse[i] = (LigneLue);
                            nbChoix++;
                        }
                    }
                    reponse = fluxEntree.readLine().charAt(0);
                    picturePath = fluxEntree.readLine();

                    Questions.add(new Question(questionLabel, choixReponse, nbChoix, reponse, picturePath));
                    nbChoix = 0;

                }
                while (questionLabel != null);

            } catch (IOException exc) {
                exc.printStackTrace();
            }
            try {
                fluxEntree.close();
            } catch (IOException localIOException1) {
            }
        }
    }
}
