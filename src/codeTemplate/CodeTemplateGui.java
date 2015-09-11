package codeTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import stringUtils.StringReplace;

/**
 * @author TeamworkGuy2
 * @since 2014-8-3
 */
public class CodeTemplateGui {
	private String title;
	private JFrame window;
	private JTextArea inputText;
	private JTextArea outputText;
	private JButton addReplacementPanelButton;
	private List<ReplacementsPanel> replacementPanels;
	private ActionListener applyAction;


	public CodeTemplateGui(String title) {
		this.title = title;
	}


	public void createAndDisplay() {
		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Specify an action for the close button.

		// Create and set the menu bar as well as the main content pane
		window.add( buildPanel() ); // Builds the entire main panel, including buttons, labels, and scroll panes, as well as assigning listeners

		// Display the window.
		window.pack(); // If size is not explicitly set using setSize or setBounds
		window.setVisible(true); // Make the window visible
	}


	private JPanel buildPanel() {
		applyAction = new ApplyTemplate();

		JPanel inputPane = new JPanel();
		inputText = new JTextArea(20, 60);
		JScrollPane inputTextPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		inputTextPane.setViewportView(inputText);
		inputPane.add(inputTextPane);

		JPanel replacementPanel = new JPanel();
		replacementPanel.setLayout(new BoxLayout(replacementPanel, BoxLayout.PAGE_AXIS));
		replacementPanels = new ArrayList<ReplacementsPanel>();

		addReplacementPanelButton = new JButton("Add Replacement Panel");
		addReplacementPanelButton.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				ReplacementsPanel newPanel = new ReplacementsPanel();
				replacementPanels.add(newPanel);
				replacementPanel.add(newPanel.panel);
				window.revalidate();
			}
		});
		replacementPanel.add(addReplacementPanelButton);

		ReplacementsPanel newReplacementPanel = new ReplacementsPanel();
		replacementPanels.add(newReplacementPanel);
		replacementPanel.add(newReplacementPanel.panel);

		JPanel outputPane = new JPanel();
		outputPane.setLayout(new BoxLayout(outputPane, BoxLayout.PAGE_AXIS));

		JButton processButton = new JButton("Apply Templates");
		processButton.addActionListener(applyAction);
		outputPane.add(processButton);

		outputText = new JTextArea(20, 60);
		JScrollPane outputTextPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outputTextPane.setViewportView(outputText);
		outputPane.add(outputTextPane);

		JPanel mainPane = new JPanel();

		mainPane.add(inputPane);
		mainPane.add(replacementPanel);
		mainPane.add(outputPane);

		return mainPane;
	}


	public static class ReplacementsPanel {
		JPanel panel;
		JButton addReplacementButton;
		JLabel label;
		JLabel keyLabel;
		JTextField searchToken;
		JPanel replacementsPanel;
		List<JTextField> replacements;

		public ReplacementsPanel() {
			this.panel = new JPanel();
			this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
			this.label = new JLabel("Key and Replacements");
			this.keyLabel = new JLabel("Key");
			this.replacementsPanel = new JPanel();
			this.replacementsPanel.setLayout(new BoxLayout(this.replacementsPanel, BoxLayout.PAGE_AXIS));
			this.searchToken = new JTextField(30);
			this.replacements = new ArrayList<JTextField>();
			addNewField();
			this.addReplacementButton = new JButton("Add Replacement Key");
			this.addReplacementButton.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					addNewField();
				}
			});
			panel.add(label);
			JPanel pTemp = new JPanel();
			pTemp.add(keyLabel);
			pTemp.add(searchToken);
			panel.add(pTemp);
			panel.add(replacementsPanel);
			panel.add(addReplacementButton);
		}

		private void addNewField() {
			JTextField field = new JTextField(30);
			ReplacementsPanel.this.replacements.add(field);
			ReplacementsPanel.this.replacementsPanel.add(field);
			ReplacementsPanel.this.replacementsPanel.revalidate();
		}

	}


	public class ApplyTemplate implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputText = CodeTemplateGui.this.inputText.getText();
			List<ReplacementsPanel> replacementPanels = CodeTemplateGui.this.replacementPanels;
			String[] keys = new String[replacementPanels.size()];
			int i = 0;
			int replacementCount = Integer.MAX_VALUE;
			// Get each panel's replacement key and the number of replacements associated with each panel
			for(ReplacementsPanel panel : replacementPanels) {
				keys[i] = panel.searchToken.getText();
				if(replacementCount > panel.replacements.size()) {
					replacementCount = panel.replacements.size();
				}
				i++;
			}

			String[][] replacements = new String[replacementCount][];
			for(int ri = 0, size = replacements.length; ri < size; ri++) {
				replacements[ri] = new String[replacementPanels.size()];
			}

			int panelI = 0;
			for(ReplacementsPanel panel : replacementPanels) {
				int ii = 0;
				for(JTextField field : panel.replacements) {
					// replace char escape literals
					replacements[ii][panelI] = StringReplace.replaceEscapeLiterals(field.getText());
					ii++;
				}
				panelI++;
			}

			try {
				String result = CodeTemplates.generateFrom(inputText, keys, replacements, false, null, 0, 0);

				List<String> results = new ArrayList<String>();
				results.add(result);
				StringBuilder strB = new StringBuilder();
				for(String text : results) {
					strB.append(text);
					strB.append('\n');
				}
				CodeTemplateGui.this.outputText.setText(result);
			} catch(Exception err) {
				CodeTemplateGui.this.outputText.setText("Error: " + err.getLocalizedMessage());
			}
		}
		
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				CodeTemplateGui gui = new CodeTemplateGui("Code Template UI");
				gui.createAndDisplay();
			}
		});
	}

}
