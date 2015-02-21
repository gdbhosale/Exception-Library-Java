import java.io.*;
import java.util.*;
import com.jscape.inet.smtp.*;
import com.jscape.inet.email.*;
import com.jscape.inet.mime.MimeException;
import com.jscape.inet.smtpssl.*;

class EmailAction extends Action
	{
	void doAction(Hashtable table, Object obj)
		{
		String to = (String)table.get("to");
		String from = (String)table.get("from");
		String subject = (String)table.get("subject");
		String msg = (String)table.get("message");
		
		System.out.println("\t_________________________________");
		System.out.println("\n\tSending email...");
		System.out.println("\tTo:      "+to);
		System.out.println("\tFrom:    "+from);
		System.out.println("\tsubject: "+subject);
		System.out.println("\tmessage: \n\t  "+msg);
		try	{
			SmtpExample example = new SmtpExample();
			//example.sendMessage(to, from, subject, msg);
			System.out.println("\n\tEmail Successfully send to Destination...");
			System.out.println("\t_________________________________");
			}
		catch(Exception e)
			{
			System.out.println("\tError:Sending Email Failed.");
			System.out.println("\nt_________________________________");
			}
		}
	}
class SmtpExample extends SmtpAdapter
	{
	SmtpSsl smtp = null;
	
	public void sendMessage(String to, String username, String subject, String body) throws Exception
		{
		String password = "hibernet";
		// create a new SmtpSsl instance connecting securely via port 465 using implicit SSL
		smtp = new SmtpSsl("smtp.gmail.com",465);
		smtp.connect();						// establish secure connection
		smtp.login(username,password);		// login using gmail account details
		EmailMessage message = new EmailMessage();// create new email message
		message.setTo(to);
		message.setFrom(username);
		message.setSubject(subject);
		message.setBody(body);
		smtp.send(message);					// send message
		smtp.disconnect(); 					// disconnect     	
		}
	}