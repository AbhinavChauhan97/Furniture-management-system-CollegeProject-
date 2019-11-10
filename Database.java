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
	     if(tableName != "purchase_records")
		  {   
			  result = statement.executeQuery("SELECT ITEM_QUANTITY FROM inventory_records WHERE ITEM_NAME ='" + data[0] +"'");
			  result.next();
			  int currentQuantity  = Integer.parseInt(result.getString(1).trim());
			  int newTotalQuantity;
			  if(tableName == "sales_records") 
			  {
			  int soldQuantity     = Integer.parseInt(data[3]);
			  newTotalQuantity = currentQuantity - soldQuantity;
			  statement.executeUpdate("INSERT INTO "+ tableName + " values('"+ data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "'," + day + "," + month + "," + year +" )");
			  }
			  else 
			  {
			  int createdQuantity = Integer.parseInt(data[2]);
			  newTotalQuantity = currentQuantity + createdQuantity;
			  statement.executeUpdate("INSERT INTO "+ tableName + " values('"+ data[0] + "','" + data[1] + "','" + data[2] +  "'," + day + "," + month + "," + year +" )");
			  }
			  statement.executeUpdate("UPDATE inventory_records SET ITEM_QUANTITY = '" + newTotalQuantity + "' WHERE ITEM_NAME ='" + data[0] + "'");
		  }
         else
	     statement.executeUpdate("INSERT INTO "+ tableName + " values('"+ data[0] + "','" + data[1] + "','" + data[2] + "','" + data[3] + "'," + day + "," + month + "," + year +" )");
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
  }
  public static String[][] getFilteredRecords(String day , String month , String year , String table)
  {   
	  String[][] records = new String[1][1];
	  ResultSet result;
	  try { 
		   if(table == "inventory_records")
		   result =  statement.executeQuery("SELECT * FROM " + table);
		   else {
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
		   }
		  
		   result.last();
		   int columnCount = result.getMetaData().getColumnCount();
		   if(table == "inventory_records")
		   records = new String[result.getRow()][columnCount + 1];
		   else
		   records = new String[result.getRow()][columnCount - 2];
		   result.beforeFirst();
		   for(int i = 0,j ; result.next() ; ++i)
		   {    
			   for(j = 0 ; j < records[0].length - 1 ; ++j) 
			       records[i][j] = result.getString(j + 1);
			   
			 if(table != "inventory_records") {
			 records[i][j] = result.getString(j + 1) + "-" + result.getString(j + 2) +  "-" + result.getString(j + 3);  
			 }
		   } 
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
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
		   items[i] = result.getString(1) + String.format("%10s",result.getString(2));
	  }
	  catch(SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
	  return items; 
  }
}