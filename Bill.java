package guifiles;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import  javax.swing.JInternalFrame;
import java.awt.print.*;
import java.time.LocalDate;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Bill extends JPanel implements Printable{
    
	ArrayList<JComboBox<String>> items;
	ArrayList<JTextField> quantities;
	ArrayList<JTextField> prices;
	String name;
	public Bill(ArrayList<JComboBox<String>> items,ArrayList<JTextField> quantities,ArrayList<JTextField> prices,String name)
	{   
		this.items = items;
		this.quantities = quantities;
		this.prices = prices;
		this.name = name;
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
    	g2D.drawImage(new ImageIcon("F:\\bill.jpg")
		          .getImage(),0,0,this);
		g2D.drawString(this.name,100,130);
		g2D.drawString(LocalDate.now().toString(),370,160);
		for(int i = 0,ycor = 270 ; i < items.size() ; ycor+=40 , ++i) {
		g2D.drawString(items.get(i).getSelectedItem().toString().replaceAll("[0-9]",""),150,ycor );
		g2D.drawString(quantities.get(i).getText(),350,ycor );
		g2D.drawString(prices.get(i).getText(),400,ycor);
		}
    }
	public static void main(String[] args)
	{  
		JDialog p = new JDialog();
		p.setBackground(Color.RED);
		p.setSize(520,650);
		//p.add(new Bill());
		p.setVisible(true);
		
	}
}
