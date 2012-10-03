import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class JItemPanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox[] CB;
	private JLabel lName, lPath, lIconRun,lIconDel;
	private LItem Item;
	
	public LItem getItem() 
	{
		return Item;
	}

	public void setItem(LItem item) 
	{
		Item = item;
	}
	
	public void setNewCPU()
	{
		Item.setNumbOfCores(getCPUCheck());
	}

	public int getCPUCheck()
	{
		String binary = "";
		for (int i = 2; i < this.getComponentCount()-2; i++) 
		{
			Component Aktuell = this.getComponent(i);
			JOptionPane.showMessageDialog(null, Aktuell.getClass().getSimpleName());
			JCheckBox cb = (JCheckBox) Aktuell;
			if (cb.isSelected()) 
			{
				binary = '1'+binary;
			}
			else binary = '0'+binary;
		}	
		
		
		return Integer.parseInt(binary,2);
		
	}
	
	public Component getItem(Point e, Integer Index)
	{
		Integer Y,X,ymin,ymax,xmin,xmax;
		X = (int) e.getX();
		Y = (int) (e.getY()-(Index*70));
		//JOptionPane.showMessageDialog(null, e);
		//JOptionPane.showMessageDialog(null,String.valueOf(CB[0].getX())+'\n'+String.valueOf(CB[0].getY()));
		
		for (int i = 0; i < this.getComponentCount(); i++) 
		{
			Component Aktuell = this.getComponent(i);
			ymin = Aktuell.getY();
			ymax = Aktuell.getY()+Aktuell.getHeight();
			xmin = Aktuell.getX();
			xmax = Aktuell.getX()+Aktuell.getWidth();
			//JOptionPane.showMessageDialog(null,String.valueOf(e)+'\n'+String.valueOf(Y)+'\n'+String.valueOf(ymin)+'\n'+String.valueOf(ymax)+'\n'+String.valueOf(xmin)+'\n'+String.valueOf(xmax)+'\n'+Aktuell.getClass().getSimpleName());
			if (Y<ymax && Y>ymin && X>xmin && X<xmax) 
			{		
				//JOptionPane.showMessageDialog(null, this.getComponent(i).getClass().getSimpleName());
				return Aktuell;
			
			}
			
		}
		
		//JOptionPane.showMessageDialog(null,String.valueOf(getComponentAt(e).getClass().getSimpleName()));
		//getComponentAt(e);
		return this;
		
	}
	
	//public JItemPanel(int CPUNumb, String Name, String Path) 
	public JItemPanel(LItem Item)
	{
		int count = 0;
		int Cores = Runtime.getRuntime().availableProcessors();
		CB = new JCheckBox[Cores];
		
		this.Item = Item;
		
		this.setMinimumSize(new Dimension(350,70));
		this.setPreferredSize(new Dimension(350,70));
		this.setBounds(0, 0, 350, 70);
		this.setLayout(null);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				
		lName = new JLabel(Item.getName());
		lName.setBounds(4,2,220,26);
		lName.setForeground(Color.WHITE);
		this.add(lName);
		lPath = new JLabel(Item.getPath());
		lPath.setBounds(4,40,260,26);
		lPath.setForeground(Color.WHITE);
		this.add(lPath);
		
		String binary = Integer.toBinaryString(Item.getNumbOfCores());
		if (binary.length() < Cores)
		{
			for (int i = binary.length(); i < Cores; i++) 
			{
				binary +='0';
			}
		}
		//JOptionPane.showMessageDialog(null, binary);
		
		
		while (Cores -4 >= 0) 						//immer max. 4 Checkboxen auf eine Reihe
		{
			Cores -= 4;
			
			for (int i = 0; i < 4; i++) {
				//JOptionPane.showMessageDialog(null, String.valueOf(count)+" "+String.valueOf(i)+" "+String.valueOf(i+(count*4)));
				CB[i+(count * 4)] = new JCheckBox("");
				CB[i+(count * 4)].setBounds(280+(i*25), 2+(count*20), 21, 21);
				CB[i+(count * 4)].setOpaque(false);
				//JOptionPane.showMessageDialog(null, binary.substring(i+(count * 4), (i+(count * 4))+1));
				if (binary.substring(i+(count * 4), (i+(count * 4))+1).equals("1"))
				{
					CB[i+(count * 4)].setSelected(true);
				}
				this.add(CB[i+(count * 4)]);
							
		}
		count++;
				
		}
		if (Cores >= 0) 		
		{
			//JOptionPane.showMessageDialog(null, String.valueOf(Cores));
			for (int i = 0; i < Cores; i++) 
			{
				CB[i+(count * 4)] = new JCheckBox("");
				CB[i+(count * 4)].setBounds(280+(i*25), 2+(count*20), 21, 21);
				CB[i+(count * 4)].setBackground(Color.WHITE);
				CB[i+(count * 4)].setOpaque(false);
				if (binary.substring(i+(count * 4), (i+(count * 4))+1).equals("1"))
				{
					CB[i+(count * 4)].setSelected(true);
				}
				this.add(CB[i+(count * 4)]);
			}	
			
		}
		ImageIcon iconRun = new ImageIcon(getClass().getClassLoader().getResource("Ressourcen/run.png"));
		lIconRun = new JLabel(iconRun);
		lIconRun.setBounds(230, 0, 35, 35);
		lIconRun.setOpaque(false);
		
		ImageIcon iconDel = new ImageIcon(getClass().getClassLoader().getResource("Ressourcen/delete2.png"));
		lIconDel = new JLabel(iconDel);
		lIconDel.setBounds(195, 5, 25, 25);
		lIconDel.setOpaque(false);
				
		this.add(lIconRun);
		this.add(lIconDel);
		this.repaint();
	}
	
	
	
}
