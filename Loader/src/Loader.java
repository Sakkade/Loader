import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Loader 
{
	IOData FileWork;
	private JFrame frame;

	/*public static boolean isAdmin() {
	    String groups[] = (new com.sun.security.auth.module.NTSystem()).getGroupIDs();
	    for (String group : groups) {
	        if (group.equals("S-1-5-32-544"))
	            return true;
	    }
	    return false;
	}/*
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loader window = new Loader();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Loader() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() 
	{
		frame = new JFrame("Loader Test");
		final DefaultListModel<JItemPanel> ApplicationList = new DefaultListModel<JItemPanel>();
		
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				FileWork.Save(ApplicationList);
			}
		});
		frame.setBounds(100, 100, 450, 400);
		frame.setMinimumSize(new Dimension(450, 400));
		frame.setPreferredSize(new Dimension(450, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		//final ArrayList<LItem> LItemList = new ArrayList<LItem>();
		//final ArrayList<JItemPanel> LPanelList = new ArrayList<JItemPanel>();
		
				
		
		LItem Item = new LItem("P:\\GoldWave\\GoldWave.exe", "Goldwave");
		//LItemList.add(Item);
		JItemPanel IPanel = new JItemPanel(Item);
		//LPanelList.add(IPanel);
		ApplicationList.addElement(IPanel);
		
		FileWork = new IOData();
		FileWork.Load(ApplicationList);
		
		JButton btnNeuesItem = new JButton("Neues Item");
		
		frame.getContentPane().add(btnNeuesItem);
		btnNeuesItem.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				JFileChooser FD = new JFileChooser();
				FD.setDialogTitle("Anwendung wählen");
				FD.setFileFilter(new FileFilter() 
				{
					@Override
					public String getDescription() 
					{
						return "*.exe, *.com, *.bat, *.cmd, *,pif" ;
					}
					
					@Override
					public boolean accept(File f) 
					{
						return f.isDirectory() || f.getName().matches(".*\\.(exe|com|bat|cmd|pif)");
					}
				});
				FD.setVisible(true);
				FD.showOpenDialog(null);
				//JOptionPane.showMessageDialog(null, FD.getCurrentDirectory().toString()+" "+FD.getSelectedFile().toString());
				
				
				LItem Item = new LItem(FD.getCurrentDirectory().toString(), FD.getSelectedFile().toString());
				//LItemList.add(Item);
				JItemPanel IPanel = new JItemPanel(Item);
				//LPanelList.add(IPanel);
				ApplicationList.addElement(IPanel);
			}
		});
		
		btnNeuesItem.setBounds(21, 11, 89, 23);
		
		
		// Checkboxliste
		
		JLoaderList list = new JLoaderList();
		list.setModel(ApplicationList);
		list.setBounds(10, 35, 300, 200);
		list.ensureIndexIsVisible(2);
		

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		scrollPane.setBounds(20, 50, 400, 300);
		//frame.getContentPane().add(list_1);
		frame.getContentPane().add(scrollPane);
		
		
		//JOptionPane.showMessageDialog(pApplications, TestItem.getNumbOfCores());
	}
}
