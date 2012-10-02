import java.io.*;
import java.util.jar.JarFile;

import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;

public class StartProcess 
{

	private int CPUcores;
	private String Path;
	
	public StartProcess(LItem App) 
	{
		setCPUcores(App.getNumbOfCores());
		//JOptionPane.showMessageDialog(null, "geht doch");
		setPath(App.getPath());
		try 
		{
			Start();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getCPUcores() 
	{
		return CPUcores;
	}

	public void setCPUcores(int cPUcores) 
	{
		CPUcores = cPUcores;
	}
	
	/*public void setCPUcores(int[] ChkBx) 
	{
		for (int i = 0; i < ChkBx.length; i++)
		{
			if (ChkBx[i] == 1)
				CPUcores += 1 + i;
		}
	}*/

	public String getPath() 
	{
		return Path;
	}

	public void setPath(String path) 
	{
		Path = path;
	}
	
	public void Start() throws IOException 
	{
		File pfad = new File("");
		
		InputStream resource = getClass().getResourceAsStream("Ressourcen/load.exe");
		FileOutputStream outStream = new FileOutputStream("load.exe");
		
		byte[] buffer = new byte[2048];
		int bytes;
		while ((bytes = resource.read(buffer)) != -1) 
		{
			outStream.write(buffer, 0, bytes);
		}
		outStream.close();
		resource.close();
		
		JOptionPane.showMessageDialog(null,pfad.getAbsolutePath()+"\\load.exe" + " \"" + Path + "\" " + CPUcores);
        Process p = Runtime.getRuntime().exec(pfad.getAbsolutePath()+"\\load.exe" + " \"" + Path + "\" " + CPUcores);
		//p.getInputStream();
		
	}

}
