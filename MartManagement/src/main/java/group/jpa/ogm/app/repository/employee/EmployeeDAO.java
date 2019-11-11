package group.jpa.ogm.app.repository.employee;

import java.rmi.RemoteException;
import java.util.List;

import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.repository.generics.GenericsDAO;

public interface EmployeeDAO extends GenericsDAO<Employee> {
	Employee add(Employee e) throws RemoteException;
	Employee findByName(String name) throws RemoteException;
	Employee getEmp(String user) throws RemoteException;
	List<Employee> findAllEmp() throws RemoteException;
	List<Employee> findName(String name) throws RemoteException;
}
