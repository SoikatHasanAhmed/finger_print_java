package main_body;
	
import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.mysql.jdbc.PreparedStatement;

import Login.Login_Page;


	
	
	
	public class DbConnection {
		
		public Connection conn = null;
		public Statement stmt = null;
		public ResultSet res = null; 
		
		public static boolean ok = true;
		
		
		
	 public DbConnection(){
		 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
		 }
		 catch(ClassNotFoundException e){
			 
			 System.out.println("driver not found");
			
			 return;
			
		 }
		 
		 try{
			 conn = DriverManager.getConnection("jdbc:mysql://www.yourdomain.com:3306/DBname","sohojweb_testing","password");
			 System.out.println("connected");
			 
		 }
		 
		 catch(SQLException e){
			 JOptionPane.showMessageDialog(null, "Connection problem");
			 System.out.println("Connection Fail");
			 
		 }
		 
		
	 }
	 /*
	  * this connect for reconnect the conn if it lost conn
	  * <................................start...................................>
	  */
	 public void connect(){
		 
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
		 }
		 catch(ClassNotFoundException e){
			 
			 System.out.println("driver not found");
			
			 return;
			
		 }
		 
		 try{
			  conn = DriverManager.getConnection("jdbc:mysql://www.yourdomain.com:3306/DBname","sohojweb_testing","password");
			System.out.println("connected");
			 
		 }
		 
		 catch(SQLException e){
			 JOptionPane.showMessageDialog(null, "Connection problem");
			 System.out.println("Connection Fail");
			 
		 }
		 
	  }
	 //<.................................end..................................>
	 
	 
	 
	 
	 
	 public void Insert_product(String pname,String Category,int pstock,int price){
	
		PreparedStatement pst = null;
		String sql = "INSERT INTO `product_tab`(`Product_Name`, `Category`, `Stock`, `Selling_Price`) VALUES (?,?,?,?)";
		try {
			
			 pst =(PreparedStatement)conn.prepareStatement(sql);
	        pst.setString(1, pname);
	        pst.setString(2, Category);
	        pst.setInt(3,pstock);   
	        pst.setInt(4,price); 
	        
	        
	        pst.executeUpdate();
	        JOptionPane.showMessageDialog(null, "inserted successfully");
		}
		catch (SQLException e1) {
		connect();
		JOptionPane.showMessageDialog(null, "Connection problem try again");
		}
		
	 }
	 
	 
	public void Insert_sales(String pname,String Category,int pstock,int Tprice){
		 
		 
		
		PreparedStatement pst = null;
		String sql = "INSERT INTO `sales_tab`(`Product_Name`, `Category`, `Quantity`, `Total_Cost`) VALUES (?,?,?,?)";
		try {
			
			 pst =(PreparedStatement)conn.prepareStatement(sql);
	        pst.setString(1, pname);
	        pst.setString(2, Category);
	        pst.setInt(3, pstock);
	        pst.setInt(4,Tprice); 
	       
	        pst.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Successfully Inserted\n\n"
	        		+ "total Payment :  "+ Integer.toString(Tprice));
		}
		catch (SQLException e1) {
			 JOptionPane.showMessageDialog(null, "ERROR\n to insert !!.");
		}
		
	 }
	 
	//empployee insert
	
	public void emp_Insert(int id, String emp_name){
		
		PreparedStatement pst = null;
		String sql = "INSERT INTO `emp_table`(`id`, `emp_name`) VALUES (?,?)";
		try {
			
			 pst =(PreparedStatement)conn.prepareStatement(sql);
	        pst.setInt(1, id);
	        pst.setString(2, emp_name);
	        
	       
	        pst.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Employee Successfully Added");
		}
		catch (SQLException e1) {
			 JOptionPane.showMessageDialog(null, "ERROR to Add Employee !!.");
		}
		
		
	}
	
public void mem_info_insert(int id, String mem_name,String mem_num){
		
		PreparedStatement pst = null;
		String sql = "INSERT INTO `member_table`(`id`, `member_name`, `member_number`) VALUES (?,?,?)";
		try {
			
			 pst =(PreparedStatement)conn.prepareStatement(sql);
	        pst.setInt(1, id);
	        pst.setString(2, mem_name);
	        pst.setString(3, mem_num);
	        
	       
	        pst.executeUpdate();
	        JOptionPane.showMessageDialog(null, "Member Successfully Added");
		}
		catch (SQLException e1) {
			 JOptionPane.showMessageDialog(null, "ERROR to Add Member !!.");
		}
		
		
	}
  public void getMemberIfo(int id){
	  
	  String sql = "SELECT * FROM member_table WHERE id = ?";
		 try {
						
				   PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
			       pst.setInt(1, id); 
			     
			       res = pst.executeQuery();
		
		     while  (res.next())
	      {
		    String name = res.getString("member_name");
		    String number = res.getString("member_number");
		   main_frame.salesMamberName_textField.setText(name);
		   main_frame.sales_member_number_textField_2.setText(number);
		    System.out.println(name+"  "+number);
		
	     	  
	     	
	     	
	       }
		     
		    
	      }
		catch(Exception e){
			    connect();
				 JOptionPane.showMessageDialog(null, "ERROR\nYou are doing some thing wrong !!.");
		}
	  
	  
  }


	
	public void getProductInfo(int id){
		 String sql = "SELECT * FROM product_tab WHERE Product_Code = ?";
		 try {
						
				   PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
			       pst.setInt(1, id); 
			     
			       res = pst.executeQuery();
		
		     while  (res.next())
	      {
		    String name = res.getString("Product_Name");
		    String Category = res.getString("Category");
		      int Sel_pri = res.getInt("Selling_Price");
	     	  int Stock = res.getInt("Stock");
	     	  
	     	
	     	 main_frame.setsalesinfo(name, Category, Sel_pri);
	       }
		     
		    
	      }
		catch(Exception e){
			    connect();
				 JOptionPane.showMessageDialog(null, "ERROR\nYou are doing some thing wrong !!.");
		}
	}
	
	
	public void deleleProdect(int code){
		
		if (code==0){
			JOptionPane.showMessageDialog(null, "Select to delete");
		}
		else{
			    String sql = "DELETE FROM product_tab WHERE Product_code = ? ";
				try {
					
					PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
			        pst.setInt(1, code);
			        pst.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Successfully Deleted");
			        
				  }
				
				 catch (SQLException e1) {
					  connect();
					 JOptionPane.showMessageDialog(null, "ERROR\n to delete !!.try again");
			}
		}
	 }
	
	
 	

	 public void updateProduct_Stock(int id,int pquen,int cu_stok){
		 
		
		 
		 PreparedStatement pst = null;
			String sql = "UPDATE product_tab SET Stock = ? WHERE Product_Code = ? ";
			try {
				
				int newstock = cu_stok - pquen;
				
			
			   pst =(PreparedStatement)conn.prepareStatement(sql);
		       
		       pst.setInt(1,newstock); 
		       pst.setInt(2, id);
		       pst.executeUpdate();
		       
		       JOptionPane.showMessageDialog(null, "Successfully updated stock ");
		     
				
			}
			catch (SQLException e1) {
				connect();
				 JOptionPane.showMessageDialog(null, "ERROR\n to insert !!. try again");
			}
	 }
	 
	
	
	public int getStock(int code){
		 
		 ResultSet res = null ;
		 int Stock = 0;
		 
		
		 PreparedStatement pst = null;
		 String sql = "SELECT Stock FROM product_tab WHERE Product_Code = ? ";
		 try {
				
		   pst =(PreparedStatement)conn.prepareStatement(sql);
	      pst.setInt(1, code); 
	      res = pst.executeQuery();
	      
	     while(res.next()){
	      
	    	 Stock = res.getInt("Stock");
	      }
	      
		}
		catch (SQLException e1) {
			 connect();
			 JOptionPane.showMessageDialog(null, "No data here try again");
		}
		
	return Stock;	 
	}
		
		//<...........................end return stock ........................>
	
	public void addPort(int port){
		
		PreparedStatement pst = null;
		String sql = "UPDATE port SET port = ? WHERE id = 1 ";
		try {
			
		   pst =(PreparedStatement)conn.prepareStatement(sql);
	       pst.setInt(1,port);
	       pst.executeUpdate();
	       
	       JOptionPane.showMessageDialog(null, "Port Added Successfully ");
	     
			
		}
		catch (SQLException e1) {
			connect();
			 JOptionPane.showMessageDialog(null, "ERROR\n to insert !!. try again");
		}
		
	}
	
	public int portNumber(){
		
		 ResultSet res = null ;
		 Statement stm = null;
		 String portnum = "SELECT port FROM port WHERE id = 1";
		 int port =0;
		 
		 try {
			stm= conn.createStatement();
			res = stm.executeQuery(portnum);
			
			
			if(res.next()){
				port = res.getInt("port");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR to get Port");
		}
			
		
	return port;
	}
	
	
	
	public boolean emp_login(int id){
		
		boolean login =false;
		try
        {
			
            
			  String sql = "select * from emp_table where id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
             pst.setInt(1, id);
           
            res = pst.executeQuery();
            if (res.next())
            {
                main_frame home = new main_frame();
                home.setVisible(true);
             
                String empname = empname(id);
                main_frame.emp_name.setText(empname);
               login = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "invalid Employee");
            }
        }
		catch(Exception e){
			connect();
			JOptionPane.showMessageDialog(null, "ERROR Try Again");
		}
		
		return login;
	
	}
	
	
	public String empname(int id){
	
		String name = null;
		String sql = "select * from emp_table where id = ?";
		try{
		 PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
	       pst.setInt(1,id);
	       res = pst.executeQuery();
	       
	       while(res.next()){
	 	      
		    	 name = res.getString("emp_name");
		      }
	       
		}
			
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERROR to get to get employee info");
		}
			
		
	return name;
	}
	
	
	
	
	
	public void createTable(){
		
		Statement stm = null;
		String salestable = "CREATE TABLE IF NOT EXISTS `sales_tab` ( `Sales_Code` INT(10) NOT NULL AUTO_INCREMENT , `Product_Name` VARCHAR(50) NOT NULL , `Category` VARCHAR(50) NOT NULL , `Quantity` INT(20) NOT NULL , `Total_Cost` INT(20) NOT NULL , `Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY (`Sales_Code`));";
		String producttable = "CREATE TABLE IF NOT EXISTS `product_tab` ( `Product_Code` INT NOT NULL AUTO_INCREMENT , `Product_Name` VARCHAR(50) NOT NULL , `Category` VARCHAR(50) NOT NULL , `Stock` INT(20) NOT NULL , `Selling_Price` INT(20) NOT NULL , `Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP , PRIMARY KEY (`Product_Code`));";
		String prottable ="CREATE TABLE IF NOT EXISTS `port`   ( `id` INT(10) NOT NULL , `port` INT(10) NOT NULL , UNIQUE (`id`));";
		String insertport = "INSERT INTO `port`(`id`, `port`) VALUES (1,1)";
		String emp_table = "CREATE TABLE IF NOT EXISTS `emp_table` ( `id` INT(20) NOT NULL , `emp_name` VARCHAR(50) NOT NULL );";
		String mem_table = "CREATE TABLE IF NOT EXISTS `member_table` ( `id` INT NOT NULL , `member_name` VARCHAR(50) NOT NULL , `member_number` VARCHAR(20) NOT NULL );";
		try {
			stm= conn.createStatement();
			stm.executeUpdate(salestable);
			stm.executeUpdate(producttable);
			stm.executeUpdate(prottable);
			stm.executeUpdate(emp_table);
			stm.executeUpdate(mem_table);
			
			
			JOptionPane.showMessageDialog(null, "Successfully created");
			
			try{
			//inserting port def. values
			stm.executeUpdate(insertport);
			JOptionPane.showMessageDialog(null, "Device Ports Default Value added");
			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Device Ports Default  Value Already added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error to create");
		}
	}
	
	
	}
