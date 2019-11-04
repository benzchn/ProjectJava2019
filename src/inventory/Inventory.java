package inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import memento.Memento;
import serial.Book;

public class Inventory implements InventoryInterface {
    
	private ArrayList<serial.Book> BookArray = new ArrayList<Book>();
	private Memento memento = new Memento();
	private InventoryStockDecorator invent ;
	private Integer numberOfState=0, timeToSave =3;
	private String CommandFileName = "item.txt";
	
	public ArrayList<Book> getBookArray() {
		return BookArray;
	}
	public void setBookArray(ArrayList<Book> BookArray) {
		this.BookArray = BookArray;
	}
	public void addBook(serial.Book book){
		int check = 0,index = 0;
		for(int i=0;i<BookArray.size();i++) {
			if (BookArray.get(i).getName().equals(book.getName()) && BookArray.get(i).getPrice() == book.getPrice()) {
			check = 1;
			index = i;
			//System.out.print(book.getQuantity() + BookArray.get(index).getQuantity());
			}
		}
		if(check == 1) {
				for(Book book1 : BookArray) {
			if (book1.getName().equals(book.getName())) {
				book1.setQuantity(book.getQuantity()+book1.getQuantity());
				if(++numberOfState == timeToSave){
					this.saveState();
	    		numberOfState=0;
					}
				//System.out.println(book1.getQuantity());
			}
		}
				System.out.println(book.getName()+" is already(increase quantity SUCCESS!!)");
			}else {
				BookArray.add(book);
				if(++numberOfState == timeToSave){
					this.saveState();
	    		numberOfState=0;
					}
				System.out.println(book.getName()+" Add SUCCESS!!");
			}
		}
	public void sellBook(String bookName,int decreaseQuantity){
		for(Book book : BookArray){
			if(book.getName().equals(bookName) && book.getQuantity() > 0){
				book.changeQuantity(-decreaseQuantity);
				if(++numberOfState == timeToSave){
			    	this.saveState();
			    	numberOfState=0;
			    }
				return ;
			}	
		}
	}
	public void CopyBook(String bookName, Integer NumberOfCopy ){
		for(Book book : BookArray){
			
			if(book.getName().equals(bookName)){
				book.changeQuantity(NumberOfCopy);
				if(++numberOfState == timeToSave){
			    	this.saveState();
			    	numberOfState=0;
			    }	
			}
		}
	}
	public void changePrice(String bookName,Integer newPrice){
		for(Book book : BookArray){
			
			if(book.getName().equals(bookName)){
				book.setPrice(newPrice);
				if(++numberOfState == timeToSave){
			    	this.saveState();
			    	numberOfState=0;
			    }
			}
		}
	}
	public Integer findPriceByName(String bookName){
		int price=0;
		for(Book book : BookArray){
			if(book.getName().equals(bookName)){
				price = book.getPrice();
			}
		}
		return price;
	}
	public Integer findPriceByID(Integer bookID) {
		int price = 0 ;
		for(Book book : BookArray){
			if(book.getUniqueID().equals(bookID)){
				price = book.getPrice();
			}
		}
		return price;
	}
	public Integer findQuantityByName(String bookName){
		int quantity = 0;
		for(Book book : BookArray){
			if(book.getName().equals(bookName)){
				quantity = book.getQuantity();
			}	
		}
		return quantity;
	}
	public Integer findQuantityByID(Integer bookID){
		int quantity = 0;
		for(Book book : BookArray){
			
			if(book.getUniqueID().equals(bookID)){
				quantity = book.getQuantity();
			}
		}
		return quantity;
	}
	public void saveState(){
	    memento.saveState(BookArray);
	    File file = new File(CommandFileName);
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getState(){
		invent.getState(); 
		BookArray = (invent.getInventory().getBookArray());
	}
}