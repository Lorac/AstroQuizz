package appInterface;

import appStructure.Question;
import appStructure.Questionnaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.text.WordUtils;

public class AppCenter extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String IMAGEPATH = "./Ressources/images/";
	private static final String NOPICTURE = "NO PICTURE";

	private JEditorPane question = new JEditorPane();
	private JPanel reponse = new JPanel();

	private Map<String, Questionnaire> Questionnaires;

	private List<String> Labels;
	public List<JButton> Answers;
	private List<String> possibleChoices = Arrays.asList("A. ", "B. ", "C. ",
			"D. ", "E. ");

	private String Questionlabel = "";
	private char GoodAnswer;
	private String PicturePath = "";

	private int questionNumber = 0;
	private int numberofQuestion = 0;
	private int indice = 0;

	public JLabel picture = new JLabel();

	private void creerQuestionaires() {

		File folder = new File("./Ressources");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName().substring(0,
						listOfFiles[i].getName().lastIndexOf("."));

				this.Questionnaires.put(fileName, new Questionnaire(fileName));
			}
		}

	}

	public int getNumberOfQuestion(String module) {
		setnumberOfQuestion(module);
		return this.numberofQuestion;
	}

	public AppCenter(String Module, int currentQuestion) {
		this.Questionnaires = new TreeMap<String, Questionnaire>();
		this.Labels = new ArrayList<String>();
		this.Answers = new ArrayList<JButton>();

		this.reponse.setBackground(Color.LIGHT_GRAY);

		for (int i = 0; i < this.Answers.size(); i++) {
			this.Answers.get(i).setBackground(Color.LIGHT_GRAY);
			this.Answers.get(i).setBorderPainted(false);

		}

		setLayout(new BorderLayout());

		this.reponse.setLayout(new GridLayout(5, 1));
		creerQuestionaires();

		afficherLesQuestions(WordUtils.uncapitalize(Module), currentQuestion);
	}

	/**
	 * @param Module
	 * @param currentQuestion
	 */
	private void afficherLesQuestions(String Module, int currentQuestion) {
		this.questionNumber = currentQuestion;
		List<Question> lesQuestions = new ArrayList<Question>();
		List<String> lesChoixPossible = new ArrayList<String>();
		Questionnaire unQuestionnaire = null;

		try {
			unQuestionnaire = this.Questionnaires.get(Module);
			if (unQuestionnaire == null) {
				System.out.println("Questionnaire vide");
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		lesQuestions = unQuestionnaire.getQuestions();
		Question laQuestion = lesQuestions.get(currentQuestion);

		this.numberofQuestion = lesQuestions.size();

		if (this.numberofQuestion > 0) {

			this.Questionlabel = laQuestion.getQuestionLabel();
			lesChoixPossible = laQuestion.getChoixReponse();

			for (int i = 0; i < lesChoixPossible.size(); i++) {
				this.Labels.add(lesChoixPossible.get(i).trim());
				this.Answers.add(new JButton());
			}
			this.GoodAnswer = laQuestion.getReponse();
			this.PicturePath = laQuestion.getPicturePath();
		}

		for (int i = 0; i < this.Answers.size(); i++) {
			this.Answers.get(i).setText(
					this.possibleChoices.get(i) + this.Labels.get(i));
		}
		for (int i = 0; i < this.Answers.size(); i++) {
			this.reponse.add(this.Answers.get(i));
		}

		for (int i = 0; i < this.Answers.size(); i++) {
			if (this.Labels.get(i) == "") {
				this.Answers.get(i).setVisible(false);
			}
		}

		this.question.setContentType("text/html");
		this.question.setText("<b> Question #" + this.questionNumber + 1
				+ ":  </b>" + this.Questionlabel);
		this.question.setEditable(false);
		this.question.setBackground(Color.LIGHT_GRAY);
		add(this.question, "North");
		add(this.reponse, "Center");
		if (this.PicturePath.equals(NOPICTURE)) {
			this.picture.setVisible(false);
		} else {
			ImageIcon image = new ImageIcon(IMAGEPATH + this.PicturePath);
			int Height = image.getIconHeight();
			int Width = image.getIconWidth();
			Dimension test = new Dimension(Width, Height);
			this.picture.setIcon(image);
			this.picture.setPreferredSize(test);
			this.picture.setBackground(Color.LIGHT_GRAY);
			this.picture.setOpaque(true);
			add(this.picture, "East");
			this.picture.setVisible(true);
		}
		setVisible(true);
	}

	public char getGoodAnswer() {
		return this.GoodAnswer;
	}

	public void newQuestion(String Module, int currentQuestion) {
		afficherLesQuestions(WordUtils.uncapitalize(Module), currentQuestion);
	}

	public void setBackgroundColor() {

		for (int i = 0; i < this.Answers.size(); i++) {
			this.Answers.get(i).setBackground(Color.LIGHT_GRAY);
		}

	}

	public int getCurrentQuestion() {
		return this.questionNumber;
	}

	public void setCurrentQuestion(int question) {
		this.questionNumber = question;
	}

	public void setnumberOfQuestion(String module) {

	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}
}
