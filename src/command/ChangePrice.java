package command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import inventory.Inventory;

@SuppressWarnings("serial")
public class ChangePrice extends Command implements java.io.Serializable {

	private String bookName;
	private Integer numberOfCopy;
	private String fileName = "item.txt";
	
	public ChangePrice(String bookName, Integer newNumberOfCopy){
	this.bookName = bookName; 
	this.numberOfCopy = newNumberOfCopy;
	}
	@Override
	public void execute(Inventory newInvent) {
		try {
		    newInvent.changePrice(bookName,numberOfCopy);
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
