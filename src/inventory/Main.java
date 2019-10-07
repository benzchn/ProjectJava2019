package inventory;

public class Main {

	public static void main(String[] args) throws Exception {
	    
		System.out.println("\t\t******** Welcome ********");
		Inventory inventory = new Inventory();
	    inventory.connectToDB();
		////////Show Book/////////////
	    inventory.showBook();
		
		//inventory.saveToMemento(inventory);
		//Inventory i = inventory.getMementoRestore();
		//i.showBook();
		
		////////Add New Book///////////
		//inventory.addBook("Python 4.0", 700, 5);
		
		////////Change price//////////
		//inventory.changePriceBook("Python 4.0", 7000);
		
		////////increase quantity//////////
		//inventory.increaseQuantityBook("Python 4.0", 40);
		
		////////search by id//////////
		//inventory.searchById(11);
		
		////////search by name//////////
		//inventory.searchByName("gg");
		System.out.println("\n---------------------Current Inventory------------------------------");
	    //inventory.saveToMemento(inventory);
	    //Inventory i = inventory.getMementoRestore();
	    //i.showBook();
		//inventory.showBook();
		
		//inventory.Save();
		//inventory.saveToMemento(inventory);
		//inventory.saveInventorytoFile();
		//inventory.getInventoryFromFile();
		//inventory.getMementoRestore();
		inventory.close();
}
}
	
