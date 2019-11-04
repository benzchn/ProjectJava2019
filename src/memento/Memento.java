package memento;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class Memento implements java.io.Serializable{

	///////saveState for new book//////////
	private ArrayList<serial.Book> bookArray;
	public void saveState(ArrayList<serial.Book> arrayList){
		this.bookArray = new ArrayList<serial.Book>(arrayList);
	}
	///////call state book//////////
	public ArrayList<serial.Book> getState(){
		return this.bookArray;
	}
}
