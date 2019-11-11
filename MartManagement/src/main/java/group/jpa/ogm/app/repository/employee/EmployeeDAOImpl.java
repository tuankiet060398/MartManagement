package group.jpa.ogm.app.repository.employee;

import java.rmi.RemoteException;
import java.util.List;
import org.bson.Document;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;



import com.google.gson.Gson;

import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.repository.account.AccountDAO;
import group.jpa.ogm.app.repository.account.AccountDAOImpl;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;


public class EmployeeDAOImpl extends GenericsDAOImpl<Employee> implements EmployeeDAO {
	public EmployeeDAOImpl() throws RemoteException {
	}

	public Employee findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		String query = "db.Employee.find({'FullName':'{p1}'})";
		query = query.replace("{p1}", name);
		Query q = entityManager.createNativeQuery(query, Employee.class);
		System.out.println("Size : " + q.getResultList().size());
		@SuppressWarnings("unchecked")
		List<Employee> listEm = q.getResultList();
		tr.commit();
		return listEm.get(0);
	}
	public List<Employee> findName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();
		String query = "db.Employee.find({'FullName':'{p1}'})";
		query = query.replace("{p1}", name);
		Query q = entityManager.createNativeQuery(query, Employee.class);
		System.out.println("Size : " + q.getResultList().size());
		@SuppressWarnings("unchecked")
		List<Employee> listEm = q.getResultList();
		tr.commit();
		return listEm;
	}
	public Employee add(Employee e) throws RemoteException {

		EntityTransaction tr = entityManager.getTransaction();
		try {
			tr.begin();

			entityManager.persist(e);

			tr.commit();
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return e;
	}
	@SuppressWarnings("unchecked")
	public Employee getEmp(String userId) throws RemoteException{
		EntityTransaction tr = entityManager.getTransaction();
		List<Employee> list = null;
		try {
			tr.begin();
			Document query1=new Document(new Document("account_id", userId));
			Gson gson =new Gson();
			Query query = entityManager.createNativeQuery(gson.toJson(query1), Employee.class);
			list = (List<Employee>) query.getResultList();
			tr.commit();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return (Employee) list.get(0);
	}

	public List<Employee> findAllEmp() throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		List<Employee> list = null;
		try {
			tr.begin();

			Document doc = new Document();
			Gson gson = new Gson();

			Query q = entityManager.createNativeQuery(gson.toJson(doc), Employee.class);

			list = q.getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
