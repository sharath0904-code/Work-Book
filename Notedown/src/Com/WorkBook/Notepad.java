package Com.WorkBook;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener {

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu help = new JMenu("Help");

	JMenuItem newFile = new JMenuItem("New");
	JMenuItem openFile = new JMenuItem("Open");
	JMenuItem saveFile = new JMenuItem("Save");
	JMenuItem print = new JMenuItem("Print");
	JMenuItem exit = new JMenuItem("Exit");

	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem selectAll = new JMenuItem("SelectAll");

	JMenuItem about = new JMenuItem("About");

	JTextArea textarea = new JTextArea();

		Notepad() {
			setTitle("Work Book");
			setBounds(100, 100, 800, 600);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ImageIcon icon = new ImageIcon(getClass().getResource("Notepad.png"));
			setIconImage(icon.getImage());

			setJMenuBar(menubar);
			menubar.add(file);
			menubar.add(edit);
			menubar.add(help);

			file.add(newFile);
			file.add(openFile);
			file.add(saveFile);
			file.add(print);
			file.add(exit);

			edit.add(cut);
			edit.add(copy);
			edit.add(paste);
			edit.add(selectAll);
			help.add(about);

			JScrollPane Scroll = new JScrollPane(textarea);
			add(Scroll);
			textarea.setFont(new Font("SANS_SARIF", Font.PLAIN, 20));
			Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			Scroll.setBorder(BorderFactory.createEmptyBorder());
			textarea.setLineWrap(true);
			textarea.setWrapStyleWord(true);

			newFile.addActionListener(this);
			openFile.addActionListener(this);
			saveFile.addActionListener(this);
			print.addActionListener(this);
			exit.addActionListener(this);
			cut.addActionListener(this);
			copy.addActionListener(this);
			paste.addActionListener(this);
			selectAll.addActionListener(this);
			about.addActionListener(this);

			newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
			openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
			saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
			print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
			exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
			cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
			copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
			paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
			selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
			about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_DOWN_MASK));

		}

		public static void main(String[] args) throws Exception {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			new Notepad().setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("New")) {
				textarea.setText(null);
			} else if (e.getActionCommand().equalsIgnoreCase("Save")) {

				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(textFilter);

				int action = fileChooser.showSaveDialog(null);
				if (action != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
					if (!fileName.contains(".txt")) {
						fileName = fileName + ".txt";
					}

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
						textarea.write(writer);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
			} else if (e.getActionCommand().equalsIgnoreCase("Open")) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(textFilter);

				int action = fileChooser.showSaveDialog(null);
				if (action != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
					if (!fileName.contains(".txt")) {
						fileName = fileName + ".txt";
					}

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
						textarea.write(writer);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}

			} else if (e.getActionCommand().equalsIgnoreCase("Print")) {
				try {
					textarea.print();
				} catch (PrinterException ex) {
					Logger.getLogger(Notepad.class.getName()).log(Level.SEVERE, null, ex);
				}

			} else if (e.getActionCommand().equalsIgnoreCase("Exit")) {

				System.exit(0);

			} else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
				textarea.cut();

			} else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
				textarea.copy();
			} else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
				textarea.paste();
			} else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
				textarea.selectAll();
			} else if (e.getActionCommand().equalsIgnoreCase("About")) {
				new About().setVisible(true);

			}

		}

	}



