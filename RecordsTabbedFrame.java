package guifiles;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTabbedPane;
public class RecordsTabbedFrame extends JFrame{
    
    static RecordsPanel salesRecords;
	static RecordsPanel purchaseRecords;
	static RecordsPanel inventoryRecords;
	static RecordsPanel productionRecords;
	public RecordsTabbedFrame()
	{   
		super("All Records");
		salesRecords = new RecordsPanel("sales_records",new String[] {"item name","buyer name","price","quantity","date"});
		purchaseRecords = new RecordsPanel("purchase_records",new String[] {"item name","vendor name","price","quantity","date"});
		productionRecords = new RecordsPanel("production_records",new String[] {"item name","item cost","item quantity","date"});
		inventoryRecords = new RecordsPanel("inventory_records",new String[] {"item name","item quantity"});
		JTabbedPane recordsTabbedPane = new JTabbedPane();
		recordsTabbedPane.setForeground(Color.RED);
		recordsTabbedPane.addTab("                Sales Records                ",salesRecords);
		recordsTabbedPane.addTab("              Purchase Records              ",purchaseRecords);
		recordsTabbedPane.addTab("             Production Records             ",productionRecords);
		recordsTabbedPane.addTab("               Inventory Records            ",inventoryRecords);
		this.getContentPane().add(recordsTabbedPane);
		Setter.setWindow(this);
	} 
}
