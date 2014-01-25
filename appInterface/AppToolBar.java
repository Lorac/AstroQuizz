package appInterface;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang3.text.WordUtils;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class AppToolBar extends JToolBar {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	public JComboBox<String>	Module				= new JComboBox<String>();
	private JLabel				moduleLabel			= new JLabel();
	private File				Ressources			= new File("./Ressources");

	public AppToolBar() {
		setFloatable(false);
		setListFiles(this.Ressources);
		setBackground(Color.LIGHT_GRAY);

		this.moduleLabel.setText("Module :  ");
		add(this.moduleLabel);

		add(this.Module);
	}

	private void setListFiles(File Ressources) {
		if (Ressources.exists() && Ressources.isDirectory()) {
			List<File> list = new ArrayList<File>();

			for (File file : Ressources.listFiles()) {
				if (file.getName().endsWith(".txt")) {
					list.add(file);
				}
			}

			if (list.size() != 0 && list != null) {
				Collections.sort(list);
				for (int i = 0; i < list.size(); i++) {
					String moduleName = (String) list.get(i).getName().subSequence(0, list.get(i).getName().length() - 4);
					moduleName = moduleName.trim().replace(' ', '_');
					moduleName = WordUtils.capitalize(moduleName);
					moduleName = moduleName.replace('_', ' ');
					Module.addItem(moduleName);
				}
			}

		}
		else {
			System.out.println("Répertoire non trouver");
		}

	}
}
