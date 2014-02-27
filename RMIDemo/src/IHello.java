import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IHello extends Remote {
	String SayHello() throws RemoteException;
	String SayName(String name) throws RemoteException;
}
