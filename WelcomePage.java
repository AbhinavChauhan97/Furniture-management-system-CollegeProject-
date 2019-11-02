package guifiles;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BasicStroke;
import javax.swing.border.BevelBorder;
import java.sql.SQLException;
 class WelcomePage extends JPanel{
     
	private static  WelcomePage object;
	static  RecordsTabbedFrame recordsTabbedFrame;
	private WelcomePage()
	{   
		setLayout(null);
		setupWelcomePanel();
	}
    public static WelcomePage welcomePageObject()
    {
    	if(object == null)
    		object = new WelcomePage();
    	
    		return object;
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
    	                                      catch(InterruptedException | SQLException e) {}
    	                                      };
    	                                 
    final Thread t = new Thread(backgroundChanger);
    t.start();
    }
    @Override
    public void paintComponent(final Graphics g)
    {
          super.paintComponent(g);
          Graphics2D g2D = (Graphics2D)g;
          {
          Random r = new Random();
          int imageChooser = r.nextInt(6) + 1;
          g2D.drawImage(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\furnitureimage"+imageChooser+".jpg")
          .getImage(),0,0,this);
          }
          g2D.drawImage(new ImageIcon("C:\\Users\\\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\vishwakarma furnitures.png")
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
		final JButton salesImageButton = new JButton();
    	salesImageButton.setSize(250,150);
    	salesImageButton.setLocation(50,230);
    	salesImageButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    	salesImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewSales.jpg"));
    	salesImageButton.setPressedIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewSales.jpg"));
    	salesImageButton.addMouseListener(new MouseAdapter() {
    		
    		@Override
    		public void mouseEntered(final MouseEvent e)
    		{
    			salesImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewSalesLighter.jpg"));
    		}
    		@Override
    		public void mouseExited(final MouseEvent e)
    		{
    			salesImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewSales.jpg"));
    		}
    		@Override
    		public void mouseClicked(final MouseEvent e) 
    		{   
    			WelcomeFrame.welcomeFrame.setVisible(false);
    			JLabel topImage = new JLabel(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\widesaleentry.png"));
    			new NewSale(topImage,"sales_records");
    	    }
    		
    	});
        return salesImageButton;
	}
	
	private JButton newPurchaseButton()
	{
		final JButton purchaseImageButton = new JButton();
		purchaseImageButton.setSize(250,150);
    	purchaseImageButton.setLocation(320,230);
    	purchaseImageButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    	purchaseImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewPurchase.jpg"));
    	purchaseImageButton.setPressedIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewPurchase.jpg"));
    	purchaseImageButton.addMouseListener(new MouseAdapter() {
    		
    		@Override
    		public void mouseEntered(final MouseEvent e)
    		{
    			purchaseImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewPurchaseLighter.jpg"));
    		}
    		@Override
    		public void mouseExited(final MouseEvent e)
    		{
    			purchaseImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\NewPurchase.jpg"));
    		}
    		@Override
    		public void mouseClicked(final MouseEvent e)
    		{
    			WelcomeFrame.welcomeFrame.setVisible(false);
    			JLabel topImage = new JLabel(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\widepurchaseentry.png"));
    			new NewPurchase(topImage,"purchase_records");
    		}
    	});
        return purchaseImageButton;
	}
	
	private JButton recordsButton()
	{
		final JButton recordsImageButton = new JButton();
		recordsImageButton.setSize(250,150);
    	recordsImageButton.setLocation(590,230);
    	recordsImageButton.setBorder(new BevelBorder(BevelBorder.RAISED));
    	recordsImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\Records.jpg"));
        recordsImageButton.setPressedIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\Records.jpg"));
    	recordsImageButton.addMouseListener(new MouseAdapter() {
    		
    		@Override
    		public void mouseEntered(final MouseEvent e)
    		{
    			recordsImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\RecordsLighter.jpg"));
    		}
    		@Override
    		public void mouseExited(final MouseEvent e)
    		{
    			recordsImageButton.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\Records.jpg"));
    		}
    		@Override
    		public void mouseClicked(final MouseEvent e)
    		{
    			WelcomeFrame.welcomeFrame.setVisible(false);
    			recordsTabbedFrame = new RecordsTabbedFrame();
    		}
    	});
        return recordsImageButton;
	}
	
	private JButton addProductButton()
	{
		final JButton addProduct = new JButton();
		addProduct.setSize(250,150);
		addProduct.setLocation(860,230);
		addProduct.setBorder(new BevelBorder(BevelBorder.RAISED));
		addProduct.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\addproduct (1).jpg"));
		addProduct.setPressedIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\addproduct (1).jpg"));
		addProduct.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(final MouseEvent e)
			{
				addProduct.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\addProductLighter.jpg"));
			}
			@Override
			public void mouseExited(final MouseEvent e)
			{
				addProduct.setIcon(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\addproduct (1).jpg"));
			}
			public void mouseClicked(final MouseEvent e)
			{
				WelcomeFrame.welcomeFrame.setVisible(false);
				JLabel topImage = new JLabel(new ImageIcon("C:\\Users\\Destiny_Computers\\eclipse-workspace\\College Project\\src\\guifiles\\Images\\addproduct.png"));
				new AddProduct(topImage,"inventory_records");
			}
		});
		return addProduct;
	}
}

 