package group.jpa.ogm.app.repository.category;

import java.rmi.RemoteException;
import java.util.List;

import group.jpa.ogm.app.entities.Category;
import group.jpa.ogm.app.repository.generics.GenericsDAO;

public interface CategoryDAO extends GenericsDAO<Category> {
	List<Category> findAll() throws RemoteException;
	Category findbyName(String name) throws RemoteException;
}
