import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class JLoaderList extends JList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1721143636111997507L;

	public JLoaderList() 
	{
		setCellRenderer(new CellRenderer());
		addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(final MouseEvent e) 
			{
				int index = locationToIndex(e.getPoint());
				if (index != -1) 
				{
					JItemPanel Item = (JItemPanel) getModel().getElementAt(index);
					
					Component Auswahl = Item.getItem(e.getPoint(),index);
					String AuswahlName= Auswahl.getClass().getSimpleName();
					switch (AuswahlName) 
					{
					case "JCheckBox":
						JCheckBox cb = (JCheckBox) Auswahl;
						if (cb.isSelected()) 
						{
							cb.setSelected(false);
						}
						else cb.setSelected(true);
						break;
					case "JLabel":
						if (Auswahl.getX()>200 && Auswahl.getWidth()<100)
						{
							Item.setNewCPU();
							StartProcess Prozess = new StartProcess(Item.getItem());
						}
						break;

					default:
						if (e.getClickCount() == 2)
						{
							Item.setNewCPU();
							StartProcess Prozess = new StartProcess(Item.getItem());
						}
						break;
					}
					
					//JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
					//checkbox.setSelected(!checkbox.isSelected());
										
					repaint();
					
				}
			}
		});
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	protected class CellRenderer implements ListCellRenderer 
	{
		public Component getListCellRendererComponent(JList list, Object value,	int index, boolean isSelected, boolean cellHasFocus) 
		{
			JItemPanel Item = (JItemPanel) value;
			if (index % 2==1)
			{
				Item.setBackground(new Color(74, 74, 74));
			} else
			{
				Item.setBackground(new Color(104, 104, 104));
			}
			
			if (isSelected) 
			{
				 
				//Item.setForeground(UIManager.getColor("List.selectionForeground"));
				Item.setBackground(new Color(140, 154, 170));
			} 
			
				
			return Item;
			
		}
	}

}