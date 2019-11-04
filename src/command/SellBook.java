package command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import inventory.Inventory;

@SuppressWarnings("serial")
public class SellBook extends Command implements java.io.Serializable {
	private String bookName;
	private int decreaseQuantity;
	private String fileName = "item.txt";
	
	public SellBook(String bookName,int decreaseQuantity){
	this.bookName = bookName;
	this.decreaseQuantity = decreaseQuantity;
	}
	
	@Override
	public void execute(Inventory newInvent) {
		try {
			 newInvent.sellBook(bookName,decreaseQuantity);
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
