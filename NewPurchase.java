package guifiles;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.util.ArrayList;
public class NewPurchase extends AddProduct{
    
	private  ArrayList<JTextField> vendorsName;
	public NewPurchase(JLabel topImage , String tableName)
	{
		super(topImage,tableName);
	}
	@Override
	protected JButton submitButton()
	   {
		   final JButton submitButton = new JButton("   SUBMIT   ");;
		   submitButton.setBackground(Color.YELLOW);
		   submitButton.setBorder(new BevelBorder(BevelBorder.RAISED));
		   submitButton.addActionListener(e -> {
			                                    for(int i = alreadySubmmited ; i < nameFields.size() ; ++i) {
			                                    	final String itemName     = this.nameFields.get(i).getText().toString().trim();
			                                    	final String vendorName   = this.vendorsName.get(i).getText().toString().trim();
			                                    	final String itemPrice    = this.priceFields.get(i).getText().toString().trim();
			                                    	final String itemQuantity = this.quantityFields.get(i).getText().toString().trim();
			                                 	    if(itemPrice.length() > 0 && itemQuantity.length() > 0 && itemName.length() > 0) {
			                                    	Database.submitData(this.tableName,itemName,vendorName,itemPrice,itemQuantity);
			                                 	    }
			                                 	    else {
			                                 	    	JOptionPane.showMessageDialog(this,"FILL ALL FIELDS");
			                                 	    	continue;
			                                 	    }
			                                 	   submitButton.setEnabled(false);
			                                    }
			                                    this.alreadySubmmited = nameFields.size();
		                                       
		                                       });
		   this.submitButton = submitButton;
		   return submitButton;		  
	   }
	@Override
	public void putEntryFields()
	{      
		   if(this.vendorsName == null) this.vendorsName = new ArrayList<>();
		   final JTextField itemName = new JTextField();
		   final JTextField itemCost = new JTextField();
		   final JTextField quantity = new JTextField("1");
		   final JTextField vendorName = new JTextField();
		   final JLabel entryLabel = new JLabel();
		   nameFields.add(itemName);
		   priceFields.add(itemCost);
		   quantityFields.add(quantity);
		   vendorsName.add(vendorName);
		   entryLabel.setLayout(new GridLayout(4,2,0,5));
		   entryLabel.add(new JLabel("     ITEM NAME"));
		   entryLabel.add(itemName);
		   entryLabel.add(new JLabel("     ITEM COST  "));
		   entryLabel.add(itemCost);
		   entryLabel.add(new JLabel("      QUANTITY    "));
		   entryLabel.add(quantity);
		   entryLabel.add(new JLabel("    VENDOR NAME   "));
		   entryLabel.add(vendorName);
		   entryLabel.setMaximumSize(new Dimension(250,120));
		   this.entryPanel.add(entryLabel);
		   this.entryPanel.add(Box.createRigidArea(new Dimension(0,20)));
		   this.entryPanel.setPreferredSize(new Dimension(1130,entryPanel.getPreferredSize().height + 150));
		   this.setVisible(true);
	}
}
