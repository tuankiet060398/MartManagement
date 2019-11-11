package group.jpa.ogm.app.repository.account;

import java.rmi.RemoteException;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.bson.Document;
import javax.persistence.Query;

import com.google.gson.Gson;

import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;

public class AccountDAOImpl extends GenericsDAOImpl<Account> implements AccountDAO {

	Gson gson = new Gson();

	public AccountDAOImpl() throws RemoteException {
	}

	@SuppressWarnings("unchecked")
	public Account findByUserName(String userName) throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		List<Account> list = null;
		try {
			tr.begin();

			Document query1 = new Document(new Document("Username", userName));

			Gson gson = new Gson();

			Query query = entityManager.createNativeQuery(gson.toJson(query1), Account.class);

			list = (List<Account>) query.getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public Account findByEmployee(String id) {

		EntityTransaction tr = entityManager.getTransaction();
		List<Account> list = null;
		try {
			tr.begin();

			Document query1 = new Document(new Document("account_id", id));
			Gson gson = new Gson();
			Query query = entityManager.createNativeQuery(gson.toJson(query1), Account.class);
			list = (List<Account>) query.getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list.get(0);
	}

	public boolean deleteById(String id) throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();

		try {
			tr.begin();

			Document query1 = new Document(new Document("_id", id));

			Gson gson = new Gson();

			Query query = entityManager.createNativeQuery(gson.toJson(query1), Account.class);

			// System.out.println(query.getSingleResult());

			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public Account findPassOld(String mkc) {
		EntityTransaction tr = entityManager.getTransaction();
		List<Account> list = null;
		try {
			tr.begin();
			Document query1 = new Document(new Document("Password", mkc));
			Gson gson = new Gson();

			Query query = entityManager.createNativeQuery(gson.toJson(query1), Account.class);

			list = (List<Account>) query.getResultList();
			tr.commit();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list.get(0);
	}

	public boolean checkPassOld(String mkc, String mkm) {
		if (findPassOld(mkc).getPassword().equals(mkm))
			return true;
		return false;
	}

	public boolean changePass(Account ac, String mkm) {
		EntityTransaction tr = entityManager.getTransaction();
		int list = 0 ;
		try {
			tr.begin();
//			db.Accounts.updateOne({"_id":"8645822b-cfa7-42b1-92c5-92c3cddfba39"},
//			$set:{"Password":"2"},
//			$currentDate:{lastModified: true}})
//			db.Accounts.updateOne({Username:"kietnhanvien"},{$set:{"Password":"2"}})
			
			Document query1 = new Document();
			
			query1
			.append("_id", ac.getId())
			.append("$set", new Document().append("Password", mkm))
			;
			System.out.println(gson.toJson(query1));
			Query query = entityManager.createNativeQuery(gson.toJson(query1), Account.class);

			list = query.executeUpdate();
			tr.commit();

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Kq");
		System.out.println(list);
		return list > 0;
	}

	@Override
	public List<Account> findAll() throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		List<Account> list = null;
		try {
			tr.begin();

			Document doc = new Document();
			Gson gson = new Gson();

			Query q = entityManager.createNativeQuery(gson.toJson(doc), Account.class);

			list = q.getResultList();
			tr.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}