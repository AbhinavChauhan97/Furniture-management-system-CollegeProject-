package guifiles;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;
public class Database {
  
  private static Statement statement;
  public static void establishConnection() throws SQLException
  {
	  final Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","asdfjkl;");
	  statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); 
  }

  public static void submitData(String tableName,String... data) 
  {   
	  final int day   = LocalDate.now().getDayOfMonth();
	  final int month = LocalDate.now().getMonthValue();
	  final int year  = LocalDate.now().getYear();
	  ResultSet result;
	  try {
	  if(tableName == "inventory_records") {
	  result = statement.executeQuery("SELECT * FROM inventory_records WHERE ITEM_NAME = '" + data[0] + "'");
	  if(result.next())
	  {   
		  int currentQuantity  = Integer.parseInt(result.getString(3).trim());
		  int newQuantity      = Integer.parseInt(data[2]);
		  int currentCost      = Integer.parseInt(result.getString(2));
		  int addedCost        = Integer.parseInt(data[1]);
		  int newTotalCost     = addedCost + currentCost;
		  int newTotalQuantity = currentQuantity + newQuantity;
		  statement.executeUpdate("UPDATE inventory_records SET ITEM_COST = '" + newTotalCost + "'" + " ,ITEM_QUANTITY = '" + newTotalQuantity + "' WHERE ITEM_NAME ='" + data[0] + "'" );
	  }
	  else 
	  statement.executeUpdate("INSERT INTO inventory_records values('"+ data[0] + "','" + data[1] + "','" + data[2] + " ')");
	  }
	  else    
	  statement.executeUpdate("INSERT INTO "+ tableName + " values('"+ data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "'," + day + "," + month + "," + year +" )");
	  if(tableName == "sales_records")
	  {   
		  result = statement.executeQuery("SELECT ITEM_QUANTITY FROM inventory_records WHERE ITEM_NAME ='" + data[0] +"'");
		  result.next();
		  int currentQuantity  = Integer.parseInt(result.getString(1).trim());
		  int soldQuantity     = Integer.parseInt(data[3]);
		  int newTotalQuantity = currentQuantity - soldQuantity;
		  statement.executeUpdate("UPDATE inventory_records SET ITEM_QUANTITY = '" + newTotalQuantity + "' WHERE ITEM_NAME ='" + data[0] + "'");
	  }
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
	 
  }
  public static String[][] getFilteredRecords(String day , String month , String year , String table)
  {   
	  
	  String[][] records = new String[1][1];
	  ResultSet result;
	  try { 
		   
		   if(day.compareTo("All") != 0 && month.compareTo("All") != 0 && year.compareTo("All") != 0) 
		   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE day = '" + day + "'" + "AND month = '" + month + "'" + "AND year = '" + year + "'");
		   else if(day.compareTo("All") != 0 && month.compareTo("All") != 0 && year.compareTo("All") == 0)
			   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE day = '" + day + "'" + "AND month = '" + month + "'");
		   else if(day.compareTo("All") != 0 && month.compareTo("All") == 0 && year.compareTo("All") != 0)
			   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE day = '" + day + "'" + "AND year = '" + year + "'");
		   else if(day.compareTo("All") == 0 && month.compareTo("All") != 0 && year.compareTo("All") != 0)
			   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE  month = '" + month + "'" + " AND year = '" + year + "'");
		   else if(day.compareTo("All") != 0 && month.compareTo("All") == 0 && year.compareTo("All") == 0)
			   result =  statement.executeQuery("SELECT FROM " + table + " WHERE day = '" + day + "'");
		   else if(day.compareTo("All") == 0 && month.compareTo("All") == 0 && year.compareTo("All") != 0)
			   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE year = '" + year + "'");
		   else if(day.compareTo("All") == 0 && month.compareTo("All") != 0 && year.compareTo("All") == 0)
			   result =  statement.executeQuery("SELECT * FROM " + table + " WHERE month ='" + month + "'");
		   else 
			   result =  statement.executeQuery("SELECT * FROM " + table);
			   
		   result.last();
		   records = new String[result.getRow()][5];
		   result.beforeFirst();
		   
		   for(int i = 0 ; result.next() ; ++i)
		   {    
				 records[i][0] = result.getString(1);
				 records[i][1] = result.getString(2);
				 records[i][2] = result.getString(3);
				 records[i][3] = result.getString(4);
				 records[i][4] = result.getString(5) + "-" + result.getString(6) +  "-" + result.getString(7); 
		   }
		   
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
	  return records;
  }
  public static String[][] getInventoryTable()
  {
	  String[][] records = new String[1][1];
	  ResultSet result;
	  try { 
		   
		 
			   result = statement.executeQuery("SELECT * FROM inventory_records");
			   result.last();
			   records = new String[result.getRow()][3];
			   result.beforeFirst();
			  
			  for(int i = 0 ; result.next() ; ++i)
			   {
				   records[i][0] = result.getString(1);
				   records[i][1] = result.getString(2);
				   records[i][2] = result.getString(3);
			   }
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null, e.getMessage());}
	 return records;
	
  }
  public static String[] getInventoryItems()
  {   
	  String[] items = new String[1];
	  try {
	  ResultSet result = statement.executeQuery("SELECT * FROM inventory_records");
	   result.last();
	   items = new String[result.getRow()];
	   result.beforeFirst();
	   for(int i = 0 ; result.next() ; ++i)
		   items[i] = result.getString(1) + String.format("%10s",result.getString(3));
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
	  return items; 
  }
  
  public static void main(String[] args)
  {
		try {
			Database.establishConnection();
			}
			catch(Exception e) {}
		String[][] arr = Database.getFilteredRecords("All",String.valueOf(LocalDate.now().getMonthValue())
                ,String.valueOf(LocalDate.now().getYear()),"sales_records");
		for(String[] s : arr)
			for(String ss : s)
				System.out.print(ss + "  ");	
}
}
