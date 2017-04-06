package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import xml.xmlReader;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class ManPages {

	private JFrame frame;
	private JTextField textField;
	private xmlReader reader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManPages window = new ManPages();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManPages() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		reader = new xmlReader("./src/xml/manpages.xml"); // The XML file location goes here
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		// Information Pane
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 1));
		
		JScrollPane topicScroll = new JScrollPane();
		panel.add(topicScroll);
		
		JScrollPane summaryScroll = new JScrollPane();
		panel.add(summaryScroll);
		
		JScrollPane detailsScroll = new JScrollPane();
		panel.add(detailsScroll);
	
		JTextPane topicPane = new JTextPane();
		topicPane.setEditable(false);
		topicScroll.setViewportView(topicPane);
		
		JTextPane summaryPane = new JTextPane();
		summaryPane.setEditable(false);
		summaryScroll.setViewportView(summaryPane);
		
		JTextPane detailsPane = new JTextPane();
		detailsPane.setEditable(false);
		detailsScroll.setViewportView(detailsPane);

		// Selection Pane
		JPanel panel2 = new JPanel();
		splitPane.setLeftComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Commands") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Frequently Used");
						node_1.add(new DefaultMutableTreeNode("ls"));
						node_1.add(new DefaultMutableTreeNode("cd"));
						node_1.add(new DefaultMutableTreeNode("mkdir"));
						node_1.add(new DefaultMutableTreeNode("rmdir"));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("A");
						node_1.add(new DefaultMutableTreeNode("alias"));
						node_1.add(new DefaultMutableTreeNode("ar"));
						node_1.add(new DefaultMutableTreeNode("..."));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("B");
						node_1.add(new DefaultMutableTreeNode("basename"));
						node_1.add(new DefaultMutableTreeNode("batch"));
						node_1.add(new DefaultMutableTreeNode("..."));
						node_1.add(new DefaultMutableTreeNode("..."));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("...");
						node_1.add(new DefaultMutableTreeNode(""));
						add(node_1);
						node_1 = new DefaultMutableTreeNode("Z");
						node_1.add(new DefaultMutableTreeNode(""));
						add(node_1);
					}
				}
				));
		panel2.add(tree, BorderLayout.CENTER);

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setEnabled(false);
		panel2.add(splitPane_1, BorderLayout.NORTH);

		textField = new JTextField();
		splitPane_1.setLeftComponent(textField);
		textField.setColumns(15);

		JButton searchButton = new JButton("Search");
		splitPane_1.setRightComponent(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load the command found in the search dialog
				String searchText = textField.getText();
				ArrayList<String> manPage = reader.returnPage(searchText);
				topicPane.setText(manPage.get(0));
				summaryPane.setText(manPage.get(1));
				detailsPane.setText(manPage.get(2));
			}
		});		
	}

}
