package Login;

import java.awt.BorderLayout;
import main_body.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sohojweb.fpsController.JNI_Connector;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login_Page extends JFrame {

	private JPanel contentPane;
	private static int varifyId;
	DbConnection  dt = new DbConnection();
	
	private int portNumber = dt.portNumber();
	JNI_Connector fpDevice = new JNI_Connector(portNumber);
	private static  Login_Page frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login_Page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Page() {
		
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login_Page.class.getResource("/round_employee.png")));
		label.setBounds(348, 115, 192, 204);
		contentPane.add(label);
		
		JLabel lblSohojInventoySystem = new JLabel("Sohoj Inventoy System");
		lblSohojInventoySystem.setForeground(new Color(51, 204, 51));
		lblSohojInventoySystem.setFont(new Font("Sylfaen", Font.BOLD | Font.ITALIC, 33));
		lblSohojInventoySystem.setBounds(268, 31, 388, 64);
		contentPane.add(lblSohojInventoySystem);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					fpDevice.varifyUser();
				}catch(NullPointerException e){
					System.out.println("error");
				}
				  boolean login =   dt.emp_login(varifyId);
				  if (login){
					
				  }
				     
				
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(385, 358, 126, 34);
		contentPane.add(btnNewButton);
		
		JLabel login_error = new JLabel("");
		login_error.setBounds(353, 460, 281, 28);
		contentPane.add(login_error);
	}
	public static void getVarify_Id(int Vid){
		varifyId = Vid;
		System.out.println(varifyId);
		
	}
	
	
	
		
	
}
