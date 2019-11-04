package command;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import inventory.Inventory;
import serial.Book;
@SuppressWarnings("serial")
public class AddBookCom extends Command implements java.io.Serializable {

	private Book book;
	private String fileName = "item.txt";

	////////constructor////////////
	public AddBookCom(Book newBook){
	this.book = newBook; 
	}
	//////////Save state to file///////////
	@Override
	public void execute(Inventory newInvent) {
		newInvent.addBook(book);
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream(fileName,true);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

}
