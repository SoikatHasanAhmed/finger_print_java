package main_body;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sohojweb.fpsController.JNI_Connector;

public class Test {
	
		
		public static void main (String[] args){
			JNI_Connector fp = new JNI_Connector(3);
						
			     DbConnection db = new DbConnection();
			  
			    /* JFrame frame = new JFrame();
			     Object result = JOptionPane.showInputDialog(frame, "Enter printer name:");

			  int tempport = Integer.parseInt(result.toString());
			    
					JNI_Connector fpDevice = new JNI_Connector(tempport);
					try {
						if(fpDevice.openDevice())
						{
						System.out.println("done");
						}
						else
						{
							
						}
					} catch (NullPointerException err)
					{
						 
					}*/
		}
}
