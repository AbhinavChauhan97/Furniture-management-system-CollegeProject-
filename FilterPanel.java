package guifiles;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import javax.swing.border.BevelBorder;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
public class FilterPanel extends JPanel{
 
	JComboBox<String> day;
	JComboBox<String> month;
	JComboBox<String> year;
    String selectedDay = "All";
	String selectedMonth;
    String selectedYear;
	final String tableName;
	private static final int START_YEAR = 2019;
    enum Months{ January , february , march , april , may , june, july , august , september , october , november , decemeber; };
	public FilterPanel(final String tableName)
	{   
		setupFilters();
		this.tableName = tableName;
		this.add(Box.createRigidArea(new Dimension(0,0)));
		this.setLayout(new GridLayout(6,1,0,100));
		this.setSize(200,618);
		this.setBackground(Color.YELLOW);
		this.add(FilterLabel("YEAR    ",this.year));
		this.add(FilterLabel("MONTH",this.month));
		this.add(FilterLabel("DAY      ",this.day));
	    this.add(filterButton());
	    this.setBorder(new BevelBorder(BevelBorder.RAISED));
	}
	@Override
	public void paintComponent(final Graphics g)
	{    
		super.paintComponent(g);
		g.setFont(new Font("Serif",Font.BOLD,20));
		g.setColor(Color.RED);
		g.drawString("SELECT DATE",35,50);
	}
	private void setupFilters()
	{   
		final int currentDay     = LocalDateTime.now().getDayOfMonth();
		final int currentMonth   = LocalDateTime.now().getMonthValue();
		final int currentYear    = LocalDateTime.now().getYear();
	    final DefaultComboBoxModel<String> allDays   = new DefaultComboBoxModel<String>(new String[] {"All","1","2","3","4","5","6","7","8","9","10","11","12",
	    		                                                                                       "13","14","15","16","17","18","19","20"
			                                                                                           ,"21","22","23","24","25","26","27","28","29","30","31"});
	    final DefaultComboBoxModel<String> allMonths = new DefaultComboBoxModel<String>(new String[] {"All","Jauary","Fabruary","March","April","May","June","July"
	    		                                                                                     ,"August","September","October","Novemeber","December"});
	  
	    final String[] years = new String[currentYear - START_YEAR + 2];
	    years[0] = "All";
	    years[1] = String.valueOf(START_YEAR);
	    for(int i = 2 ; i < years.length ; ++i)
	    years[i] = String.valueOf(START_YEAR + i - 1);
	   
		final String[] currentMonthDays = new String[currentDay + 1];
    	currentMonthDays[0] = "All";
        for(int i = 1 ; i < currentMonthDays.length ; ++i)
        currentMonthDays[i] = String.valueOf(i);
        
        this.day   = new JComboBox<>(allDays);
		this.month = new JComboBox<>(allMonths);
		this.year  = new JComboBox<>(years);
		
        final DefaultComboBoxModel<String> currentMonthDaysModel;
        currentMonthDaysModel =  new DefaultComboBoxModel<String>(currentMonthDays);
		this.year.addItemListener(e -> {
                                        if(e.getStateChange() == ItemEvent.SELECTED)
                                        {   
                                        final DefaultComboBoxModel<String> currentYearMonthsModel;
            	                        final String selectedYear = new String((String)this.year.getSelectedItem());
                                     	if(selectedYear.equals(String.valueOf(currentYear)) == true)
                                     	{   
            	                        	
            		                        this.day.setModel(currentMonthDaysModel);
            		
            	                         	String[] months = new String[currentMonth + 1];
            	    
                                            months[0] = "All";
            	                           	for(int i = 1 ; i < months.length ; ++i)
            			                    months[i] =  Months.values()[i - 1].toString();
            		
            		                        currentYearMonthsModel =  new DefaultComboBoxModel<String>(months);
            		                        this.month.setModel(currentYearMonthsModel);
            	                         }
                                     	else
                                     	{
                                     		this.day.setModel(allDays);
                                     		this.month.setModel(allMonths);
                                     	}
                                     	if(year.getSelectedIndex() == 0)
                                     		this.selectedYear ="All";
                                     	else
                                     	this.selectedYear = selectedYear;
                                        }
		                                });
		
		this.month.addItemListener(e -> {
			                              
			                              if(e.getStateChange() == ItemEvent.SELECTED)
			                              {   
			                            	  final int selectedMonth = this.month.getSelectedIndex();
			                            	  if(selectedMonth == 0)
			                            	  this.selectedMonth = "All";
			                            	  else {
			                            	  if(selectedMonth != currentMonth)    this.day.setModel(allDays);
			                            	  else                                 this.day.setModel(currentMonthDaysModel); 
			                            	  this.selectedMonth = String.valueOf(selectedMonth);
			                            	  }
			                              }
		                                });
		
		this.day.addItemListener(e -> {
			                             if(e.getStateChange() == ItemEvent.SELECTED)
			                             {   
			                            	 if(this.day.getSelectedIndex() == 0)
			                            		 this.selectedDay = "All";
			                            	 else
			                            	 this.selectedDay = (String)this.day.getSelectedItem();
			                             }
	                                  });
		
		
		this.year.setSelectedIndex(currentYear - START_YEAR + 1);
		this.month.setSelectedIndex(currentMonth);
		this.day.setSelectedIndex(0);
		this.day.setMaximumRowCount(5);
		this.month.setMaximumRowCount(5);
		this.year.setMaximumRowCount(5);
	}
	private JLabel FilterLabel(final String title ,final JComboBox<String> item)
	{   
		item.setBackground(Color.RED);
		item.setForeground(Color.YELLOW);
		final JLabel filterLabel = new JLabel();
		filterLabel.setLayout(new BoxLayout(filterLabel,BoxLayout.X_AXIS));
	    filterLabel.setPreferredSize(new Dimension(150,30));
	    JLabel entryLabel = new JLabel(title + "    ");
	    entryLabel.setForeground(Color.RED);
		filterLabel.add(entryLabel);
		filterLabel.add(item);
		return filterLabel;
	}
	public JButton filterButton()
	{
		final JButton filterButton = new JButton("APPLY FILTERS");
		filterButton.setBackground(Color.RED);
		filterButton.setForeground(Color.YELLOW);
		filterButton.setBorder(new BevelBorder(BevelBorder.RAISED));
		filterButton.addActionListener(e -> {
			                                 if(this.tableName == "sales_records") {
			                                 RecordsTabbedFrame.salesRecords.remove(RecordsTabbedFrame.salesRecords.recordsTable);
			                                 RecordsTabbedFrame.salesRecords.recordsTable = null;
			                                 RecordsTabbedFrame.salesRecords.recordsTable = new RecordsTable(Database.getFilteredRecords
                 		                                                                   (this.selectedDay,this.selectedMonth,this.selectedYear,this.tableName)
                 		                                                                   , new String[] {"item name","buyer name","price","quantitiy","date"});
			                              
			                                 RecordsTabbedFrame.salesRecords.add(RecordsTabbedFrame.salesRecords.recordsTable);
			                                 }
			                                 else 
			                                 {
			                                	 RecordsTabbedFrame.purchaseRecords.remove(RecordsTabbedFrame.purchaseRecords.recordsTable);
				                                 RecordsTabbedFrame.purchaseRecords.recordsTable = null;
				                             
				                                 RecordsTabbedFrame.purchaseRecords.recordsTable = new RecordsTable(Database.getFilteredRecords
	                 		                                                               (this.selectedDay,this.selectedMonth,this.selectedYear,this.tableName)
	                 		                                                               , new String[] {"item name","vendor name","price","quantity","date"});
				                              
				                                 RecordsTabbedFrame.purchaseRecords.add(RecordsTabbedFrame.purchaseRecords.recordsTable); 
			                                 } 
			                                 }
		                              );
		return filterButton;
	}
}
