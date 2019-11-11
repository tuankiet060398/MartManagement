package group.jpa.ogm.app.repository.customer;



import group.jpa.ogm.app.entities.Customer;
import group.jpa.ogm.app.repository.generics.GenericsDAOImpl;

import javax.persistence.EntityManager;
import java.rmi.RemoteException;


public class CustomerDAOImpl extends GenericsDAOImpl<Customer> implements CustomerDAO {
    public CustomerDAOImpl() throws RemoteException {
 
    }
}
