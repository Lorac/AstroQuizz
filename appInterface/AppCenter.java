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
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import appStructure.Question;
import appStructure.Module;

public class AppCenter extends JPanel {
    /**
	 *
	 */
    private static final long   serialVersionUID = 1L;
    private static final String IMAGEPATH        = "./Ressources/images/";
    private static final String NOPICTURE        = "NO PICTURE";

    public JEditorPane          question         = new JEditorPane();
    public JPanel               reponse          = new JPanel();
    public JLabel               picture          = new JLabel();

    public List<String>         Labels           = new ArrayList<String>();
    private List<String>        possibleChoices  = Arrays.asList("A. ", "B. ", "C. ", "D. ", "E. ");

    private String              Questionlabel    = "";
    private String              PicturePath      = "";
    private char                GoodAnswer;

    private int                 questionNumber   = 0;
    private int                 numberofQuestion = 0;

    public List<JButton>        Answers          = new ArrayList<JButton>();

    /**
     *
     * @param Module
     * @param currentQuestion
     */
    public AppCenter(Module module, int currentQuestion, int numberOfChoices) {

        this.questionNumber = currentQuestion;

        this.Answers.clear();
        this.picture.removeAll();

        for (int i = 0; i < numberOfChoices; i++) {
            this.Answers.add(new JButton());
        }

        this.reponse.setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < numberOfChoices; i++) {
            this.Answers.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    ((JComponent) e.getSource()).setBackground(Color.LIGHT_GRAY);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ((JComponent) e.getSource()).setBackground(Color.GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ((JComponent) e.getSource()).setBackground(Color.LIGHT_GRAY);

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    ((JComponent) e.getSource()).setBackground(Color.LIGHT_GRAY);

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub

                }
            });
        }

        setLayout(new BorderLayout());

        this.reponse.setLayout(new GridLayout(numberOfChoices, 1));

        for (int i = 0; i < numberOfChoices; i++) {

            this.Answers.get(i).setBackground(Color.LIGHT_GRAY);
            this.Answers.get(i).setBorderPainted(false);

        }

        afficherLaQuestion(module, currentQuestion);
    }

    /**
     * @param Module
     * @param currentQuestion
     */
    private void afficherLaQuestion(Module module, int currentQuestion) {
        this.questionNumber = currentQuestion;
        String[] lesChoixPossible = null;
        ArrayList<Question> lesQuestions = module.getQuestions();

        if (lesQuestions == null) {
            System.out.println("afficherLesQuestions: Choix de questions vide");
            return;
        }

        Question laQuestion = lesQuestions.get(currentQuestion);
        int nbDeChoixPossible = laQuestion.getNbChoix();

        this.numberofQuestion = lesQuestions.size();

        this.Questionlabel = laQuestion.getQuestionLabel();
        lesChoixPossible = laQuestion.getChoixReponse();

        Labels.clear();
        for (int i = 0; i < nbDeChoixPossible; i++) {
            try {
                this.Labels.add(lesChoixPossible[i].trim());
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }

            this.Answers.get(i).setText(this.possibleChoices.get(i) + this.Labels.get(i));
            this.reponse.add(this.Answers.get(i));

            if (this.Labels.get(i).isEmpty()) {
                this.Answers.get(i).setVisible(false);
            } else {
                this.Answers.get(i).setVisible(true);
            }

        }
        this.GoodAnswer = laQuestion.getReponse();
        this.PicturePath = laQuestion.getPicturePath();

        this.question.setContentType("text/html");
        this.question.setText("<b> Question #" + (this.questionNumber + 1) + ":  </b>" + this.Questionlabel);
        this.question.setEditable(false);
        this.question.setBackground(Color.LIGHT_GRAY);

        add(this.question, "North");
        add(this.reponse, "Center");

        if (this.PicturePath.equals(NOPICTURE)) {
            this.picture.setVisible(false);
        } else {
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

    public void newQuestion(Module module, int currentQuestion) {

        afficherLaQuestion(module, currentQuestion);
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

}
