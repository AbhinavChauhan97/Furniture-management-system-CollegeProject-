package guifiles;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.awt.BasicStroke;
import java.sql.SQLException;
 class WelcomePage extends JPanel{
     
	static  RecordsTabbedFrame recordsTabbedFrame;
	final String ImagesPath = "C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\";
	static JFrame welcomeFrame;
	public WelcomePage()
	{   
		setLayout(null);
		setupWelcomePanel();
	}
    private void backgroundChanger() {
    	
    final Runnable backgroundChanger = () -> {
    	                                       try {
    	                                       Database.establishConnection();
    	                                       while(true){
    	                                        Thread.sleep(1000);
    	                                        repaint();
    	                                       }
    	                                      }
    	                                      catch(InterruptedException | SQLException e) {JOptionPane.showMessageDialog(null,e.getMessage());}
    	                                      };
    	                                 
    final Thread t = new Thread(backgroundChanger);
    t.start();
    }
    @Override
    public void paintComponent(final Graphics g)
    {
          Graphics2D g2D = (Graphics2D)g;
          {
          Random r = new Random();
          int imageChooser = r.nextInt(6) + 1;
          g2D.drawImage(new ImageIcon(this.ImagesPath + "furnitureimage"+imageChooser+".jpg")
          .getImage(),0,0,this);
          }
          g2D.drawImage(new ImageIcon(this.ImagesPath + "vishwakarma furnitures.png")
          .getImage(),100,30,this);
          g2D.setStroke(new BasicStroke(3));
          g2D.setColor(Color.RED);
          g2D.drawRect(40,215,1080,180);
    }
    
	private void setupWelcomePanel()
	{   
		this.add(newSalesButton());
		this.add(newPurchaseButton());
		this.add(recordsButton());
		this.add(addProductButton());
		this.backgroundChanger();
	} 
	
	private JButton newSalesButton()
	{   
		final JButton salesImageButton = buttonSetter("NewSales.jpg","NewSalesLighter.jpg",new Rectangle(50,230,250,150));
		final JLabel topImage = new JLabel(new ImageIcon(this.ImagesPath + "widesaleentry.png"));
		salesImageButton.addActionListener(e -> new NewSale(topImage,"sales_records"));
        return salesImageButton;
	}
	
	private JButton newPurchaseButton()
	{
		final JButton purchaseImageButton = buttonSetter("NewPurchase.jpg","NewPurchaseLighter.jpg",new Rectangle(320,230,250,150));
		final JLabel topImage = new JLabel(new ImageIcon(this.ImagesPath + "widepurchaseentry.png"));
		purchaseImageButton.addActionListener(e -> new NewPurchase(topImage,"purchase_records"));
        return purchaseImageButton;
	}
	
	private JButton recordsButton()
	{
		final JButton recordsImageButton = buttonSetter("Records.jpg","RecordsLighter.jpg",new Rectangle(590,230,250,150));
		recordsImageButton.addActionListener(e -> recordsTabbedFrame = new RecordsTabbedFrame());
        return recordsImageButton;
	}
	
	private JButton addProductButton()
	{
		final JButton addProduct = buttonSetter("addproduct (1).jpg","addproductLighter.jpg",new Rectangle(860,230,250,150));
		final JLabel topImage = new JLabel(new ImageIcon(this.ImagesPath + "addproduct.png"));
		addProduct.addActionListener(e -> new AddProduct(topImage,"production_records"));
		return addProduct;
	}
	
 private JButton buttonSetter(String iconImage,String mouseEnteredImage,Rectangle bounds)
 {   
	 JButton button = Setter.setButton(null,bounds);
	 button.setIcon(new ImageIcon(this.ImagesPath + iconImage));
	 button.addMouseListener(new MouseAdapter()
			 {
		     @Override 
		     public void mouseEntered(MouseEvent e)
		     {
		    	 button.setIcon(new ImageIcon(WelcomePage.this.ImagesPath + mouseEnteredImage));
		     }
		     @Override
		     public void mouseExited(MouseEvent e)
		     {
		    	 button.setIcon(new ImageIcon(WelcomePage.this.ImagesPath + iconImage));
		     }
		     @Override
		     public void mouseClicked(MouseEvent e)
		     {
		    	 welcomeFrame.setVisible(false);
		     }
			 });
	 return button;
 }
 public static void main(String[] args)
	{  
       welcomeFrame = new JFrame("Welcome");
	   welcomeFrame.getContentPane().add(new WelcomePage());
	   Setter.setWindow(welcomeFrame);
	}
 }