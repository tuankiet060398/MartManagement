package group.jpa.ogm.app.repository.generics;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import group.jpa.ogm.app.config.SingletonPersistenceConfig;

public class GenericsDAOImpl<T> extends UnicastRemoteObject implements GenericsDAO<T> {
	public GenericsDAOImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = -3127853915912269457L;
	protected EntityManager entityManager = SingletonPersistenceConfig.getInstance().getEntityManager();

	public T findById(String id, Class<T> t) {
		return entityManager.find(t, id);
	}

	public void save(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(t);
		transaction.commit();
	}

	public void remove(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));
		transaction.commit();
	}

	public void update(T t) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(t);
		transaction.commit();
	}

}