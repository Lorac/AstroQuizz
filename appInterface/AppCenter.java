package appInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.text.WordUtils;

import appStructure.Question;
import appStructure.Questionnaire;

public class AppCenter extends JPanel {
	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;
	private static final String			IMAGEPATH			= "./Ressources/images/";
	private static final String			NOPICTURE			= "NO PICTURE";

	private JEditorPane					question			= new JEditorPane();
	private JPanel						reponse				= new JPanel();

	private Map<String, Questionnaire>	Questionnaires;

	private List<String>				Labels;

	private List<String>				possibleChoices		= Arrays.asList("A. ", "B. ", "C. ", "D. ", "E. ");

	private String						Questionlabel		= "";
	private char						GoodAnswer;
	private String						PicturePath			= "";

	private int							questionNumber		= 0;
	private int							numberofQuestion	= 0;
	public JLabel						picture				= new JLabel();
	public List<JButton>				Answers				= new ArrayList<JButton>();							;

	/**
	 * 
	 */
	private void creerQuestionaires() {

		File folder = new File("./Ressources");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf("."));

				this.Questionnaires.put(WordUtils.uncapitalize(fileName), new Questionnaire(fileName));

			}
		}

	}

	/**
	 * 
	 * @param module
	 * @return
	 */
	public int getNumberOfQuestion(String module) {
		module = module.trim().replace(' ', '_');
		module = WordUtils.uncapitalize(module);
		return this.Questionnaires.get(module).getSizeQuestionnaire() - 1;
	}

	/**
	 * 
	 * @param Module
	 * @param currentQuestion
	 */
	public AppCenter(String Module, int currentQuestion) {
		this.Questionnaires = new TreeMap<String, Questionnaire>();
		this.Labels = new ArrayList<String>();

		this.reponse.setBackground(Color.LIGHT_GRAY);

		setLayout(new BorderLayout());

		this.reponse.setLayout(new GridLayout(5, 1));

		creerQuestionaires();

		for (int i = 0; i < 5; i++) {
			this.Answers.add(new JButton());
		}

		for (int i = 0; i < this.Answers.size(); i++) {

			this.Answers.get(i).setBackground(Color.LIGHT_GRAY);
			this.Answers.get(i).setBorderPainted(false);

		}

		afficherLesQuestions(Module, currentQuestion);
	}

	/**
	 * @param Module
	 * @param currentQuestion
	 */
	private void afficherLesQuestions(String Module, int currentQuestion) {
		this.questionNumber = currentQuestion;
		ArrayList<Question> lesQuestions;
		String[] lesChoixPossible = new String[5];
		Questionnaire unQuestionnaire = null;

		Module = Module.trim().replace(' ', '_');
		Module = WordUtils.uncapitalize(Module);

		try {
			unQuestionnaire = this.Questionnaires.get(Module);

			if (unQuestionnaire == null) {
				System.out.println("Questionnaire vide");
				System.out.println("Module : " + Module);
			}
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

		lesQuestions = unQuestionnaire.getQuestions();

		if (lesQuestions == null) {
			System.out.println("afficherLesQuestions: Choix de questions vide");
			return;
		}
		Question laQuestion = lesQuestions.get(currentQuestion);

		this.numberofQuestion = lesQuestions.size();

		if (this.numberofQuestion > 0) {
			this.Questionlabel = laQuestion.getQuestionLabel();
			lesChoixPossible = laQuestion.getChoixReponse();

			Labels.clear();
			for (int i = 0; i < lesChoixPossible.length - 1; i++) {
				this.Labels.add(lesChoixPossible[i].trim());
			}
			this.GoodAnswer = laQuestion.getReponse();
			this.PicturePath = laQuestion.getPicturePath();
		}

		for (int i = 0; i < this.Answers.size() - 1; i++) {
			this.Answers.get(i).setText(this.possibleChoices.get(i) + this.Labels.get(i));
		}

		for (int i = 0; i < this.Answers.size(); i++) {
			this.reponse.add(this.Answers.get(i));
		}

		for (int i = 0; i < this.Answers.size() - 1; i++) {
			if (this.Labels.get(i) == "") {
				this.Answers.get(i).setVisible(false);
			}
			else {
				this.Answers.get(i).setVisible(true);
			}
		}

		this.question.setContentType("text/html");
		this.question.setText("<b> Question #" + (this.questionNumber + 1) + ":  </b>" + this.Questionlabel);
		this.question.setEditable(false);
		this.question.setBackground(Color.LIGHT_GRAY);

		add(this.question, "North");
		add(this.reponse, "Center");

		if (this.PicturePath.equals(NOPICTURE)) {
			this.picture.setVisible(false);
		}
		else {
			ImageIcon image = new ImageIcon(IMAGEPATH + this.PicturePath);
			Dimension imageSize = new Dimension(image.getIconWidth(), image.getIconHeight());

			this.picture.setIcon(image);
			this.picture.setPreferredSize(imageSize);
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
