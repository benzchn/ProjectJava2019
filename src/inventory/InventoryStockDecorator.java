package inventory;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import command.AddBookCom;
import command.ChangePrice;
import command.Command;
import command.CopyCom;
import command.SellBook;
import memento.CareTaker;
import memento.Memento;
import serial.Book;

public class InventoryStockDecorator implements InventoryInterface {

	
	private Inventory inventory = new Inventory();
	private FileInputStream fileIn ;
	private String CommandFileName = "item.txt";
	private ArrayList<Command> commandCollection = new ArrayList<Command>();
	private CareTaker careTaker = new CareTaker();
	private Memento memento = new Memento();
	
///////////// GET SET inventory////////////////
	public Inventory getInventory() {
		return this.inventory;
	}
	public void setInvent(Inventory invent) {
		this.inventory = invent;
	}
	public void addBook(Book book){
		AddBookCom addbook= new AddBookCom(book);
		addbook.execute(inventory);
	}
	public void CopyBook(String bookName, Integer numberOfCopy){
		CopyCom addCopy = new CopyCom(bookName, numberOfCopy);
		addCopy.execute(inventory);
	}
	public void changePrice(String bookName, Integer newPrice){
		ChangePrice changePrice = new ChangePrice(bookName, newPrice);
		changePrice.execute(inventory);
	}
	public void sellBook(String bookName,int decreaseQuantity){
		SellBook sellbook = new SellBook(bookName,decreaseQuantity);
		sellbook.execute(inventory);
	}
	public Integer findPriceByName(String bookName){
		return inventory.findPriceByName(bookName);
	}
	public Integer findPriceByID(Integer bookID){
		return inventory.findPriceByID(bookID);
	}
	public Integer findQuantityByName(String bookName){
		return inventory.findQuantityByName(bookName);
	}
	public Integer findQuantityByID(Integer bookID){
		return inventory.findQuantityByID(bookID);
	}
	private void replyCommands(Inventory invent){
		try {
				  fileIn = new FileInputStream(CommandFileName);
				  while (true) {
					ObjectInputStream input = new ObjectInputStream(fileIn);
				    commandCollection.add((Command) input.readObject());
				  }  
				}catch (EOFException e) {
					
					try{	
						fileIn.close();
					}catch(IOException i)
					{
						i.printStackTrace();
					}
				}catch(IOException i)
				{
					i.printStackTrace();
				}catch(ClassNotFoundException c)
				{
					System.out.println("class not found");
					c.printStackTrace();
				}
			
			for(Command command : commandCollection){
		         command.execute(invent);
			}
		}

	@Override
	public void saveState() {
		memento.saveState(inventory.getBookArray());
		careTaker.serialzeMemento(memento);
		File file = new File(CommandFileName);
		file.delete();
		try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	public void getState() {
	memento = careTaker.deserialseMemento();
	inventory.setBookArray(memento.getState());
	this.replyCommands(inventory);
	
	}

}