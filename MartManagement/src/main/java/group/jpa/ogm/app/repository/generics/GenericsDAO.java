package group.jpa.ogm.app.repository.generics;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GenericsDAO<T> extends Remote {
	T findById(String id,Class<T> t) throws RemoteException;
    void save(T t) throws RemoteException;
    void remove(T t) throws RemoteException;
    void update(T t) throws RemoteException;
}
