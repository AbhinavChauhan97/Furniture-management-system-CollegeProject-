package guifiles;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.print.PrinterException;
public class RecordsPanel extends JPanel{
	
    RecordsTable recordsTable;
	public RecordsPanel(String tableName)  {
		this.setLayout(null);
		if(tableName.compareTo("inventory_records") == 0 ) {
			recordsTable  = new RecordsTable(Database.getInventoryTable(), new String[] {"item name","item cost","item quantity"});
		}
		else if(tableName.compareTo("sales_records") == 0){
	    recordsTable = new RecordsTable(Database.getFilteredRecords("All",String.valueOf(LocalDate.now().getMonthValue())
	    		                                                   ,String.valueOf(LocalDate.now().getYear()),tableName),
	    		                                       new String[] {"item name","buyer name","price","quantitiy","date"});
		this.add(new FilterPanel("sales_records"));
		}
		else {
			recordsTable = new RecordsTable(Database.getFilteredRecords("All",String.valueOf(LocalDate.now().getMonthValue())
					                                                  ,String.valueOf(LocalDate.now().getYear()),tableName),
					                                   new String[] {"item name","vendor name","item price","quantity","date"});
			this.add(new FilterPanel("purchase_records"));
		}
		this.add(recordsTable);
		this.add(backButton());
		this.add(printButton());
        this.setBackground(Color.RED);	
	}
	private JButton printButton()
	{
		final JButton print = new JButton("PRINT");
		print.setSize(100,40);
		print.setLocation(870,510);
		print.setBackground(Color.YELLOW);
		print.setForeground(Color.RED);
		print.setBorder(new BevelBorder(BevelBorder.RAISED));
		print.addActionListener(e -> {  
			                            try {
			                            	switch(RecordsTabbedFrame.selectedTable)
			                            	{
			                            	case 0 : RecordsTabbedFrame.salesRecords.recordsTable.recordsTable.print();
			                            	break;
			                            	case 1 : RecordsTabbedFrame.purchaseRecords.recordsTable.recordsTable.print();
			                            	break;
			                            	case 2 : RecordsTabbedFrame.inventoryRecords.recordsTable.recordsTable.print();
			                            	}
			                            }
			                            catch(PrinterException ex) {JOptionPane.showMessageDialog(this,ex.getMessage());}
	                               	 });
		return print;
	}
	private JButton backButton()
	{
		final JButton back = new JButton("BACK");
		back.setSize(100,40);
		back.setLocation(1000,510);
		back.setBackground(Color.YELLOW);
		back.setForeground(Color.RED);
		back.setBorder(new BevelBorder(BevelBorder.RAISED));
		back.addActionListener(e -> {
			                            WelcomePage.recordsTabbedFrame.setVisible(false);
			                            WelcomeFrame.welcomeFrame.setVisible(true);
	                                });
		return back;
	}
	public static void main(String[] args)
	{   
		try {
			Database.establishConnection();
			}
			catch(Exception e) {}
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		//RecordsPanel.recordsTable
		//frame.getContentPane().add(comp)
		frame.setSize(1166,618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new RecordsPanel("sales_records"));
		//new RecordsPanel("inventory_records");
		frame.setVisible(true);
		
		
	}
}
