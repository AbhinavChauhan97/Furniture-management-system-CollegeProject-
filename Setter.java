package guifiles;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
public interface Setter {
   static JButton setButton(String name,Rectangle bounds)
   {
	 JButton button = new JButton(name);
	 button.setBackground(Color.YELLOW);
	 button.setForeground(Color.RED);
	 button.setBounds(bounds);
	 button.setBorder(new BevelBorder(BevelBorder.RAISED));
	 return button;
   }
   static void setWindow(JFrame frame)
   {  
	  Toolkit toolkit = Toolkit.getDefaultToolkit();
   	  Dimension screenSize = toolkit.getScreenSize();
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	  frame.setBounds(screenSize.width/10 - 40,screenSize.height/10,screenSize.width - 200,screenSize.height - 150);
	  frame.setResizable(false);
	  frame.setVisible(true);
   }
}
