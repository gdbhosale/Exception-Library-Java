import java.io.*;
import java.util.*;
import org.apache.log4j.*;
import org.apache.log4j.xml.DOMConfigurator;

class LogAction extends Action
	{
	String fileName;
	FileAppender appender = null;
	
	void doAction(Hashtable table, Object obj)
		{
		fileName = (String)table.get("file");
		
		System.out.println("\t_________________________________");
		System.out.println("\n\tPrinting Log...");
		System.out.println("\tFile: "+fileName);
		System.out.println("\t_________________________________");
		Logger logger = Logger.getLogger(LogAction.class.getName());
		SimpleLayout layout = new SimpleLayout();
		try	{
			appender = new FileAppender(layout, fileName, false);
			}
		catch(Exception e)
			{
			e.printStackTrace();
			}
		logger.addAppender(appender);
		logger.setLevel((Level) Level.DEBUG);
		
		StackTraceElement element[] = (new NullPointerException()).getStackTrace();
		
		logger.debug("Here is some DEBUG");
		for(int i=0; i<element.length; i++)
			logger.debug(element[i].toString());
		logger.debug("*********************");
		}
	}