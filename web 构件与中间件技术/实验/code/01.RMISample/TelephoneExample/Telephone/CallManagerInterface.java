package Telephone;

public interface CallManagerInterface
	extends java.rmi.Remote
{
	public Database.DatabaseTableModel getCallHistory(String subscriber)
		throws java.rmi.RemoteException;
}