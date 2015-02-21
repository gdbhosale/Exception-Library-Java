import java.sql.*;

public class DBClient
	{
	static Connection conn = null;
	static Statement st;
	public static void main(String args[])
		{
		ExceptionLib lib = new ExceptionLibImpl();
		try	{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			System.out.println("___________________________________________________________________________");
			System.out.println("\n\tTrying to Connect to Database...");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@127.0.0.1:1521:XE","System", "ggansaal");
			System.out.println("\n\tConnection successfully established...");
			System.out.println("___________________________________________________________________________");
			
			st=conn.createStatement();
			
			int n=st.executeUpdate("insert into STUDENT values (7,'BEEXTC',65)");
			System.out.println("\t"+n+" row(s) affected...\n");
			
			
			try	{
				Thread.sleep(5000);
				}
			catch(Exception e2)
				{
				e2.printStackTrace();
				}
			//conn.close();
			
			ResultSet rs=st.executeQuery("select * from STUDENT");
			while(rs.next())
				{
				int cid=rs.getInt("ID");
				String name=rs.getString("NAME");
				int strength=rs.getInt("MARKS");
				System.out.println("\t"+cid+"  "+name+"  "+strength);
				}
			}
		catch(SQLException e3)
			{
			System.out.print("\tSQL Exception Occurs...");
			//System.out.print(e3);
			System.out.println("\n\tfailed to connect to database...");
			ProjectContext pc = new ProjectContext("Bank" ,"showBalance", e3, conn);
			lib.handleException(pc);
			}
		/*
		finally
			{
			try	{
				if(conn != null && st != null)
				if(! conn.isClosed())
					{
					st.close();
					conn.close();
					System.out.println("\n\tConnection Closed...");
					}
				}
			catch(Exception e)
				{
				e.printStackTrace();
				}
			System.out.println("___________________________________________");
			}
		*/
		}
	}