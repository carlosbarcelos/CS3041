package window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import xml.xmlReader;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.SystemColor;
import java.awt.Font;

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
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Man Pages 2.0");
		frame.setLocationRelativeTo(null);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);


		/***** Information Pane *****/
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane topicScroll = new JScrollPane();
		panel.add(topicScroll);

		JScrollPane summaryScroll = new JScrollPane();
		panel.add(summaryScroll);

		JScrollPane detailsScroll = new JScrollPane();
		panel.add(detailsScroll);

		JTextPane topicPane = new JTextPane();
		topicPane.setFont(new Font("Courier New", Font.BOLD, 14));
		topicPane.setForeground(Color.WHITE);
		topicPane.setText("Topic Pane: Name and usage information");
		topicPane.setToolTipText("Topic Pane: Name and usage information");
		topicPane.setBackground(Color.DARK_GRAY);
		topicPane.setEditable(false);
		topicScroll.setViewportView(topicPane);

		JTextPane summaryPane = new JTextPane();
		summaryPane.setFont(new Font("Courier New", Font.BOLD, 14));
		summaryPane.setForeground(Color.WHITE);
		summaryPane.setText("Summary Pane: Command description and usage");
		summaryPane.setToolTipText("Summary Pane: Command description and usage");
		summaryPane.setBackground(Color.DARK_GRAY);
		summaryPane.setEditable(false);
		summaryScroll.setViewportView(summaryPane);

		JTextPane detailsPane = new JTextPane();
		detailsPane.setFont(new Font("Courier New", Font.BOLD, 14));
		detailsPane.setForeground(Color.WHITE);
		detailsPane.setText("Details Pane: Command syntax, flags, etc.");
		detailsPane.setToolTipText("Details Pane: Command syntax, flags, etc.");
		detailsPane.setBackground(Color.DARK_GRAY);
		detailsPane.setEditable(false);
		detailsScroll.setViewportView(detailsPane);

		/***** Selection Pane *****/
		// Selection Tree
		JPanel panel2 = new JPanel();
		panel2.setBackground(SystemColor.inactiveCaption);
		splitPane.setLeftComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		JTree tree = new JTree();
		tree.setBackground(SystemColor.menu);
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

		// Selection Text Box		
		JSplitPane splitPane_1 = new JSplitPane();
		panel2.add(splitPane_1, BorderLayout.NORTH);

		textField = new JTextField();
		textField.setBackground(SystemColor.text);
		splitPane_1.setLeftComponent(textField);
		textField.setColumns(15);
		// Selected nodes will fill text box
		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent e) {
				Object node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				textField.setText(node.toString());
			}
		});

		// Search button
		JButton searchButton = new JButton("Search");
		searchButton.setBackground(UIManager.getColor("Button.darkShadow"));
		splitPane_1.setRightComponent(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load the command found in the search dialog
				String searchText = textField.getText();
				ArrayList<String> manPage = reader.returnPage(searchText);
				if(manPage.get(0).equals("404")){
					// Alert the user of a mistaken command typed
					Toolkit.getDefaultToolkit().beep();
				}
				topicPane.setText(manPage.get(0));
				summaryPane.setText(manPage.get(1));
				detailsPane.setText(manPage.get(2));
			}
		});
		
		// Quick search feature on double click
		tree.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() >= 2){
					String searchText = textField.getText();
					ArrayList<String> manPage = reader.returnPage(searchText);
					topicPane.setText(manPage.get(0));
					summaryPane.setText(manPage.get(1));
					detailsPane.setText(manPage.get(2));
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
		// Help button
		JButton helpButton = new JButton("Help");
		helpButton.setBackground(UIManager.getColor("Button.darkShadow"));
		panel2.add(helpButton, BorderLayout.SOUTH);
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Load help information
				ArrayList<String> manPage = reader.returnPage("HELP");
				topicPane.setText(manPage.get(0));
				summaryPane.setText(manPage.get(1));
				detailsPane.setText(manPage.get(2));
			}
		});

		frame.getRootPane().setDefaultButton(searchButton);	// Enter key activates button
	}
}
