package guifiles;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
public class AddProduct extends JFrame{
   
   protected final JPanel     entryPanel      = new JPanel();
   protected final JTextField nameField       = new JTextField();
   protected final JTextField priceField      = new JTextField();
   protected final JTextField quantityField   = new JTextField("1");
   protected final String   tableName;
   protected JButton  submitButton;
  
   public AddProduct(JLabel topImage , String tableName)
   {  
	   
	  super("New Sales Entry");
	  this.setLayout(null);
	  this.tableName = tableName;
	  getContentPane().add(buttonsLabel());
	  getContentPane().add(topImage(topImage));
	  setupEntryPanel();
	  getContentPane().add(entryPanel);
	  Setter.setWindow(this);
	  
   }
   private JLabel topImage(JLabel topImage)
   {
	   final JLabel topImageLabel = topImage;
	   topImageLabel.setSize(1166,150);
	   topImageLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
	   return topImageLabel;
   }
   protected JLabel buttonsLabel()
   {
	   final JLabel buttonsLabel = new JLabel();
	   buttonsLabel.setBounds(0,525,1166,65);
	   buttonsLabel.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\buttonslabel.png"));
	   buttonsLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
	   buttonsLabel.setLayout(new BoxLayout(buttonsLabel,BoxLayout.X_AXIS));
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(resetButton());
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(submitButton());
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(backButton());
       return buttonsLabel;
   }
   protected JButton resetButton()
   {
	   JButton resetButton = Setter.setButton("   RESET    ",new Rectangle(0,0,0,0));
	   resetButton.addActionListener(e -> {
      		                                    this.nameField.setText("");
      		                                    this.priceField.setText("");
      		                                    this.quantityField.setText("1"); 
                                           });                        
	   return resetButton;
   }
   protected JButton submitButton()
   {   
	   this.submitButton = Setter.setButton("   SUBMIT   ",new Rectangle(0,0,0,0));
	   this.submitButton.addActionListener(e -> {
		                                    	String itemName     = this.nameField.getText().toString().trim();
		                                    	String itemCost    = this.priceField.getText().toString().trim();
		                                    	String itemQuantity = this.quantityField.getText().toString().trim();
		                                 	    if(itemCost.matches("[0-9]+")  && itemQuantity.matches("[0-9]+") && itemName.matches("[a-zA-Z]+")) {
		                                 	    	  if(JOptionPane.showConfirmDialog(this, "Item Name ---      " + itemName +
		                                                                                     "\nItem Cost ---    " + itemCost +
		                                                                                     "\nItem Quantity --- " + itemQuantity,"IS IT CORRECT?", JOptionPane.YES_NO_OPTION) == 0)
		                                    	Database.submitData(this.tableName,itemName,itemCost,itemQuantity);
		                                 	    }
		                                        else {
		                                        	JOptionPane.showMessageDialog(this,"INVALID ENTRY");
		                                        }
	                                       });
	   return this.submitButton;		  
   }
   private JButton backButton()
   {
	   final JButton backButton = Setter.setButton("     BACK     ",new Rectangle(0,0,0,0));
	   backButton.addActionListener(e -> { 
		   this.setVisible(false);
		   WelcomePage.welcomeFrame.setVisible(true);
	   });
	   return backButton;
   }
   protected void setupEntryPanel()
   {  
	   this.entryPanel.setLayout(new BoxLayout(entryPanel,BoxLayout.Y_AXIS));
	   this.entryPanel.setBounds(0,150,1160,375);
	   this.entryPanel.setBackground(Color.YELLOW);
	   putEntryFields();
   }
   protected JLabel putEntryFields()
   {  
	   final JLabel entryLabel = new JLabel();
	   entryLabel.setLayout(new GridLayout(3,2,0,5));
	   entryLabel.add(new JLabel("     ITEM NAME   "));
	   entryLabel.add(nameField);
	   entryLabel.add(new JLabel("     ITEM COST  "));
	   entryLabel.add(priceField);
	   entryLabel.add(new JLabel("     QUANTITY    "));
	   entryLabel.add(quantityField);
	   entryLabel.setMaximumSize(new Dimension(250,90));
	   this.entryPanel.add(entryLabel);
	   return entryLabel;
   }
}
