package guifiles;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.BevelBorder;
public class AddProduct extends JFrame{
   
   protected final JPanel                entryPanel       = new JPanel();
   private final JScrollPane             entryScrollPane  = new JScrollPane(entryPanel); ;
   protected final ArrayList<JTextField> nameFields       = new ArrayList<>();
   protected final ArrayList<JTextField> priceFields      = new ArrayList<>();
   protected final ArrayList<JTextField> quantityFields   = new ArrayList<>();
   protected  int    alreadySubmmited = 0;
   protected JButton submitButton;
   protected String  tableName;
   
   public AddProduct(JLabel topImage , String tableName)
   {  
	  super("New Sales Entry");
	  setLayout(null);
	  this.tableName = tableName;
	  setSize(WelcomeFrame.welcomeFrame.getSize());
	  getContentPane().add(buttonsLabel());
	  getContentPane().add(topImage(topImage));
	  setLocation(WelcomeFrame.welcomeFrame.getLocation());
	  setupEntryPanel();
	  setupScrollPane();
	  getContentPane().add(entryScrollPane);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setResizable(false);
	  setVisible(true);
   }
   private JLabel topImage(JLabel topImage)
   {
	   final JLabel topImageLabel = topImage;
	   topImageLabel.setSize(1166,150);
	   topImageLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
	   return topImageLabel;
   }
   private JLabel buttonsLabel()
   {
	   final JLabel buttonsLabel = new JLabel();
	   buttonsLabel.setSize(1166,65);
	   buttonsLabel.setLocation(0,525);
	   buttonsLabel.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\buttonslabel.png"));
	   buttonsLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
	   buttonsLabel.setLayout(new BoxLayout(buttonsLabel,BoxLayout.X_AXIS));
	   buttonsLabel.add(resetButton());
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(addMoreButton());
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(submitButton());
	   buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
	   buttonsLabel.add(backButton());
       return buttonsLabel;
   }
   private JButton addMoreButton()
   {
	   final JButton addMoreButton = new JButton("  ADD MORE  ");
	   addMoreButton.setBackground(Color.YELLOW);
	   addMoreButton.setBorder(new BevelBorder(BevelBorder.RAISED));
	   addMoreButton.addActionListener(e -> {
		                                     putEntryFields();
		                                     this.submitButton.setEnabled(true);
	                                        });
	   return addMoreButton;
   }
   private JButton resetButton()
   {
	   final JButton resetButton = new JButton("    RESET    ");
	   resetButton.setBackground(Color.YELLOW);
	   resetButton.setBorder(new BevelBorder(BevelBorder.RAISED));
	   resetButton.addActionListener(e -> {
      	                                     for(int i = 0 ; i < nameFields.size() ; ++i)
      	                                      {
      		                                    nameFields.get(i).setText("");
      		                                    priceFields.get(i).setText("");
      	                                      }
                                           });                        
	   return resetButton;
   }
   protected JButton submitButton()
   {
	   final JButton submitButton = new JButton("   SUBMIT   ");;
	   submitButton.setBackground(Color.YELLOW);
	   submitButton.setBorder(new BevelBorder(BevelBorder.RAISED));
	   submitButton.addActionListener(e -> {
		   
		                                    for(int i = alreadySubmmited ; i < nameFields.size() ; ++i) {
		                                    	
		                                    	String itemName     = this.nameFields.get(i).getText().toString().trim();
		                                    	String itemPrice    = this.priceFields.get(i).getText().toString().trim();
		                                    	String itemQuantity = this.quantityFields.get(i).getText().toString().trim();
		                                 	    if(itemPrice.length() > 0 && itemQuantity.length() > 0 && itemName.length() > 0) 
		                                    	Database.submitData(this.tableName,itemName,itemPrice,itemQuantity);
		                                        else {
		                                        	JOptionPane.showMessageDialog(this,"FILL ALL FIELDS");
		                                        	continue;
		                                        }
		                                    submitButton.setEnabled(false);
		                                    this.alreadySubmmited = nameFields.size();
		                                    }
	                                       });
	   this.submitButton = submitButton;
	   return submitButton;		  
   }
   private JButton backButton()
   {
	   final JButton backButton = new JButton("      Back      ");
	   backButton.setBackground(Color.YELLOW);
	   backButton.setBorder(new BevelBorder(BevelBorder.RAISED));
	   backButton.addActionListener(e ->  {
		                                   this.setVisible(false);
		                                   WelcomeFrame.welcomeFrame.setVisible(true);
	                                      });
	   return backButton;
   }
   private void setupScrollPane()
   {
	  this.entryScrollPane.setSize(1160,375);
	  this.entryScrollPane.setLocation(0,150);
	  this.entryScrollPane.getVerticalScrollBar().setBackground(Color.YELLOW);
   }
   protected void setupEntryPanel()
   {  
	   this.entryPanel.setLayout(new BoxLayout(entryPanel,BoxLayout.Y_AXIS));
	   this.entryPanel.setPreferredSize(new Dimension(1130,368));
	   this.entryPanel.setBackground(Color.YELLOW);
	   putEntryFields();
   }
   protected void putEntryFields()
   {  
	   final JTextField itemName = new JTextField();
	   final JTextField itemCost = new JTextField();
	   final JTextField quantity = new JTextField("1");
	   final JLabel entryLabel = new JLabel();
	   this.nameFields.add(itemName);
	   this.priceFields.add(itemCost);
	   this.quantityFields.add(quantity);
	   entryLabel.setLayout(new GridLayout(3,2,0,5));
	   entryLabel.add(new JLabel("     ITEM NAME   "));
	   entryLabel.add(itemName);
	   entryLabel.add(new JLabel("     ITEM COST  "));
	   entryLabel.add(itemCost);
	   entryLabel.add(new JLabel("      QUANTITY    "));
	   entryLabel.add(quantity);
	   entryLabel.setMaximumSize(new Dimension(250,90));
	   this.entryPanel.add(entryLabel);
	   this.entryPanel.add(Box.createRigidArea(new Dimension(0,20)));
	   this.entryPanel.setPreferredSize(new Dimension(1130,entryPanel.getPreferredSize().height + 150));
	   this.setVisible(true);
   }
}
