package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


/*
 * This class creates a splash screen that is displayed when the program is run.  It includes thread management,
 * mouse adapter, and runs with a JWindow on top of the JFrame for the overall application.
 */
public class Splash extends JWindow {
	
	 public static Login_Page frame;
	
	
	
    public Splash(URL img, Frame f, int waitTime) {
        super(f);
        JLabel label = new JLabel(new ImageIcon(img));
        getContentPane().add(label, BorderLayout.CENTER);
        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = label.getPreferredSize();
        setLocation(screenSize.width / 2 - (labelSize.width / 2), screenSize.height / 2 - (labelSize.height / 2));

      
        

        final int pause = waitTime;
        final Runnable closerRunner = new Runnable() {

            public void run() {
                setVisible(false);
                dispose();
                frame = new Login_Page();
               frame.setVisible(true);
            }
        };

        Runnable waitRunner = new Runnable() {
            public void run(){

            try {
                Thread.sleep(pause);
                SwingUtilities.invokeAndWait(closerRunner);
                
                
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getStackTrace());
            }
        }
    };
       
        setVisible(true);
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
        
    }
   
    
    
    
}