package main_body;

import com.sohojweb.fpsController.JNI_Connector;

import Login.Login_Page;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ViewportUI;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.Button;

public class main_frame extends JFrame {
	
	/*
	 * global variable 
	  */
	 
	
	
	
	JScrollPane scrollPane;   
	JScrollPane scrollPanes; 
	JScrollPane scrollPanem;
	
	JPanel product_table; 
	JPanel buttonPanel;
	JPanel newsales_panel;
	JPanel salesmamber_panel;
	JPanel addproduct;
	JPanel Show_sales_panel;
	JComboBox cat_comboBox;
	JPanel setting_panel;
	JLabel deviceStatus;
	JLabel emp_enroll_status;
	JPanel member_panel;
	JLabel mem_enroll_Status;
	JPanel member_list;
	JLabel sell_verification_status;
	
	
	private JPanel member_list_panel;
	
	DbConnection dt = new DbConnection();
	
	//connect the device when the program start
	
	
	
	String[] category ={"Normal Food","Soft Drink","Snacks","Personal Care"};
	String comPortList[] = {"0","1","2","3","4","5","6","7","8","9"};
	
	private String emp_en_status;
	private String mem_en_status;
	private int portNumber = dt.portNumber();
	private int emp_id = 0;
	private int mem_id = 0;
	private static int varifyId = -1;
	private boolean ifmember = false;
	JNI_Connector fpDevice = new JNI_Connector(portNumber);
	public static JLabel emp_name;
	
	
	private static int sales_product_price=0;
    private int product_Id = 0;
	private JPanel contentPane;
	
	
	
	private static JTextField salespname_textField;
	private static JTextField Sales_cat_textField_1;
	public static JTextField salesMamberName_textField;
	
	public static JTextField sales_member_number_textField_2;
	private JTextField addPname_textField;
	private JTextField Price_textField;
	private static JTextField productPrice_tf;
	private JTextField empname_textfield;
	private JTextField member_name_textField;
	private JTextField member_number_textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_frame frame = new main_frame();
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
	public main_frame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddProducts = new JButton("Add Products");
		btnAddProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newsales_panel.setVisible(false);
				addproduct.setVisible(true);
				product_table.setVisible(true);
				newsales_panel.setVisible(false);
				
				Show_sales_panel.setVisible(false); 
				member_panel.setVisible(false);
				
			}
		});
		btnAddProducts.setBounds(531, 59, 130, 23);
		contentPane.add(btnAddProducts);
		
		JButton btnAllSales = new JButton("All Sales");
		btnAllSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Show_sales_panel.setVisible(true);
				product_table.setVisible(false);
				
				 setting_panel.setVisible(false);
				 member_panel.setVisible(false);
			}
		});
		btnAllSales.setBounds(661, 59, 130, 23);
		contentPane.add(btnAllSales);
		
		JButton btnMember_1 = new JButton("Member");
		btnMember_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				member_panel.setVisible(true);
				 newsales_panel.setVisible(false);
				 addproduct.setVisible(false);
				 Show_sales_panel.setVisible(false);
				 product_table.setVisible(false);
				 setting_panel.setVisible(false);
				 
				 
				 
				 
				 
			}
		});
		btnMember_1.setBounds(788, 59, 130, 23);
		contentPane.add(btnMember_1);
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 newsales_panel.setVisible(false);
				 addproduct.setVisible(false);
				 Show_sales_panel.setVisible(false);
				 product_table.setVisible(false);
				 setting_panel.setVisible(true);
				 member_panel.setVisible(false);
			
				
				
				
			}
		});
		btnSettings.setBounds(917, 59, 113, 23);
		contentPane.add(btnSettings);
		
	    addproduct = new JPanel();
	    addproduct.setVisible(false);
		
		member_panel = new JPanel();
		member_panel.setBackground(Color.WHITE);
		member_panel.setBounds(115, 123, 790, 460);
		member_panel.setVisible(false);
		contentPane.add(member_panel);
		member_panel.setLayout(null);
		
		JButton btnClose_3 = new JButton("Close");
		btnClose_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 newsales_panel.setVisible(true);
				 addproduct.setVisible(false);
				 Show_sales_panel.setVisible(false);
				 product_table.setVisible(true);
				 setting_panel.setVisible(false);
				 member_panel.setVisible(false);
			}
		});
		btnClose_3.setBounds(691, 426, 89, 23);
		member_panel.add(btnClose_3);
		
		JPanel new_menber_panel = new JPanel();
		new_menber_panel.setBackground(SystemColor.inactiveCaptionBorder);
		new_menber_panel.setBounds(411, 126, 357, 295);
		member_panel.add(new_menber_panel);
		new_menber_panel.setLayout(null);
		
		JLabel lblMemberName_1 = new JLabel("Member Name :");
		lblMemberName_1.setBounds(22, 39, 82, 14);
		new_menber_panel.add(lblMemberName_1);
		
		member_name_textField = new JTextField();
		member_name_textField.setBounds(142, 36, 205, 20);
		new_menber_panel.add(member_name_textField);
		member_name_textField.setColumns(10);
		
		member_number_textField = new JTextField();
		member_number_textField.setColumns(10);
		member_number_textField.setBounds(142, 64, 205, 20);
		new_menber_panel.add(member_number_textField);
		
		JLabel lblMemberNumber = new JLabel("Member Number:");
		lblMemberNumber.setBounds(22, 67, 110, 14);
		new_menber_panel.add(lblMemberNumber);
		
		JLabel lblEnrollStatus = new JLabel("Enroll Status :");
		lblEnrollStatus.setBounds(22, 92, 92, 14);
		new_menber_panel.add(lblEnrollStatus);
		
		mem_enroll_Status = new JLabel("");
		mem_enroll_Status.setBounds(114, 95, 205, 14);
		new_menber_panel.add(mem_enroll_Status);
		
		JButton btnEnrollFinger = new JButton("Enroll Finger");
		btnEnrollFinger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					mem_id = fpDevice.getUserCount()+1;
					mem_en_status = (fpDevice.Enroll(mem_id));
					mem_enroll_Status.setText(mem_en_status);
					}
					
				 catch (NullPointerException err)
				{
					mem_enroll_Status.setText("Select comm port first !");
				}
			}
		});
		btnEnrollFinger.setBounds(22, 133, 112, 23);
		new_menber_panel.add(btnEnrollFinger);
		
		JButton btnNewButton_2 = new JButton("Add Member");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				if(member_name_textField.getText().equals("")||member_number_textField.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Can't be Empty");
				}
				else{
					if(mem_en_status.equals("Enroll successfully")){
						
						dt.mem_info_insert(mem_id,member_name_textField.getText(),member_number_textField.getText());
						
						
						member_name_textField.setText("");
						member_number_textField.setText("");
						
					    }
					else{
						JOptionPane.showMessageDialog(null, "Enroll First");
					}
				}
			  
			}
		});
		btnNewButton_2.setBounds(22, 167, 112, 23);
		new_menber_panel.add(btnNewButton_2);
		
		JLabel member_pic = new JLabel("");
		member_pic.setIcon(new ImageIcon(main_frame.class.getResource("/member.jpeg")));
		member_pic.setBounds(343, 0, 164, 120);
		member_panel.add(member_pic);
		
	    member_list_panel = new JPanel();
		member_list_panel.setBounds(23, 126, 372, 323);
		member_panel.add(member_list_panel);
		
		member_list_panel.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				scrollPanem.setVisible(false);
				/*
				 * member table 
				 * <......................................table code start.........................>
				 * 
				 * this code takes the list from the database when the program start.
				 */
		       
				member_list = new JPanel();
				
				
				ArrayList columnNames11 = new ArrayList();
		        ArrayList data11 = new ArrayList();

		        //  Connect to an MySQL Database, run query, get result set
		      
		        String sql11 = "SELECT * FROM `member_table`";

		       
		        try (
		        Statement stmt = (Statement) dt.conn.createStatement();
		            ResultSet rs = stmt.executeQuery( sql11 ))
		        {
		            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		            int columns = md.getColumnCount();

		            //  Get column names
		            for (int i = 1; i <= columns; i++)
		            {
		                columnNames11.add( md.getColumnName(i) );
		            }

		            //  Get row data
		            while (rs.next())
		            {
		                ArrayList row = new ArrayList(columns);

		                for (int i = 1; i <= columns; i++)
		                {
		                    row.add( rs.getObject(i) );
		                }

		                data11.add( row );
		            }
		        }
		        catch (SQLException e1)
		        {
		            System.out.println( e1.getMessage() );
		        }

		        // Create Vectors and copy over elements from ArrayLists to them
		        // Vector is deprecated but I am using them in this example to keep 
		        // things simple - the best practice would be to create a custom defined
		        // class which inherits from the AbstractTableModel class
		        Vector columnNamesVector11 = new Vector();
		        Vector dataVector11 = new Vector();

		        for (int i = 0; i < data11.size(); i++)
		        {
		            ArrayList subArray = (ArrayList)data11.get(i);
		            Vector subVector = new Vector();
		            for (int j = 0; j < subArray.size(); j++)
		            {
		                subVector.add(subArray.get(j));
		            }
		            dataVector11.add(subVector);
		        }

		        for (int i = 0; i < columnNames11.size(); i++ )
		            columnNamesVector11.add(columnNames11.get(i));
		        member_list_panel.setLayout(null);
				
				
				        JTable table2 = new JTable(dataVector11, columnNamesVector11)
				        {
				            public Class getColumnClass(int column)
				            {
				                for (int row = 0; row < getRowCount(); row++)
				                {
				                    Object o = getValueAt(row, column);
				
				                    if (o != null)
				                    {
				                        return o.getClass();
				                    }
				                }
				
				                return Object.class;
				            }
				        };
				        table2.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
				        table2.setForeground(new Color(0, 0, 51));
				        table2.setBorder(UIManager.getBorder("ToolTip.border"));
				        table2.setBackground(SystemColor.controlLtHighlight);
				        
				                        scrollPanem = new JScrollPane( table2 );
				                        scrollPanem.setBounds(10, 32, 357, 285);
				                        member_list_panel.add( scrollPanem );
				                
				                        buttonPanel = new JPanel();
				                        buttonPanel.setBounds(477, 201, 10, 10);
				                        buttonPanel.setBackground(SystemColor.activeCaption);
				                        member_list_panel.add( buttonPanel );
				                        
				                        JLabel lblNewLabel11 = new JLabel("Member List");
				                        lblNewLabel11.setBounds(127, 0, 172, 28);
				                        lblNewLabel11.setForeground(new Color(0, 0, 153));
				                        lblNewLabel11.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
				                        member_list_panel.add(lblNewLabel11);
				                        
				                       
				                       
				                        
				
				        /*
				         * member list table
				         * <......................................table code End.........................>
				         */
			}
		});
		btnRefresh.setBounds(278, 288, 89, 23);
		member_list_panel.add(btnRefresh);
		
		setting_panel = new JPanel();
		setting_panel.setBackground(SystemColor.inactiveCaption);
		setting_panel.setBounds(55, 102, 923, 431);
		contentPane.add(setting_panel);
		setting_panel.setVisible(false);
		setting_panel.setLayout(null);
		
		JButton btnCreateTables = new JButton("Create Tables");
		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dt.createTable();
			}
		});
		btnCreateTables.setBounds(22, 84, 142, 23);
		setting_panel.add(btnCreateTables);
		
		JLabel lblNewLabel_1 = new JLabel("Settings");
		lblNewLabel_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel_1.setBounds(431, 0, 137, 39);
		setting_panel.add(lblNewLabel_1);
		
		JButton btnClose_2 = new JButton("Close");
		btnClose_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				newsales_panel.setVisible(true);
				 addproduct.setVisible(false);
				 Show_sales_panel.setVisible(false);
				 product_table.setVisible(true);
				 setting_panel.setVisible(false);
				
			}
		});
		btnClose_2.setBounds(809, 397, 89, 23);
		setting_panel.add(btnClose_2);
		
		JButton btnNewButton_1 = new JButton("Open Device");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//to re open the device
				portNumber = dt.portNumber();
				fpDevice = new JNI_Connector(portNumber);
				try {
					if(fpDevice.openDevice())
					{
						deviceStatus.setText("Device Connected ");
					}
					else
					{
						deviceStatus.setText("Communication error!");
					}
				} catch (NullPointerException err)
				{
					 deviceStatus.setText("Select comm port first !");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(22, 118, 100, 23);
		setting_panel.add(btnNewButton_1);
		
		JLabel lblPortSettings = new JLabel("Port Select :");
		lblPortSettings.setBounds(22, 162, 81, 14);
		setting_panel.add(lblPortSettings);
		
		JComboBox port_comboBox = new JComboBox(comPortList);
		port_comboBox.setBounds(113, 159, 63, 20);
		setting_panel.add(port_comboBox);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dt.addPort(Integer.parseInt(port_comboBox.getSelectedItem().toString()));
			}
		});
		btnAdd_1.setBounds(111, 190, 89, 23);
		setting_panel.add(btnAdd_1);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setForeground(Color.BLUE);
		lblAddEmployee.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblAddEmployee.setBounds(73, 226, 110, 39);
		setting_panel.add(lblAddEmployee);
		
		JButton btnEnrollEmployeeFinger = new JButton("Enroll Employee Finger");
		btnEnrollEmployeeFinger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					emp_id = fpDevice.getUserCount()+1;
					emp_en_status = (fpDevice.Enroll(emp_id));
					emp_enroll_status.setText(emp_en_status);
					}
					
				 catch (NullPointerException err)
				{
					emp_enroll_status.setText("Select comm port first !");
				}
				
			}
		});
		btnEnrollEmployeeFinger.setBounds(22, 331, 155, 23);
		setting_panel.add(btnEnrollEmployeeFinger);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStatus.setBounds(22, 297, 57, 23);
		setting_panel.add(lblStatus);
		
		emp_enroll_status = new JLabel("");
		emp_enroll_status.setBounds(73, 297, 393, 23);
		setting_panel.add(emp_enroll_status);
		
		JLabel lblEmployeeName = new JLabel("Employee  Name :");
		lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmployeeName.setBounds(22, 263, 128, 23);
		setting_panel.add(lblEmployeeName);
		
		empname_textfield = new JTextField();
		empname_textfield.setColumns(10);
		empname_textfield.setBounds(144, 265, 155, 20);
		setting_panel.add(empname_textfield);
		
		JButton btnAdd_2 = new JButton("Add  Employee");
		btnAdd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(empname_textfield.getText().equals("")){
				
				JOptionPane.showMessageDialog(null, "Employee Name Can't be Empty");
			}
			else{
				if(emp_en_status.equals("Enroll successfully")){
					
					dt.emp_Insert(emp_id,empname_textfield.getText());
					
					empname_textfield.setText("");
				    }
				else{
					JOptionPane.showMessageDialog(null, "Enroll First");
				}
			}
		  }
		});
		btnAdd_2.setBounds(22, 366, 154, 23);
		setting_panel.add(btnAdd_2);
		
		newsales_panel = new JPanel();
		newsales_panel.setBackground(SystemColor.inactiveCaption);
		newsales_panel.setBounds(620, 123, 383, 487);
		contentPane.add(newsales_panel);
		newsales_panel.setLayout(null);
		
		JLabel lblSaleProdect = new JLabel("Sale Prodect");
		lblSaleProdect.setForeground(new Color(0, 0, 153));
		lblSaleProdect.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
		lblSaleProdect.setBounds(162, 0, 110, 28);
		newsales_panel.add(lblSaleProdect);
		
		JLabel lblProductName = new JLabel("Product name :");
		lblProductName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblProductName.setBounds(22, 66, 115, 28);
		newsales_panel.add(lblProductName);
		
		salespname_textField = new JTextField();
		salespname_textField.setBounds(186, 69, 172, 20);
		newsales_panel.add(salespname_textField);
		salespname_textField.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblCategory.setBounds(22, 105, 115, 28);
		newsales_panel.add(lblCategory);
		
		Sales_cat_textField_1 = new JTextField();
		Sales_cat_textField_1.setColumns(10);
		Sales_cat_textField_1.setBounds(186, 108, 172, 20);
		newsales_panel.add(Sales_cat_textField_1);
		
		JSpinner Salesquantity_spinner = new JSpinner();
		Salesquantity_spinner.setBounds(186, 170, 86, 20);
		newsales_panel.add(Salesquantity_spinner);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblQuantity.setBounds(22, 170, 115, 28);
		newsales_panel.add(lblQuantity);
		
		JLabel lblSalesType = new JLabel("Sales Type");
		lblSalesType.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblSalesType.setBounds(22, 204, 115, 28);
		newsales_panel.add(lblSalesType);
		
		JButton btnSell = new JButton("Sell");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String sproduct_name = salespname_textField.getText();
				String Cat = Sales_cat_textField_1.getText();
				int quen = Integer.parseInt(Salesquantity_spinner.getValue().toString());
			    int	total = sales_product_price * quen;
			    int precost;
			    
			    if(ifmember== true && sell_verification_status.getText().equals("Member Vaified")){
			    	precost = total;
			    	precost = (int) (precost - precost*.1);
			    	JOptionPane.showMessageDialog(null, "Total Cost = "+total+"\n\nMember Discount 10%\n\nAfter Discount Total Cost = "+precost);
			    }
			    else{
			    	precost = total;
			    	
			    }
			
				
				if(sproduct_name.equals("") ||Cat.equals("") || quen==0){
					JOptionPane.showMessageDialog(null, "ERROR\nField Can't be empty !! or 0");
				}
				
				else{
					
					
					int avilable = dt.getStock(product_Id);
					if(avilable>=quen){
					dt.Insert_sales(sproduct_name, Cat, quen, precost);
					dt.updateProduct_Stock(product_Id, quen, avilable);
					}
					else{
						 JOptionPane.showMessageDialog(null, "Out of Stock");
					}
				}
				
				
				
			}
		});
		btnSell.setBounds(284, 453, 89, 23);
		newsales_panel.add(btnSell);
		
		JButton btnMember = new JButton("Member");
		btnMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salesmamber_panel.setVisible(true);
				ifmember = true;
			}
		});
		btnMember.setBounds(183, 206, 89, 23);
		newsales_panel.add(btnMember);
		
		salesmamber_panel = new JPanel();
		salesmamber_panel.setVisible(false);
		salesmamber_panel.setBackground(SystemColor.inactiveCaption);
		salesmamber_panel.setBounds(36, 243, 322, 183);
		newsales_panel.add(salesmamber_panel);
		salesmamber_panel.setLayout(null);
		
		JButton btnVarify = new JButton("Varify ");
		btnVarify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					fpDevice.varifyUser();
					if(varifyId>= 0 && varifyId < 200 ){
						sell_verification_status.setText("Member Vaified");
						dt.getMemberIfo(varifyId);
						
						varifyId = -1;
					}
					else{
						sell_verification_status.setText("Member not Vaified");
						varifyId = -1;
					}
				}catch (Exception eee) {
					System.out.println(eee.getLocalizedMessage());
				}
			}
		});
		btnVarify.setBounds(154, 8, 89, 23);
		salesmamber_panel.add(btnVarify);
		
		JLabel lblMemberName = new JLabel("Member Name :");
		lblMemberName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblMemberName.setBounds(10, 89, 115, 28);
		salesmamber_panel.add(lblMemberName);
		
		salesMamberName_textField = new JTextField();
		salesMamberName_textField.setBounds(154, 92, 158, 20);
		salesmamber_panel.add(salesMamberName_textField);
		salesMamberName_textField.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number :");
		lblNumber.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblNumber.setBounds(10, 118, 115, 28);
		salesmamber_panel.add(lblNumber);
		
		sales_member_number_textField_2 = new JTextField();
		sales_member_number_textField_2.setColumns(10);
		sales_member_number_textField_2.setBounds(154, 121, 158, 20);
		salesmamber_panel.add(sales_member_number_textField_2);
		
		JButton btnCancel = new JButton("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salesmamber_panel.setVisible(false);
				ifmember = false;
			}
		});
		btnCancel.setBounds(10, 153, 89, 23);
		salesmamber_panel.add(btnCancel);
		
		JLabel lblVarifyMember = new JLabel("Varify Member  :");
		lblVarifyMember.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblVarifyMember.setBounds(10, 6, 115, 28);
		salesmamber_panel.add(lblVarifyMember);
		
		JLabel VarifyStatus = new JLabel("Varify Status :");
		VarifyStatus.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		VarifyStatus.setBounds(10, 45, 115, 28);
		salesmamber_panel.add(VarifyStatus);
		
		sell_verification_status = new JLabel("varified / not varified");
		sell_verification_status.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		sell_verification_status.setBounds(154, 42, 115, 28);
		salesmamber_panel.add(sell_verification_status);
		
		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblPrice_1.setBounds(22, 142, 115, 28);
		newsales_panel.add(lblPrice_1);
		
		productPrice_tf = new JTextField();
		productPrice_tf.setColumns(10);
		productPrice_tf.setBounds(186, 139, 86, 20);
		newsales_panel.add(productPrice_tf);
		addproduct.setBackground(SystemColor.inactiveCaption);
		addproduct.setBounds(620, 123, 383, 375);
		contentPane.add(addproduct);
		addproduct.setLayout(null);
		
		JLabel label_2 = new JLabel("Product name :");
		label_2.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		label_2.setBounds(37, 56, 115, 28);
		addproduct.add(label_2);
		
		addPname_textField = new JTextField();
		addPname_textField.setColumns(10);
		addPname_textField.setBounds(201, 59, 172, 20);
		addproduct.add(addPname_textField);
		
		JLabel lblCategory_1 = new JLabel("Category :");
		lblCategory_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblCategory_1.setBounds(37, 95, 115, 28);
		addproduct.add(lblCategory_1);
		
		JLabel lblQuantity_1 = new JLabel("Quantity :");
		lblQuantity_1.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblQuantity_1.setBounds(37, 129, 115, 28);
		addproduct.add(lblQuantity_1);
		
		JSpinner addQuen_spinner = new JSpinner();
		addQuen_spinner.setBounds(201, 129, 86, 20);
		addproduct.add(addQuen_spinner);
		
		
		
		
		cat_comboBox = new JComboBox(category);
		cat_comboBox.setBounds(201, 98, 172, 20);
		addproduct.add(cat_comboBox);
		
		JLabel lblAddProduct = new JLabel("Add Product");
		lblAddProduct.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddProduct.setBounds(147, 11, 129, 26);
		addproduct.add(lblAddProduct);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblPrice.setBounds(37, 168, 115, 28);
		addproduct.add(lblPrice);
		
		Price_textField = new JTextField();
		Price_textField.setColumns(10);
		Price_textField.setBounds(201, 171, 86, 20);
		addproduct.add(Price_textField);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				/*
				 * inserting Values to database
				 */
				 
				 
				if(addPname_textField.getText().equals("")||Price_textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Field can't be empty");
				}
				else{
					try {
					    int pric = Integer.parseInt(Price_textField.getText()); 
					    int qean = Integer.parseInt(addQuen_spinner.getValue().toString());
					    dt.Insert_product(addPname_textField.getText(), cat_comboBox.getSelectedItem().toString(), qean, pric);
						  
						addPname_textField.setText("");
						Price_textField.setText("");
						addproduct.setVisible(false);
						newsales_panel.setVisible(true);
						
					    
					  }
					catch (NumberFormatException nfe) {
						
						  JOptionPane.showMessageDialog(null, "Price Should be Number");
					  }
					
				
				}

			}
		});
		btnAdd.setBounds(284, 341, 89, 23);
		addproduct.add(btnAdd);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				newsales_panel.setVisible(true);
				addproduct.setVisible(false);
			}
		});
		btnClose.setBounds(187, 341, 89, 23);
		addproduct.add(btnClose);
		
		
		/*
		 * product table
		 * <......................................table code start.........................>
		 * 
		 * this code takes the list from the database when the program start.
		 */
       
		product_table = new JPanel();
		
		
		ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
      
        String sql = "SELECT * FROM `product_tab`";

       
        try (
        Statement stmt = (Statement) dt.conn.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e1)
        {
            System.out.println( e1.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));
		        product_table.setLayout(null);
		
		
		        JTable table = new JTable(dataVector, columnNamesVector)
		        {
		            public Class getColumnClass(int column)
		            {
		                for (int row = 0; row < getRowCount(); row++)
		                {
		                    Object o = getValueAt(row, column);
		
		                    if (o != null)
		                    {
		                        return o.getClass();
		                    }
		                }
		
		                return Object.class;
		            }
		        };
		        table.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
		        table.setForeground(new Color(0, 0, 51));
		        table.setBorder(UIManager.getBorder("ToolTip.border"));
		        table.setBackground(SystemColor.controlLtHighlight);
		        
		                        scrollPane = new JScrollPane( table );
		                        scrollPane.setBounds(10, 32, 551, 413);
		                        product_table.add( scrollPane );
		                
		                        buttonPanel = new JPanel();
		                        buttonPanel.setBounds(477, 201, 10, 10);
		                        buttonPanel.setBackground(SystemColor.activeCaption);
		                        product_table.add( buttonPanel );
		                        
		                        JLabel lblNewLabel = new JLabel("Product List");
		                        lblNewLabel.setBounds(229, 0, 172, 28);
		                        lblNewLabel.setForeground(new Color(0, 0, 153));
		                        lblNewLabel.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
		                        product_table.add(lblNewLabel);
		                        
		                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		                            public void valueChanged(ListSelectionEvent event) {
		                                // do some actions here, for example
		                                // print first column value from selected row
		                                product_Id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
		                               
		                            }
		                        });
		                        
		
		        /*
		         * product table
		         * <......................................table code End.........................>
		         */
	

		                		/*
		                		 * sales table
		                		 * <......................................table code start.........................>
		                		 * 
		                		 * this code takes the list from the database when the program start.
		                		 */
		                		
		                		
		                		ArrayList columnNames1 = new ArrayList();
		                        ArrayList data1 = new ArrayList();

		                        //  Connect to an MySQL Database, run query, get result set
		                      
		                        String sql1 = "SELECT * FROM `sales_tab`";

		                       
		                        try (
		                        Statement stmt = (Statement) dt.conn.createStatement();
		                            ResultSet rs = stmt.executeQuery( sql1 ))
		                        {
		                            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		                            int columns = md.getColumnCount();

		                            //  Get column names
		                            for (int i = 1; i <= columns; i++)
		                            {
		                                columnNames1.add( md.getColumnName(i) );
		                            }

		                            //  Get row data
		                            while (rs.next())
		                            {
		                                ArrayList row = new ArrayList(columns);

		                                for (int i = 1; i <= columns; i++)
		                                {
		                                    row.add( rs.getObject(i) );
		                                }

		                                data1.add( row );
		                            }
		                        }
		                        catch (SQLException e1)
		                        {
		                            System.out.println( e1.getMessage() );
		                        }

		                        // Create Vectors and copy over elements from ArrayLists to them
		                        // Vector is deprecated but I am using them in this example to keep 
		                        // things simple - the best practice would be to create a custom defined
		                        // class which inherits from the AbstractTableModel class
		                        Vector columnNamesVector1 = new Vector();
		                        Vector dataVector1 = new Vector();

		                        for (int i = 0; i < data1.size(); i++)
		                        {
		                            ArrayList subArray = (ArrayList)data1.get(i);
		                            Vector subVector = new Vector();
		                            for (int j = 0; j < subArray.size(); j++)
		                            {
		                                subVector.add(subArray.get(j));
		                            }
		                            dataVector1.add(subVector);
		                        }

		                        for (int i = 0; i < columnNames1.size(); i++ )
		                            columnNamesVector1.add(columnNames1.get(i));
		                		                        
		                		
		                		        /*
		                		         * sales table
		                		         * <......................................table code End.........................>
		                		         */

		                        /*
		        				 * member table 
		        				 * <......................................table code start.........................>
		        				 * 
		        				 * this code takes the list from the database when the program start.
		        				 */
		        		       
		        				member_list = new JPanel();
		        				
		        				
		        				ArrayList columnNames11 = new ArrayList();
		        		        ArrayList data11 = new ArrayList();

		        		        //  Connect to an MySQL Database, run query, get result set
		        		      
		        		        String sql11 = "SELECT * FROM `member_table`";

		        		       
		        		        try (
		        		        Statement stmt = (Statement) dt.conn.createStatement();
		        		            ResultSet rs = stmt.executeQuery( sql11 ))
		        		        {
		        		            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		        		            int columns = md.getColumnCount();

		        		            //  Get column names
		        		            for (int i = 1; i <= columns; i++)
		        		            {
		        		                columnNames11.add( md.getColumnName(i) );
		        		            }

		        		            //  Get row data
		        		            while (rs.next())
		        		            {
		        		                ArrayList row = new ArrayList(columns);

		        		                for (int i = 1; i <= columns; i++)
		        		                {
		        		                    row.add( rs.getObject(i) );
		        		                }

		        		                data11.add( row );
		        		            }
		        		        }
		        		        catch (SQLException e1)
		        		        {
		        		            System.out.println( e1.getMessage() );
		        		        }

		        		        // Create Vectors and copy over elements from ArrayLists to them
		        		        // Vector is deprecated but I am using them in this example to keep 
		        		        // things simple - the best practice would be to create a custom defined
		        		        // class which inherits from the AbstractTableModel class
		        		        Vector columnNamesVector11 = new Vector();
		        		        Vector dataVector11 = new Vector();

		        		        for (int i = 0; i < data11.size(); i++)
		        		        {
		        		            ArrayList subArray = (ArrayList)data11.get(i);
		        		            Vector subVector = new Vector();
		        		            for (int j = 0; j < subArray.size(); j++)
		        		            {
		        		                subVector.add(subArray.get(j));
		        		            }
		        		            dataVector11.add(subVector);
		        		        }

		        		        for (int i = 0; i < columnNames11.size(); i++ )
		        		            columnNamesVector11.add(columnNames11.get(i));
		        		        member_list_panel.setLayout(null);
		        				
		        				
		        				        JTable table2 = new JTable(dataVector11, columnNamesVector11)
		        				        {
		        				            public Class getColumnClass(int column)
		        				            {
		        				                for (int row = 0; row < getRowCount(); row++)
		        				                {
		        				                    Object o = getValueAt(row, column);
		        				
		        				                    if (o != null)
		        				                    {
		        				                        return o.getClass();
		        				                    }
		        				                }
		        				
		        				                return Object.class;
		        				            }
		        				        };
		        				        table2.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
		        				        table2.setForeground(new Color(0, 0, 51));
		        				        table2.setBorder(UIManager.getBorder("ToolTip.border"));
		        				        table2.setBackground(SystemColor.controlLtHighlight);
		        				        
		        				                        scrollPanem = new JScrollPane( table2 );
		        				                        scrollPanem.setBounds(10, 50, 357, 227);
		        				                        member_list_panel.add( scrollPanem );
		        				                
		        				                        buttonPanel = new JPanel();
		        				                        buttonPanel.setBounds(477, 201, 10, 10);
		        				                        buttonPanel.setBackground(SystemColor.activeCaption);
		        				                        member_list_panel.add( buttonPanel );
		        				                        
		        				                        JLabel lblNewLabel11 = new JLabel("Member List");
		        				                        lblNewLabel11.setBounds(109, 0, 172, 28);
		        				                        lblNewLabel11.setForeground(new Color(0, 0, 153));
		        				                        lblNewLabel11.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
		        				                        member_list_panel.add(lblNewLabel11);
		        				                        
		        				                       
		        				                       
		        				                        
		        				
		        				        /*
		        				         * member list table
		        				         * <......................................table code End.........................>
		        				         */
		
		
		product_table.setBackground(SystemColor.inactiveCaption);
		product_table.setBounds(10, 123, 571, 487);
		contentPane.add(product_table);
		
		JButton btnSell_1 = new JButton("Sell  Product");
		btnSell_1.setBounds(20, 456, 129, 23);
		btnSell_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 System.out.println(product_Id);
				dt.getProductInfo(product_Id);
				 
				
			}
		});
		product_table.add(btnSell_1);
		
		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			  dt.deleleProdect( product_Id);
				
			}
		});
		btnDeleteProduct.setBounds(159, 456, 124, 23);
		product_table.add(btnDeleteProduct);
		
		JButton btnUpdateTable = new JButton("Refresh");
		btnUpdateTable.setBounds(445, 456, 116, 23);
		btnUpdateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 scrollPane.setVisible(false);   
                 buttonPanel.setVisible(false);
                
				
				
				
				ArrayList columnNames = new ArrayList();
		        ArrayList data = new ArrayList();

		        //  Connect to an MySQL Database, run query, get result set
		      
		        String sql = "SELECT * FROM `product_tab`";

		       
		        try (
		        Statement stmt = (Statement) dt.conn.createStatement();
		            ResultSet rs = stmt.executeQuery( sql ))
		        {
		            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		            int columns = md.getColumnCount();

		            //  Get column names
		            for (int i = 1; i <= columns; i++)
		            {
		                columnNames.add( md.getColumnName(i) );
		            }

		            //  Get row data
		            while (rs.next())
		            {
		                ArrayList row = new ArrayList(columns);

		                for (int i = 1; i <= columns; i++)
		                {
		                    row.add( rs.getObject(i) );
		                }

		                data.add( row );
		            }
		        }
		        catch (SQLException e1)
		        {
		            System.out.println( e1.getMessage() );
		        }

		        // Create Vectors and copy over elements from ArrayLists to them
		        // Vector is deprecated but I am using them in this example to keep 
		        // things simple - the best practice would be to create a custom defined
		        // class which inherits from the AbstractTableModel class
		        Vector columnNamesVector = new Vector();
		        Vector dataVector = new Vector();

		        for (int i = 0; i < data.size(); i++)
		        {
		            ArrayList subArray = (ArrayList)data.get(i);
		            Vector subVector = new Vector();
		            for (int j = 0; j < subArray.size(); j++)
		            {
		                subVector.add(subArray.get(j));
		            }
		            dataVector.add(subVector);
		        }

		        for (int i = 0; i < columnNames.size(); i++ )
		            columnNamesVector.add(columnNames.get(i));
				        product_table.setLayout(null);
				
				
				        JTable table = new JTable(dataVector, columnNamesVector)
				        {
				            public Class getColumnClass(int column)
				            {
				                for (int row = 0; row < getRowCount(); row++)
				                {
				                    Object o = getValueAt(row, column);
				
				                    if (o != null)
				                    {
				                        return o.getClass();
				                    }
				                }
				
				                return Object.class;
				            }
				        };
				        table.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
				        table.setForeground(new Color(0, 0, 51));
				        table.setBorder(UIManager.getBorder("ToolTip.border"));
				        table.setBackground(new Color(255, 255, 255));
				        
				                        scrollPane = new JScrollPane( table );
				                        scrollPane.setBounds(10, 32, 551, 413);
				                        product_table.add( scrollPane );
				                
				                        buttonPanel = new JPanel();
				                        buttonPanel.setBounds(477, 201, 10, 10);
				                        buttonPanel.setBackground(SystemColor.activeCaption);
				                        product_table.add( buttonPanel );
				                        
				                        JLabel lblNewLabel = new JLabel("Product List");
				                        lblNewLabel.setBounds(229, 0, 172, 28);
				                        lblNewLabel.setForeground(new Color(0, 0, 153));
				                        lblNewLabel.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
				                        product_table.add(lblNewLabel);
				                        
				                        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				                            public void valueChanged(ListSelectionEvent event) {
				                                
				                                product_Id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				                               
				                            }
				                        });
				
				
			}
		});
		product_table.add(btnUpdateTable);
		
		 Show_sales_panel = new JPanel();
		 Show_sales_panel.setBackground(SystemColor.inactiveCaption);
		 Show_sales_panel.setLayout(null);
		 
		 
		         JTable table1 = new JTable(dataVector11, columnNamesVector11)
		         {
		             public Class getColumnClass(int column)
		             {
		                 for (int row = 0; row < getRowCount(); row++)
		                 {
		                     Object o = getValueAt(row, column);
		 
		                     if (o != null)
		                     {
		                         return o.getClass();
		                     }
		                 }
		 
		                 return Object.class;
		             }
		         };
		         table1.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
		         table1.setForeground(new Color(0, 0, 51));
		         table1.setBorder(UIManager.getBorder("ToolTip.border"));
		         table1.setBackground(SystemColor.controlLtHighlight);
		         
		                         scrollPanes = new JScrollPane( table1 );
		                         scrollPanes.setBounds(10, 32, 551, 413);
		                         Show_sales_panel.add( scrollPanes );
		                         
		                                 buttonPanel = new JPanel();
		                                 buttonPanel.setBounds(477, 201, 10, 10);
		                                 buttonPanel.setBackground(SystemColor.activeCaption);
		                                 Show_sales_panel.add( buttonPanel );
		                                 
		                                 JLabel lblNewLabel1 = new JLabel("Sales List");
		                                 lblNewLabel1.setBounds(229, 0, 172, 28);
		                                 lblNewLabel1.setForeground(new Color(0, 0, 153));
		                                 lblNewLabel1.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
		                                 Show_sales_panel.add(lblNewLabel1);
		                                 
		                                 table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		                                     public void valueChanged(ListSelectionEvent event) {
		                                         // do some actions here, for example
		                                         // print first column value from selected row
		                                         product_Id = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 0).toString());
		                                        
		                                     }
		                                 });
		                                 
		                                 Show_sales_panel.setVisible(false);
		                                 Show_sales_panel.setBounds(10, 120, 571, 490);
		                                 contentPane.add(Show_sales_panel);
		                                 
		                                 JButton btnNewButton = new JButton("Refresh");
		                                 
		                                 
		                                 btnNewButton.addActionListener(new ActionListener() {
		                         			public void actionPerformed(ActionEvent e) {
		                         				
		                         				 scrollPanes.setVisible(false);   
		                                          buttonPanel.setVisible(false);
		                         				
		                         				
		                         				
		                         				ArrayList columnNames = new ArrayList();
		                         		        ArrayList data = new ArrayList();

		                         		        //  Connect to an MySQL Database, run query, get result set
		                         		      
		                         		        String sql = "SELECT * FROM `sales_tab`";

		                         		       
		                         		        try (
		                         		        Statement stmt = (Statement) dt.conn.createStatement();
		                         		            ResultSet rs = stmt.executeQuery( sql ))
		                         		        {
		                         		            ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
		                         		            int columns = md.getColumnCount();

		                         		            //  Get column names
		                         		            for (int i = 1; i <= columns; i++)
		                         		            {
		                         		                columnNames.add( md.getColumnName(i) );
		                         		            }

		                         		            //  Get row data
		                         		            while (rs.next())
		                         		            {
		                         		                ArrayList row = new ArrayList(columns);

		                         		                for (int i = 1; i <= columns; i++)
		                         		                {
		                         		                    row.add( rs.getObject(i) );
		                         		                }

		                         		                data.add( row );
		                         		            }
		                         		        }
		                         		        catch (SQLException e1)
		                         		        {
		                         		            System.out.println( e1.getMessage() );
		                         		        }

		                         		        // Create Vectors and copy over elements from ArrayLists to them
		                         		        // Vector is deprecated but I am using them in this example to keep 
		                         		        // things simple - the best practice would be to create a custom defined
		                         		        // class which inherits from the AbstractTableModel class
		                         		        Vector columnNamesVector = new Vector();
		                         		        Vector dataVector = new Vector();

		                         		        for (int i = 0; i < data.size(); i++)
		                         		        {
		                         		            ArrayList subArray = (ArrayList)data.get(i);
		                         		            Vector subVector = new Vector();
		                         		            for (int j = 0; j < subArray.size(); j++)
		                         		            {
		                         		                subVector.add(subArray.get(j));
		                         		            }
		                         		            dataVector.add(subVector);
		                         		        }

		                         		        for (int i = 0; i < columnNames.size(); i++ )
		                         		            columnNamesVector.add(columnNames.get(i));
		                         		       Show_sales_panel.setLayout(null);
		                         				
		                         				
		                         				        JTable table = new JTable(dataVector, columnNamesVector)
		                         				        {
		                         				            public Class getColumnClass(int column)
		                         				            {
		                         				                for (int row = 0; row < getRowCount(); row++)
		                         				                {
		                         				                    Object o = getValueAt(row, column);
		                         				
		                         				                    if (o != null)
		                         				                    {
		                         				                        return o.getClass();
		                         				                    }
		                         				                }
		                         				
		                         				                return Object.class;
		                         				            }
		                         				        };
		                         				        table.setFont(new Font("Urdu Typesetting", Font.PLAIN, 12));
		                         				        table.setForeground(new Color(0, 0, 51));
		                         				        table.setBorder(UIManager.getBorder("ToolTip.border"));
		                         				        table.setBackground(new Color(255, 255, 255));
		                         				        
		                         				                        scrollPanes = new JScrollPane( table );
		                         				                        scrollPanes.setBounds(10, 32, 551, 413);
		                         				                        Show_sales_panel.add( scrollPanes );
		                         				                
		                         				                        buttonPanel = new JPanel();
		                         				                        buttonPanel.setBounds(477, 201, 10, 10);
		                         				                        buttonPanel.setBackground(SystemColor.activeCaption);
		                         				                       Show_sales_panel.add( buttonPanel );
		                         				                        
		                         				                        JLabel lblNewLabel = new JLabel("Product List");
		                         				                        lblNewLabel.setBounds(229, 0, 172, 28);
		                         				                        lblNewLabel.setForeground(new Color(0, 0, 153));
		                         				                        lblNewLabel.setFont(new Font("Urdu Typesetting", Font.PLAIN, 16));
		                         				                       Show_sales_panel.add(lblNewLabel);
		                         				                        
		                         				                        
		                         				
		                         				
		                         			}
		                         		});
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 
		                                 btnNewButton.setBounds(417, 451, 112, 28);
		                                 Show_sales_panel.add(btnNewButton);
		                                 
		                                 JButton btnClose_1 = new JButton("close");
		                                 btnClose_1.addActionListener(new ActionListener() {
		                                 	public void actionPerformed(ActionEvent e) {
		                                 		Show_sales_panel.setVisible(false);
		                                 		product_table.setVisible(true);
		                                 		newsales_panel.setVisible(true);
		                                 		addproduct.setVisible(true);
		                                 		
		                                 		
		                                 		
		                                 	}
		                                 });
		                                 btnClose_1.setBounds(210, 451, 150, 28);
		                                 
		                                 Show_sales_panel.add(btnClose_1);
		
		deviceStatus = new JLabel("");
		deviceStatus.setForeground(Color.WHITE);
		deviceStatus.setBounds(10, 63, 220, 14);
		contentPane.add(deviceStatus);
		
		JLabel lblEmplyeeName = new JLabel("Emplyee Name :");
		lblEmplyeeName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmplyeeName.setForeground(Color.WHITE);
		lblEmplyeeName.setBounds(260, 63, 95, 14);
		contentPane.add(lblEmplyeeName);
		
		emp_name = new JLabel("");
		emp_name.setForeground(Color.WHITE);
		emp_name.setBounds(361, 63, 165, 14);
		contentPane.add(emp_name);
		                                 
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(main_frame.class.getResource("/himg.jpg")));
		label.setBounds(0, 0, 1030, 63);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(main_frame.class.getResource("/bg.jpg")));
		
		label_1.setBounds(0, 59, 1030, 572);
		contentPane.add(label_1);
		
		//show device status
		try {
			if(fpDevice.openDevice())
			{
				deviceStatus.setText("Device Connected ");
			}
			else
			{
				deviceStatus.setText("Communication error!");
			}
		} catch (NullPointerException err)
		{
			 deviceStatus.setText("Select comm port first !");
		}
		
		
		
	}
	public static void setsalesinfo(String product_name,String category, int price){
		salespname_textField.setText(product_name);
		Sales_cat_textField_1.setText(category);
		sales_product_price = price;
		productPrice_tf.setText(Integer.toString(price));
	}
	
	public static void getVarify_Id(int Vid){
		varifyId = Vid;
		System.out.println(varifyId);
		
	}
}
