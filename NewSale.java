package guifiles;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
public class NewSale extends AddProduct
{   
	
	private JComboBox<String> itemName;
	private Bill bill;
	private ArrayList<String> entryData = new ArrayList<>();
	private String buyerName = "zzz";
	private JTextField buyer;
	public NewSale(JLabel topImage,String tableName)
	{   
		super(topImage,tableName);
	}
	@Override
	protected JLabel buttonsLabel()
	{
		JLabel buttonsLabel = super.buttonsLabel();
		buttonsLabel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonsLabel.add(printBillButton());
		return buttonsLabel;
	}
	@Override
	protected JButton submitButton()
	   {
		   super.submitButton = Setter.setButton("   SUBMIT   ",new Rectangle(0,0,0,0));
		   super.submitButton.addActionListener(e -> {
		                            	    final String selectedItem = this.itemName.getSelectedItem().toString();
		                            	    final String itemPrice    = super.priceField.getText().trim();
		                            	    final String itemQuantity = super.quantityField.getText().trim();
		                            	    final String buyerName    = this.buyer.getText().trim();
		                            	    if(itemPrice.matches("[0-9]+") && itemQuantity.matches("[0-9a-zA-Z]+") && buyerName.matches("[a-zA-Z\\s]+")) {
		                            	    	
		                            	        if(Integer.parseInt(selectedItem.replaceAll("[^0-9]","")) >= Integer.parseInt(this.quantityField.getText())) 
		                            	        {   
		                            	        if(JOptionPane.showConfirmDialog(this,  "Item Name ---     " + selectedItem.replaceAll("[0-9]"," ") +
		                            	        		                              "\nItem Price ---    " + itemPrice +
		                            	        		                              "\nItem Quantity --- " + itemQuantity +
		                            	        		                              "\nBuyer Name ---    " + buyerName ,"IS IT CORRECT ?", JOptionPane.YES_NO_OPTION) == 0)
		                            	        {
		                            	        Database.submitData(this.tableName,selectedItem.replaceAll("[0-9]","").trim(),buyerName,itemPrice,itemQuantity);
		                            	        if(this.buyerName.compareToIgnoreCase(buyerName) != 0) 
		                            	            this.entryData.clear();
		                            	        this.buyerName = buyerName;
		                            	        this.entryData.add(selectedItem);
		                            	        this.entryData.add(itemQuantity);
		                            	        this.entryData.add(itemPrice);
		                            	        this.priceField.setText("");
		                            	        }
		                            	        }
		        	                            else  JOptionPane.showMessageDialog(this,"NOT ENOUGH QUANTITY AVAILABLE");
		                                      }
		                                else  JOptionPane.showMessageDialog(this,"INVALID ENTRY");
		                            	    
		                            	      this.itemName.setModel(new DefaultComboBoxModel<String>(Database.getInventoryItems()));
		                                           });
		return super.submitButton;  		  
	 }
	private JButton printBillButton()
	{
		final JButton print = Setter.setButton("   PRINT BILL   ",new Rectangle(0,0,0,0));
		print.addActionListener(e -> {
			                         if(this.buyerName == null)
			                        	 JOptionPane.showMessageDialog(this,"YOU DID NOT SUBMIT ANYTHING");
			                         else
			                        	 showBillDialog();
		                             });
		return print;
	}
	@Override
	protected JLabel putEntryFields()
	{   
	    itemName  = new JComboBox<>(Database.getInventoryItems());
		itemName.setSelectedIndex(0);
		JLabel entryLabel = new JLabel();	
	    entryLabel.setLayout(new GridLayout(4,2,0,5));
		entryLabel.add(new JLabel("     ITEM NAME"));
		entryLabel.add(this.itemName);
		entryLabel.add(new JLabel("     ITEM PRICE  "));
		entryLabel.add(super.priceField);
		entryLabel.add(new JLabel("      QUANTITY    "));
		entryLabel.add(super.quantityField);
		entryLabel.add(new JLabel("    BUYER NAME    "));
		this.buyer = new JTextField();
		entryLabel.add(this.buyer);
	    entryLabel.setMaximumSize(new Dimension(250,120));
	    super.entryPanel.add(entryLabel);
	    return entryLabel;
	}
	private void showBillDialog()
	{
		JDialog billing = new JDialog();
    	billing.setLayout(null);
    	billing.setBounds(200,20,520,650);
    	this.bill = new Bill(this.entryData,this.buyerName);
    	billing.add(this.bill);
    	billing.setVisible(true);
    	billing.add(printButton());
	}
	private JButton printButton()
	{
		JButton print = Setter.setButton("   PRINT  ",new Rectangle(0,530,80,30));
		print.addActionListener(e -> {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(bill);
			if(job.printDialog())
			{
				try {
					job.print();
				}
				catch(PrinterException ex) {JOptionPane.showMessageDialog(this,ex.getMessage());}
			}
		});
		return print;
	}
}