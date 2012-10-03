import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class IOData 
{
	private String AWName;
	private String AWPath;
	private int[] AWNumbCores;
	private int AWNumbCoresB;
	private File f;
	
	public IOData() 
	{
		f = new File("awconfig.cfg");
		if (f.exists()) 
		{
			//erstes element reinladen
		}
		else 
		{
			try
			{
				f.createNewFile();
			}
			catch (Exception e)
			{
				System.out.println("Datei konnte nicht erstellt werden!");
			}
			setAWName(null);
			setAWPath(null);
			AWNumbCores = new int[Runtime.getRuntime().availableProcessors()];
		}
	}
	
	public IOData(String name, String path, int[] cores) 
	{
		if (f == null)
			f = new File("awconfig.cfg");
		setAWName(new String(name));
		setAWPath(new String(path));
		AWNumbCores = new int[Runtime.getRuntime().availableProcessors()];
		for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++)
			AWNumbCores[i] = cores[i];
		if (f.exists() == false)
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		try
		{
			InsertItem(BuildDataString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getAWName() 
	{
		return AWName;
	}

	public void setAWName(String aWName) 
	{
		AWName = aWName;
	}

	public String getAWPath() 
	{
		return AWPath;
	}

	public void setAWPath(String aWPath) 
	{
		AWPath = aWPath;
	}
	
	public int[] getAWNumbCores() 
	{
		return AWNumbCores;
	}

	public void setAWNumbCores(int[] aWNumbCores) 
	{
		AWNumbCores = aWNumbCores;
	}

	public String BuildDataString()
	{
		String ret = new String();
		ret = AWName + "|" + AWPath + "|" + AWNumbCoresB;
		//for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++)
		//	ret += AWNumbCoresB[i];
		ret += '\n';
		return ret;
	}
	
	public void Load(DefaultListModel<JItemPanel> AppList)
	{
		if (f.exists()) 
		{
			List<String> Buf = new ArrayList<String>();
			String sBuf = null;
			
			int i = 0;
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(f));
				while((sBuf =br.readLine()) != null)
				{
					Buf.add(sBuf);
					i++;
				}
				br.close();
				
				for(int j = 0; j < i; j++)
				{
					sBuf =Buf.get(j);
					AWName = sBuf.substring(0, sBuf.indexOf('|'));
					AWPath = sBuf.substring(sBuf.indexOf('|')+1, sBuf.indexOf('|', sBuf.indexOf('|')+1));
					AWNumbCoresB = Integer.parseInt(sBuf.substring(AWName.length()+AWPath.length()+2));
					LItem Item = new LItem(AWPath, AWName, AWNumbCoresB);
					JItemPanel IPanel = new JItemPanel(Item);
					AppList.addElement(IPanel);
				}
				
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void Save(DefaultListModel<JItemPanel> AppList)
	{
		if (f.exists())
		{
			
			for (int i = 0; i < AppList.size(); i++) 
			{
				LItem Item = AppList.get(i).getItem();
				AppList.get(i).setNewCPU();
				AWName = Item.getName();
				AWPath = Item.getPath();
				AWNumbCoresB = Item.getNumbOfCores();
				try
				{
					InsertItem(BuildDataString());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public void InsertItem(String item) throws IOException
	{
		String test = null, s1 = null, s2 = null;
		char t = 'n';
		if (f.exists())
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((test = br.readLine()) != null)
			{
				s1 = item.substring(0, item.indexOf('|'));
				s2 = test.substring(0, test.indexOf('|'));
				JOptionPane.showMessageDialog(null, s1+'\n'+s2+'\n'+test+'\n'+item);
				if (s1.equals(s2))
				{	//Selber Anwedungsname
					if (!item.equals(test))
					{	//Gesamter Eintrag ist nicht identisch
						t = 'u';
					}
					else
					{	//Einträge absolut identisch
						t = 'x';
						break;
					}
				}
				else
				{	//Neuer Eintrag
					t = 'n';
				}
			}
			br.close();
			JOptionPane.showMessageDialog(null, t);
			switch (t)
			{
			case 'u'://update der Zeile
				updateItem(item, test);
				break;
			case 'n':
				Writer fw = new FileWriter(f, true);
				fw.write(item);
				fw.close();
				break;
			case 'x':
			default:
				break;
			}
		}
	}
	
	private void updateItem (String item, String Replace)
	{
		List<String> Buf = new ArrayList<String>();
		String sBuf = null;
		int i = 0;
		if (f.exists())
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(f));
				while((sBuf = new String(br.readLine())) != null)
				{
					Buf.add(sBuf);
					i++;
				}
				br.close();
				Buf.set(Buf.indexOf(Replace),item);  //anstatt  löschen updaten wir mal hier ^.^
				f.delete();
				f.createNewFile();
				Writer fw = new FileWriter(f, true);
				for(int j = 0; j <= i; j++)
				{
					fw.write(Buf.get(j));
				}
				fw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
