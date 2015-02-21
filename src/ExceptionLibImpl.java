import java.io.*;
import java.util.*;

class ExceptionLibImpl implements ExceptionLib
{
	static XMLParserClass parser;
	
	public synchronized void handleException(ProjectContext project_context)
	{
		
		String project = project_context.getProjectName();
		String module  = project_context.getModuleName();
		String exceptionName  = project_context.getExceptionName();
		Object obj  = project_context.getObject();
		
		parser = new XMLParserClass();				//Object of XML Parser Class
		Hashtable table = parser.getHashtable();	//HashTable for Library
		doActions(table, project+"."+module+"."+exceptionName, obj);
	}
	
	void doActions(Hashtable table, String exception, Object obj)//=================================================do Actions
	{
		//Does all actions given by exception
		
		Hashtable tableException,tableAction;
		tableException = (Hashtable)table.get(exception);
		
		if(tableException == null)
			{
			return;
			}
		Set keys       = tableException.keySet();
		Iterator iter1 = keys.iterator();
		
		while(iter1.hasNext())//iterating Actions
		{
			Object o   = iter1.next();
			String str = (String)o;
			if(str != "#text")
			{
				tableAction = (Hashtable) tableException.get( str );
				action(str, tableAction, obj);
			}
		}
	}

	void action(String act, Hashtable table, Object obj)//=============================================================action performing
	{
		try	{
			Action action = (Action)(Class.forName(act)).newInstance();
			action.doAction(table, obj);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}