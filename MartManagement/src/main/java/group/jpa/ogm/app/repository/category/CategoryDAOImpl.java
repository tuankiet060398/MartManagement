package group.jpa.ogm.app.repository.category;

import java.rmi.RemoteException;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.bson.Document;

import com.google.gson.Gson;

import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.entities.Category;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;

public class CategoryDAOImpl extends GenericsDAOImpl<Category> implements CategoryDAO {

	public CategoryDAOImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAll() throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		List<Category> list = null;
		try {
			tr.begin();

		//	Gson gson = new Gson();
			String query = "db.Categories.find({})";
			Query q = entityManager.createNativeQuery(query, Category.class);
			list = q.getResultList();

			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public Category findbyName(String name) throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		Category category = null;
		try {
			tr.begin();

			Gson gson = new Gson();
			Document doc = new Document("Name", name);
			Query q = entityManager.createNativeQuery(gson.toJson(doc), Category.class);
			category = (Category) q.getSingleResult();

			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return category;
	}

}
