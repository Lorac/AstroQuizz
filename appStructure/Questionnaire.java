package appStructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Questionnaire {

	private List<Question> Questions;

	public Questionnaire() {

	}

	public Questionnaire(String module) {
		this.Questions = new ArrayList<Question>();
		construireQuestionnaire(module);
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return Questions;
	}

	/**
	 * @param questions
	 *            the questions to set
	 */
	public void setQuestions(List<Question> questions) {
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
		List<String> choixReponse = new ArrayList<String>();
		char reponse;
		String LigneLue;
		String picturePath;

		int i = 0;
		if (module != "") {
			try {
				fluxEntree = new BufferedReader(new InputStreamReader(
						new FileInputStream("./Ressources/" + module + ".txt"),
						"UTF-8"));
				do {

					questionLabel = fluxEntree.readLine();
					if (questionLabel == null) {
						break;
					}
					for (i = 0; i < 5; i++) {
						LigneLue = fluxEntree.readLine();
						choixReponse.add(LigneLue);

					}
					reponse = fluxEntree.readLine().charAt(0);
					picturePath = fluxEntree.readLine();
					Questions.add(new Question(questionLabel, choixReponse,
							choixReponse.size(), reponse, picturePath));

					choixReponse.clear();

				} while (questionLabel != null);

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
