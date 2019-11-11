package group.jpa.ogm.app.repository.invoice;

import java.rmi.RemoteException;

import group.jpa.ogm.app.entities.Invoice;
import group.jpa.ogm.app.repository.generics.GenericsDAO;

public interface InvoiceDAO extends GenericsDAO<Invoice> {
	Invoice getLastInvoiceByDate() throws RemoteException;
}
