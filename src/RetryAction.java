import java.io.*;
import java.util.*;
import java.sql.*;


class RetryAction extends Action
	{
	int frequency = 0;
	public int freq;
	public int interval;
	public String query;
	public String username;
	public String password;
	
	void doAction(Hashtable table, Object obj)
		{
		freq     = Integer.parseInt((String)table.get("freq"));
		interval = Integer.parseInt((String)table.get("interval"));
		query    = (String)table.get("query");
		username = (String)table.get("username");
		password = (String)table.get("password");
		System.out.println("\t_________________________________");
		System.out.println("\n\tRetry...");
		System.out.println("\tfreq:     "+freq);
		System.out.println("\tinterval: "+interval);
		System.out.println("\tQuery:    "+query);
		System.out.println("\tusername: "+username);
		System.out.println("\tpassword: "+password);
		
		/*
		if(obj instanceof Connection)
			System.out.print("True");
		else
			{
			System.out.print("false");
			return;
			}
		*/	
		Connection conn = (Connection)obj;
		
		try	{
			//if(conn.isClosed())
			//	{
				System.out.println("\n\tConnection is not Available...");
				
				while(frequency < freq)
					{
					try	{
						System.out.println("\n\tWaiting for a while to reconnect...");
						Thread.sleep(interval);
						}
					catch(Exception e2)
						{
						e2.printStackTrace();
						}
					
					
					try	{
						System.out.println("\n\tTrying to Reconnect...");
						conn = DriverManager.getConnection (query, username, password);
						System.out.println("\n\tSuccessfully Connected...");
						System.out.println("\t_________________________________");
						break;
						}
					catch(Exception e)
						{
						//e.printStackTrace();
						System.out.println("\n\tFailed to Connect..."+frequency);
						LogAction log = new LogAction();
						log.doAction(table,new Object());
						if(frequency == (freq-1))
							{
							ShutDownAction shutDown = new ShutDownAction();
							shutDown.doAction(table,new Object());
							}
						frequency++;
						}
					}
			//	}
			//else
			//	{
			//	System.out.println("\nCouldnt able to understand reason of exception...");
			//	}
			}
		catch(Exception e)
			{
			e.printStackTrace();
			}
		}
	}