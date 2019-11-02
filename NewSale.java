package guifiles;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import  javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
public class NewSale extends AddProduct
{   
	
	private ArrayList<JComboBox<String>> itemNames;
	private Bill bill;
	public NewSale(JLabel topImage,String tableName)
	{   
		super(topImage,tableName);
		
	}
	@Override
	protected JButton submitButton()
	   {
		   final JButton submitButton = new JButton("   SUBMIT   ");
		   submitButton.setBackground(Color.YELLOW);
		   submitButton.setBorder(new BevelBorder(BevelBorder.RAISED));
		   submitButton.addActionListener(e -> {
		                                for(int i = this.alreadySubmmited ; i < nameFields.size() ; ++i) {
		                            	    final String selectedItem = this.itemNames.get(i).getSelectedItem().toString();
		                            	    final String itemPrice    = this.priceFields.get(i).getText().trim();
		                            	    final String itemQuantity = this.quantityFields.get(i).getText().trim();
		                            	    final String itemName     = this.nameFields.get(i).getText().trim();
		                            	   if(itemPrice.length() > 0 && itemQuantity.length() > 0 && itemName.length() > 0) {
		                            	        if(Integer.parseInt(selectedItem.replaceAll("[^0-9]","")) >= Integer.parseInt(this.quantityFields.get(i).getText())) 
		                            	        {   
		                            	        	
		                            	        showBillDialog();
		                            	        Database.submitData(this.tableName,selectedItem.replaceAll("[0-9]","").trim(),itemName,itemPrice,itemQuantity);
		                            	        submitButton.setEnabled(false);
		                            	        }
		        	                            else {
		        	                            	JOptionPane.showMessageDialog(this,"NOT ENOUGH QUANTITY AVAILABLE");
		        	                            	continue;
		        	                            }
		                                      }
		                                else {
		                                	JOptionPane.showMessageDialog(this,"FILL ALL FIELDS");
		                                	continue;
		                                }
		                            	
		                                }
		                                this.alreadySubmmited = nameFields.size();
		                            	
		                            	
		                                       });
		   this.submitButton = submitButton;
		   return submitButton;		  
	 }
	@Override
	protected void putEntryFields()
	{   
		if(this.itemNames == null) this.itemNames = new ArrayList<>();
		JComboBox<String> itemName  = new JComboBox<>(Database.getInventoryItems());
		itemName.setSelectedIndex(0);
		JTextField itemPrice = new JTextField();
		JTextField buyerName = new JTextField();
		JTextField quantity  = new JTextField("1");
		this.priceFields.add(itemPrice);
		this.quantityFields.add(quantity);
		this.nameFields.add(buyerName);
		itemNames.add(itemName);
		JLabel entryLabel = new JLabel();
		entryLabel.setLayout(new GridLayout(4,2,0,5));
		entryLabel.add(new JLabel("     ITEM NAME"));
		entryLabel.add(itemName);
		entryLabel.add(new JLabel("     ITEM PRICE  "));
		entryLabel.add(itemPrice);
		entryLabel.add(new JLabel("      QUANTITY    "));
		entryLabel.add(quantity);
		entryLabel.add(new JLabel("    BUYER NAME    "));
		entryLabel.add(buyerName);
	    entryLabel.setMaximumSize(new Dimension(250,120));
	    this.entryPanel.add(entryLabel);
		this.entryPanel.add(Box.createRigidArea(new Dimension(0,20)));
		this.entryPanel.setPreferredSize(new Dimension(1130,entryPanel.getPreferredSize().height + 150));
		this.setVisible(true);
	}	
	private void showBillDialog()
	{
		JDialog billing = new JDialog();
    	billing.setLayout(null);
    	billing.setSize(520,650);
    	billing.setLocation(200,20);
    	this.bill = new Bill(itemNames,quantityFields,priceFields,nameFields.get(0).getText());
    	billing.add(bill);
    	billing.setVisible(true);
    	billing.add(printButton());
	}
	private JButton printButton()
	{
		JButton print = new JButton("PRINT");
		print.setForeground(Color.YELLOW);
		print.setBackground(Color.RED);
		print.setSize(80,30);
		print.setLocation(0,530);
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