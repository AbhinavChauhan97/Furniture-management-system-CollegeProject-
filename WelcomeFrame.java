package guifiles;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
public class WelcomeFrame {
    
	static JFrame welcomeFrame;
	public static void main(String[] args)
	{  
		Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Dimension screenSize = toolkit.getScreenSize();
        welcomeFrame = new JFrame("Welcome");
		welcomeFrame.setLocation(screenSize.width/10 - 40,screenSize.height/10);
		welcomeFrame.setSize(screenSize.width - 200,screenSize.height - 150);
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeFrame.getContentPane().add(WelcomePage.welcomePageObject());
		welcomeFrame.setResizable(false);
		welcomeFrame.setVisible(true);
	}
}
