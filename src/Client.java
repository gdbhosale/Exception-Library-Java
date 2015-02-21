
public class Client
	{
	public static void main(String args[])
		{
		ExceptionLib lib = new ExceptionLibImpl();
		try	{
			throw new InvalidUserException();
			}
		catch(InvalidUserException e1)
			{
			ProjectContext pc = new ProjectContext("LIC", "createLICAccount", e1, new Object());
			lib.handleException(pc);
			}
			
		try	{
			throw new InsufficientBalanceException();
			}
		catch(InsufficientBalanceException e2)
			{
			ProjectContext pc = new ProjectContext("Bank" ,"showBalance", e2, new Object());
			lib.handleException(pc);
			}
		}
	}
class InvalidUserException extends Exception
	{
	
	}
class InsufficientBalanceException extends Exception
	{
	
	}