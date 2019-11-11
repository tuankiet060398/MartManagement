package group.jpa.ogm.app.repository.invoice;

import java.rmi.RemoteException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import group.jpa.ogm.app.entities.Good;
import group.jpa.ogm.app.entities.Invoice;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;

public class InvoiceDAOImpl extends GenericsDAOImpl<Invoice> implements InvoiceDAO {

	public InvoiceDAOImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Invoice getLastInvoiceByDate() throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		tr.begin();

		String query = "db.Invoice.find({}).sort({InvoiceDate:-1}).limit(1)";
		Query q = entityManager.createNativeQuery(query, Invoice.class);

		@SuppressWarnings("unchecked")
		List<Invoice> list = q.getResultList();
		tr.commit();

		return list.get(0);
	}

}
