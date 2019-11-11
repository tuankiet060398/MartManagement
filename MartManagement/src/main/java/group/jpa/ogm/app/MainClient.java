package group.jpa.ogm.app;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import group.jpa.ogm.app.controller.client.ClientController;
import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.entities.Category;
import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.entities.Good;
import group.jpa.ogm.app.entities.Invoice;
import group.jpa.ogm.app.entities.InvoiceDetails;

public class MainClient {
	public static void main(String[] args) throws RemoteException, NotBoundException, ParseException {

		ClientController callService = new ClientController("192.168.31.22", 9999);
		
		/*
		 * System.out.println("tesst: " + call.getInvoiceDAO().getLastInvoiceByDate());
		 * System.out.println("find by id: "); InvoiceDetails inD =
		 * call.getInvoiceDetailsDAO().findByInvoiceId(call.getInvoiceDAO().
		 * getLastInvoiceByDate().getId());
		 * 
		 * 
		 */
		

		
		Account ac = new Account();
		ac.setUsername("kiet");
		ac.setType(2);
		ac.setPassword("123");
		ac.setStartingDate(new Date());
		ac.setStatus("Yes");


		Employee em = new Employee();
		em.setFullName("Ronaldo Nguyen");
		em.setGender("Nam");
		em.setAddress("NVB");
		em.setBirthdate(new Date());
		em.setAccount(ac);
		callService.getEmployeeDAO().save(em);
////
		Account ac1 = new Account();
		ac1.setUsername("luan");
		ac1.setType(1);
		ac1.setPassword("123");
		ac1.setStartingDate(new Date());
		ac1.setStatus("Yes");


		Employee em1 = new Employee();
		em1.setFullName("Neymar");
		em1.setGender("Nữ");
		em1.setAddress("NVB");
		em1.setBirthdate(new Date());
		em1.setAccount(ac1);
		callService.getEmployeeDAO().save(em1);
////
		Account ac2 = new Account();
		ac2.setUsername("huy");
		ac2.setType(1);
		ac2.setPassword("123");
		ac2.setStartingDate(new Date());
		ac2.setStatus("Yes");


		Employee em2 = new Employee();
		em2.setFullName("pepe");
		em2.setGender("Nữ");
		em2.setAddress("NVB");
		em2.setBirthdate(new Date());
		em2.setAccount(ac2);
		callService.getEmployeeDAO().save(em2);
///		
		Account ac3 = new Account();
		ac3.setUsername("kietdeptrai");
		ac3.setType(2);
		ac3.setPassword("123");
		ac3.setStartingDate(new Date());
		ac3.setStatus("Yes");


		Employee em3 = new Employee();
		em3.setFullName("Ngô Tuấn Kiệt");
		em3.setGender("Nam");
		em3.setAddress("NVB");
		em3.setBirthdate(new Date());
		em3.setAccount(ac3);
		callService.getEmployeeDAO().save(em3);
		
		System.out.println("Done!");

	}

}
