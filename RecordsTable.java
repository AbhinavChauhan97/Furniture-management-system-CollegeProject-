package guifiles;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
public class RecordsTable  extends JScrollPane{
    
	final JTable recordsTable;

	public RecordsTable(String[][] filteredRecords,String[] colums) 
	{   
		this.recordsTable = new JTable(filteredRecords,colums);
		this.recordsTable.setBackground(Color.CYAN);
		this.recordsTable.setForeground(Color.RED);
		this.recordsTable.getTableHeader().setBackground(Color.RED);
		this.recordsTable.getTableHeader().setForeground(Color.YELLOW);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    this.setBounds(205,0,945,500);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setViewportView(recordsTable);
	}
}
