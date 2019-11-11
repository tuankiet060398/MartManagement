package group.jpa.ogm.app.repository.account;

import java.rmi.RemoteException;
import java.util.List;

import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.repository.generics.GenericsDAO;

public interface AccountDAO extends GenericsDAO<Account> {
	Account findByUserName(String userName) throws RemoteException;

	List<Account> findAll() throws RemoteException;
	boolean deleteById(String id) throws RemoteException;

	Account findByEmployee(String id) throws RemoteException;
	Account findPassOld(String mkc) throws RemoteException ;
	boolean checkPassOld(String mkc,String mkm) throws RemoteException;
	boolean changePass(Account ac,String mkm) throws RemoteException;

}
