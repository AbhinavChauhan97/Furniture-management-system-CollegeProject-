package guifiles;
import java.awt.*;
import javax.swing.*;
public class NewPurchase extends AddProduct{
    
	private  JTextField vendorName;
	public NewPurchase(JLabel topImage , String tableName)
	{  
		super(topImage,tableName);
	}
	@Override
	protected JButton submitButton()
	   {
		   final JButton submitButton = Setter.setButton("  SUBMIT   ",new Rectangle(0,0,0,0));
		   submitButton.addActionListener(e -> {
			                                    	final String itemName     = this.nameField.getText().toString().trim();
			                                    	final String vendorName   = this.vendorName.getText().toString().trim();
			                                    	final String itemPrice    = this.priceField.getText().toString().trim();
			                                    	final String itemQuantity = this.quantityField.getText().toString().trim();
			                                 	    if(itemPrice.matches("[0-9]+")  && itemQuantity.matches("[0-9]+") && itemName.matches("[a-zA-Z]+") && vendorName.matches("[a-zA-Z]+")){
			                                 	    if(JOptionPane.showConfirmDialog(this, "Item Name ---      " + itemName +
          	        		                                                           "\nItem Price ---    " + itemPrice +
          	        		                                                           "\nItem Quantity --- " + itemQuantity +
          	        		                                                           "\nVendor Name ---   " + vendorName ,"IS IT CORRECT ?", JOptionPane.YES_NO_OPTION) == 0)
          	        
			                                    	Database.submitData(this.tableName,itemName,vendorName,itemPrice,itemQuantity);
			                                 	    }
			                                 	    else {
			                                 	    	JOptionPane.showMessageDialog(this,"INVALID ENTRY");
			                               
			                                 	    }
		                                       });
		   return submitButton;		  
	   }
	@Override
	public JLabel putEntryFields()
	{      
		   JLabel entryLabel = super.putEntryFields();
		   entryLabel.setLayout(new GridLayout(4,2,0,5));
		   entryLabel.add(new JLabel("    VENDOR NAME   "));
		   this.vendorName = new JTextField();
		   entryLabel.add(this.vendorName);
		   entryLabel.setMaximumSize(new Dimension(250,120));
		   return entryLabel;
	}
}
