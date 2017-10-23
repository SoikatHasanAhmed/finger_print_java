package Login;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class test extends JPanel {

	/**
	 * Create the panel.
	 */
	public test() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(test.class.getResource("/splash.png")));
		lblNewLabel.setBounds(210, 194, 46, 14);
		add(lblNewLabel);

	}
}
