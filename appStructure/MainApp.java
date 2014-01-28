package appStructure;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import appInterface.AppFrame;
import appStructure.Module;

import org.apache.commons.lang3.text.WordUtils;

public class MainApp {
    public static void main(String[] args) {
    	Map<String, Module> questionnaires = new TreeMap<String, Module>();
    	creerQuestionaires(questionnaires);
		AppFrame AstroQuizz = new AppFrame(questionnaires);
        AstroQuizz.setVisible(true);
    }

    /**
	 *
	 */
    private static void creerQuestionaires(Map<String, Module> questionnaires) {

        File folder = new File("./Ressources");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf("."));

                questionnaires.put(WordUtils.uncapitalize(fileName), new Module(fileName));

            }
        }

    }
}
