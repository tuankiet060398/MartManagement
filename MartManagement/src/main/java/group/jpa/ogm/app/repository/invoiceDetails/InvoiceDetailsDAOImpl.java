package group.jpa.ogm.app.repository.invoiceDetails;

import java.rmi.RemoteException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.bson.Document;

import com.google.gson.Gson;

import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.entities.InvoiceDetails;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;

public class InvoiceDetailsDAOImpl extends GenericsDAOImpl<InvoiceDetails> implements InvoiceDetailsDAO {

	public InvoiceDetailsDAOImpl() throws RemoteException {
	}

	@Override
	public InvoiceDetails findByInvoiceId(String id) throws RemoteException {
		EntityTransaction tr = entityManager.getTransaction();
		InvoiceDetails inD = null;
		try {
			tr.begin();
			Document query1 = new Document(new Document("Invoice_id", id));
			Gson gson =new Gson();
			Query query = entityManager.createNativeQuery(gson.toJson(query1), InvoiceDetails.class);
			inD = (InvoiceDetails) query.getSingleResult();
			tr.commit();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return inD;
	}
	
	

}
