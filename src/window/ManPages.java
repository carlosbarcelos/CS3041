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

public class ManPages {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextPane topicPane = new JTextPane();
		topicPane.setText("topicPane");
		panel.add(topicPane, BorderLayout.NORTH);
		
		JTextPane summaryPane = new JTextPane();
		summaryPane.setText("summaryPane");
		panel.add(summaryPane, BorderLayout.SOUTH);
		
		JTextPane detailsPane = new JTextPane();
		detailsPane.setText("detailsPane");
		panel.add(detailsPane, BorderLayout.CENTER);
		
	}

}
