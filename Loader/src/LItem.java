public class LItem 
{
	private int NumbOfCores;
	private String Path;
	private String Name;

	
	public int getNumbOfCores() 
	{
		return NumbOfCores;
	}
	
	public void setNumbOfCores(int i)
	{	
		NumbOfCores = i;
	}
	
	public String getPath() 
	{
		return Path;
	}
	
	public void setPath(String path) 
	{
		Path = path;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public LItem(String iPath, String Name) 
	{				
		setPath(iPath);
		setName(Name);
		setNumbOfCores(0);
				
	}
	
	public LItem(String iPath, String Name, int Cores) 
	{				
		setPath(iPath);
		setName(Name);
		setNumbOfCores(Cores);
				
	}
}