package guifiles;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTabbedPane;
public class RecordsTabbedFrame extends JFrame{
    
    static RecordsPanel salesRecords;
	static RecordsPanel purchaseRecords;
	static RecordsPanel inventoryRecords;
	static int selectedTable = 0;
	public RecordsTabbedFrame()
	{   
		super("All Records");
		salesRecords = new RecordsPanel("sales_records");
		purchaseRecords = new RecordsPanel("purchase_records");
		inventoryRecords = new RecordsPanel("inventory_records");
		JTabbedPane recordsTabbedPane = new JTabbedPane();
		recordsTabbedPane.setForeground(Color.RED);
		recordsTabbedPane.addTab("                Sales Records                ",null,salesRecords,"Sales Records");
		recordsTabbedPane.addTab("              Purchase Records              ",null,purchaseRecords,"Purchase Records");
		recordsTabbedPane.addTab("               Inventory Records            ",null,inventoryRecords,"Inventory Records");
		recordsTabbedPane.setSize(WelcomeFrame.welcomeFrame.getSize());
		recordsTabbedPane.setSize(1166,618);
		recordsTabbedPane.setSelectedIndex(0);
		recordsTabbedPane.addChangeListener(e -> selectedTable = recordsTabbedPane.getSelectedIndex());
		this.setLayout(null);
		//this.setSize(1166,618);
		this.setSize(WelcomeFrame.welcomeFrame.getSize());
		this.setLocation(WelcomeFrame.welcomeFrame.getLocation());
		this.getContentPane().add(recordsTabbedPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	} 
	public static void main(String[] args)
	{   
		try {
		Database.establishConnection();
		}
		catch(Exception e) {}
		new RecordsTabbedFrame();
	}
}
