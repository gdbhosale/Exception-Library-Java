import java.sql.*;

public class ProjectContext
	{
	private String projectName;
	private String moduleName;
	private Exception exce;
	private Object obj;
	
	public ProjectContext(String projectName, String moduleName, Exception exce, Object obj)
		{
		this.projectName = projectName;
		this.moduleName = moduleName;
		this.exce = exce;
		this.obj = obj;
		}
	public String getProjectName()
		{
		return this.projectName;
		}
	public String getModuleName()
		{
		return this.moduleName;
		}
	public String getExceptionName()
		{
		String exception = exce.toString();
		String exceptionName1 = exception.substring(0,exception.lastIndexOf("Exception")+9);
		String exceptionName = exception.substring(exceptionName1.lastIndexOf(".")+1, exceptionName1.length());
		
		if(exceptionName.equals("SQLException"))
			{
			exceptionName = exceptionName.concat("."+((SQLException)exce).getErrorCode());
			}
		System.out.println("\n\t******---"+exceptionName+"---*****");
		return exceptionName;
		}
	public Object getObject()
		{
		return this.obj;
		}
	}