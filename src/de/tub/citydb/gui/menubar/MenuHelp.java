package de.tub.citydb.gui.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import de.tub.citydb.config.internal.Internal;
import de.tub.citydb.gui.ImpExpGui;
import de.tub.citydb.gui.util.GuiUtil;

@SuppressWarnings("serial")
public class MenuHelp extends JMenu {
	private JMenuItem info;
	private JMenuItem readMe;
	
	public MenuHelp() {
		init();
	}
	
	private void init() {
		info = new JMenuItem();
		readMe = new JMenuItem();
		
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printHelp();
			}
		});
		
		readMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printReadMe();
			}
		});
		
		add(info);
		add(readMe);
	}
	
	public void doTranslation() {
		info.setText(Internal.I18N.getString("menu.file.info.label"));		
		readMe.setText(Internal.I18N.getString("menu.file.readMe.label"));
		
		GuiUtil.setMnemonic(info, "menu.file.info.label", "menu.file.info.label.mnemonic");
		GuiUtil.setMnemonic(readMe, "menu.file.readMe.label", "menu.file.readMe.label.mnemonic");
	}
	
	private void printHelp() {		
		final InfoDialog infoDialog = new InfoDialog((ImpExpGui)getTopLevelAncestor());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				infoDialog.setLocationRelativeTo(getTopLevelAncestor());
				infoDialog.setVisible(true);
			}
		});
	}
	
	private void printReadMe() {		
		final ReadMeDialog readMeDialog = new ReadMeDialog((ImpExpGui)getTopLevelAncestor());
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				readMeDialog.setLocationRelativeTo(getTopLevelAncestor());
				readMeDialog.setVisible(true);
			}
		});
	}
}
