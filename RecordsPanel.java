package guifiles;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.awt.print.PrinterException;
public class RecordsPanel extends JPanel{
	
    RecordsTable recordsTable;
	public RecordsPanel(String tableName,String[] columns)  {
		this.setLayout(null);
		recordsTable = new RecordsTable(Database.getFilteredRecords("All",String.valueOf(LocalDate.now().getMonthValue())
			                                                        ,String.valueOf(LocalDate.now().getYear()),tableName),columns);
		if(tableName != "inventory_records")
		this.add(new FilterPanel(tableName,this,columns));
		this.add(recordsTable);
		this.add(backButton());
		this.add(printButton());
        this.setBackground(Color.RED);	
	}
	private JButton printButton() 
	{
		final JButton print = Setter.setButton("PRINT",new Rectangle(870,510,100,40));
		print.addActionListener(e -> { 
			                            try {
			                            	 this.recordsTable.recordsTable.print();
			                            }
			                            catch(PrinterException ex) {JOptionPane.showMessageDialog(this,ex.getMessage());}
	                               	 });
		return print;
	}
	private JButton backButton()
	{
		final JButton back = Setter.setButton("BACK",new Rectangle(1000,510,100,40));
		back.addActionListener(e -> {
			WelcomePage.recordsTabbedFrame.setVisible(false);
			WelcomePage.welcomeFrame.setVisible(true);
		});
		return back;
	}
}
