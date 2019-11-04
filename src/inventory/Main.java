package inventory;
import serial.Book;

public class Main {

	public static void main(String[] args){
	    
		InventoryStockDecorator stock = new InventoryStockDecorator();
		InventoryStockDecorator stockSave = new InventoryStockDecorator();
		int price,quantity;
		
		/////Add
		System.out.println("-----------Add book----------");
		stock.addBook(new Book("Java",250,20));
		stock.addBook(new Book("data",24450,25));
		stock.addBook(new Book("python",500,21));
		
		stock.addBook(new Book("Java",250,22));
		stock.addBook(new Book("Java",250,22));
		price = stock.findPriceByID(1);
		System.out.println(stock.getInventory().getBookArray().get(0).getName() + " price = " + price);
		quantity = stock.findQuantityByID(1);
		System.out.println(stock.getInventory().getBookArray().get(0).getName() + " quantity = " + quantity);
		
		////Change price
		System.out.println("-----------Change Price----------");
		stock.changePrice("Java", 300);
		price = stock.findPriceByID(1);
		System.out.println(stock.getInventory().getBookArray().get(0).getName() + " newprice = " + price);
		
		///Sell
//		System.out.println("-----------Sell----------");
//		stock.sellBook("Java",5);
//		quantity = stock.findQuantityByID(1);
//		System.out.println(stock.getInventory().getBookArray().get(0).getName() + " amount = " + quantity);
		
		////copy /// book will increase quantity
//		System.out.println("-----------Copy----------");
//		stock.CopyBook("Java", 20);
//		quantity = stock.findQuantityByID(1);
//		System.out.println(stock.getInventory().getBookArray().get(0).getName() + " amount = " + quantity);
		stock.saveState();
		
		/////save state////
//		System.out.println("-----------State Restore----------");
//		
		stockSave.getState();
		quantity = stockSave.findQuantityByID(1);
		System.out.println(stockSave.getInventory().getBookArray().get(0).getName() + " amount = " + quantity);
		price = stockSave.findPriceByID(1);
		System.out.println(stockSave.getInventory().getBookArray().get(0).getName() + " price = " + price);
}
}


	
