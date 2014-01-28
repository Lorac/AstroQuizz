package appStructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Module {

	private ArrayList<Question> Questions;
	private String _name;
	private int _size;

	public Module(String module) {
		this.Questions = new ArrayList<Question>();
		construireQuestionnaire(module);
	}

	public ArrayList<Question> getQuestions() {
		return Questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		Questions = questions;
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
						if (!LigneLue.isEmpty()) {
							choixReponse[i] = (LigneLue);
							nbChoix++;
						}
					}
					reponse = fluxEntree.readLine().charAt(0);
					picturePath = fluxEntree.readLine();

					Questions.add(new Question(questionLabel, choixReponse,
							nbChoix, reponse, picturePath));
					nbChoix = 0;

				} while (questionLabel != null);
				this.setName(module);
				this.setSize(Questions.size());

			} catch (IOException exc) {
				exc.printStackTrace();
			}
			try {
				fluxEntree.close();
			} catch (IOException localIOException1) {
			}
		}
	}

	/**
	 * @return the _name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * @param _name
	 *            the _name to set
	 */
	public void setName(String _name) {
		this._name = _name;
	}

    /**
     * @return the _size
     */
    public int getSize() {
        return _size;
    }

    /**
     * @param _size the _size to set
     */
    public void setSize(int _size) {
        this._size = _size;
    }
}
