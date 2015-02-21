import java.io.*;
import java.util.*;

abstract class Action
	{
	abstract void doAction(Hashtable table, Object obj);
	}

class ShutDownAction extends Action
	{
	int timeToShutDown = 0;
	void doAction(Hashtable table, Object obj)
		{
		timeToShutDown = Integer.parseInt((String)table.get("time"));
		System.out.println("_________________________________");
		System.out.println("\nShuting Down after "+(timeToShutDown/1000)+" Seconds...");
		System.out.println("_________________________________");
		try	{
			Thread.sleep(timeToShutDown);
			}
		catch(Exception e2)
			{
			e2.printStackTrace();
			}
		System.exit(0);
		}
	}