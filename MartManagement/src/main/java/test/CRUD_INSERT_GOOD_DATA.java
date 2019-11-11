package test;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import group.jpa.ogm.app.entities.Category;
import group.jpa.ogm.app.entities.Good;
import group.jpa.ogm.app.repository.category.CategoryDAO;
import group.jpa.ogm.app.repository.category.CategoryDAOImpl;
import group.jpa.ogm.app.repository.goods.GoodDAO;
import group.jpa.ogm.app.repository.goods.GoodDAOImpl;

public class CRUD_INSERT_GOOD_DATA {

	private CategoryDAO categoryService = new CategoryDAOImpl();
	private GoodDAO goodService = new GoodDAOImpl();

	public CRUD_INSERT_GOOD_DATA() throws RemoteException {
		super();
	}
	@Test
	public void InsertCategories() throws RemoteException {
		System.out.println("Insert Categories");
		List<String> categoryNames = Arrays.asList(
				"Carbonated Beverages",
				"Milk",
				"Fresh Bread & Rolls",
				"Salty Snacks",
				"Natural Cheese",
				"Wine",
				"Cigarettes",
				"Soap");
		for(String name:categoryNames) {
			Category newCategory = constructCategory(name);
			categoryService.save(newCategory);
		}
	}

	@Test
	public void InsertGoods() throws RemoteException, ParseException {
		System.out.println("Insert goods ");
		//Add Snacks
		Category c1 = categoryService.findbyName("Salty Snacks");
		categoryService.save(c1);
		Good g1_1 = constructGood(c1,"17/10/2019","Lays", 1.99, 100);
		Good g1_2 = constructGood(c1,"22/9/2019","Pringles",3.37, 100);
		Good g1_3 = constructGood(c1,"15/2/2019","Uncle Chips",5.96, 200);
		Good g1_4 = constructGood(c1,"16/1/2019","Haldiram’sTakatak",11.92, 2000);
		Good g1_5 = constructGood(c1,"15/10/2019","Parle Fulltoss",32.37, 200);
		Good g1_6 = constructGood(c1,"15/11/2019","Cheetos",3.37, 20000);
		Good g1_7 = constructGood(c1,"15/10/2019","Hostess Potato Chips",6.37, 200);
		Good g1_8 = constructGood(c1,"25/12/2019","Cheez Doodles",23.37, 200);
		//Add Cigarettes
		Category c2 = categoryService.findbyName("Cigarettes");
		Good g2_1 = constructGood(c2,"15/10/2019","Karelia",3.70, 300);
		Good g2_2 = constructGood(c2,"15/10/2019","Kent",6.60, 200);
		Good g2_3 = constructGood(c2,"15/10/2019","Marlboro",3.90, 1200);
		Good g2_4 = constructGood(c2,"15/10/2019","Nat Sherman",10, 1000);
		Good g2_5 = constructGood(c2,"23/10/2019","Davidoff",4.80, 3000);
		//Add Milks
		Category c3 = categoryService.findbyName("Milk");
		Good g3_1 = constructGood(c3,"5/12/2019","Dairyland",4.80, 4000);
		Good g3_2 = constructGood(c3,"22/12/2019","Amul",4.80, 1000);
		Good g3_3 = constructGood(c3,"15/10/2019","Mayfield Dairy",4.80, 500);
		Good g3_4 = constructGood(c3,"01/07/2019","Trader Joe's",4.80, 300);
		Good g3_5 = constructGood(c3,"03/11/2019","Sraus",4.87, 200);
		//Add Fresh Bread & Rolls
		Category c4 = categoryService.findbyName("Fresh Bread & Rolls");
		Good g4_1 = constructGood(c4,"5/12/2019","FULLY BAKED CLASSIC BRIOCHE BUN SLICED",4.80, 2000);
		Good g4_2 = constructGood(c4,"15/12/2019","ASSORTED CHEVERNY ROLLS (FRENCH WHEAT ONION DILL)",6.88, 3000);
		Good g4_3 = constructGood(c4,"03/12/2019","BAGUETTE BREAD DOUGH",3.80, 170);
		Good g4_4 = constructGood(c4,"13/03/2019","ASSORTED PARBAKED ROLLS",5.80, 370);
		Good g4_5 = constructGood(c4,"23/10/2019","BAKED BUN BRIOCHE",1.81, 1000);
		Good g4_6 = constructGood(c4,"05/11/2019","BOLILLO ROLL DOUGH",3.83, 300);
		Good g4_7 = constructGood(c4,"15/12/2019","BORDEAUX ROLL",2.85, 500);
		Good g4_8 = constructGood(c4,"13/12/2019","BRIOCHE DINNER ROLL FULLY BAKED",2.93, 200);
		Good g4_9 = constructGood(c4,"23/02/2019","BUTTER EGG DINNER ROLL DOUGH",3.18, 100);
		Good g4_10 = constructGood(c4,"03/12/2019","CIABATTA STYLE LOAF",2.87, 300);
		List<Good> listGoods = Arrays.asList(g1_1,g1_2,g1_3,g1_4,g1_5,g1_6,g1_7,g1_8,
				g2_1,g2_2,g2_3,g2_4,g2_5,
				g3_1,g3_2,g3_3,g3_4,g3_5,
				g4_1,g4_2,g4_3,g4_4,g4_5,g4_6,g4_7,g4_8,g4_9,g4_10);
		for(Good good:listGoods) {
			goodService.save(good);
		}
	}
	

	private Good constructGood(Category category,String sDate, String name, double price, int quantity) throws ParseException {
		Good newGood = new Good();
		Date newEnterDate = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
		newGood.setCategory(category);
		newGood.setEnterDate(newEnterDate);
		newGood.setName(name);
		newGood.setPrice(price);
		newGood.setQuantity(quantity);
		return newGood;

	}

	private Category constructCategory(String name) {
		Category newCategory = new Category();
		newCategory.setName(name);
		return newCategory;
	}
}
