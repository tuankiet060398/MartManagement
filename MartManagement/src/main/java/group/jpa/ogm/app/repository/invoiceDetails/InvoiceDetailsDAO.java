package group.jpa.ogm.app.repository.invoiceDetails;

import java.rmi.RemoteException;

import group.jpa.ogm.app.entities.InvoiceDetails;
import group.jpa.ogm.app.repository.generics.GenericsDAO;

public interface InvoiceDetailsDAO extends GenericsDAO<InvoiceDetails> {
	InvoiceDetails findByInvoiceId(String id) throws RemoteException;

}
