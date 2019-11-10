package guifiles;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.print.*;
import java.time.LocalDate;
import java.awt.*;
public class Bill extends JPanel implements Printable{
    
	ArrayList<String> entryData;
	String buyerName;
	public Bill(ArrayList<String> entryData,String buyerName)
	{  
		this.buyerName = buyerName;
		this.entryData = entryData;
		this.setSize(520,650);
		this.setVisible(true);
	}
	@Override
	public int print(Graphics g,PageFormat format,int page)
	{   
		if(page >= 1) return Printable.NO_SUCH_PAGE;
		Graphics2D g2D = (Graphics2D)g;
		g2D.translate((int)format.getImageableX(),(int)format.getImageableY());
		drawBill(g2D);
		return Printable.PAGE_EXISTS;
	}
	@Override 
	public void paintComponent(Graphics g)
	{   
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		drawBill(g2D);
	}
    private void drawBill(Graphics2D g2D)
    {   
    	String temp;
    	int totalPrice = 0;
    	g2D.drawImage(new ImageIcon("F:\\bill.jpg")
		          .getImage(),0,0,this);
		g2D.drawString(this.buyerName,100,130);
		g2D.drawString(LocalDate.now().toString(),370,160);
		for(int i = 0,ycor = 260,srNo = 1; i < entryData.size() ; ycor+=25 , i+=3 , ++srNo) {
		g2D.drawString(String.valueOf(srNo),30,ycor);
		g2D.drawString(entryData.get(i).replaceAll("[0-9]",""),150,ycor );
		g2D.drawString(entryData.get(i + 1),350,ycor);
		g2D.drawString(temp = entryData.get(i + 2),400,ycor);
		totalPrice+=Integer.parseInt(temp);
		}
		g2D.drawString(String.valueOf(totalPrice),400,500);
    }
}
