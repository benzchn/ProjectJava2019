package inventory;
import serial.Book;

public interface InventoryInterface {

	public void addBook(Book book);
	public void sellBook(String bookName,int decreaseQuantity);
	public void CopyBook(String bookName, Integer NumberOfCopy ) ;
	public void changePrice(String bookName,Integer newPrice) ;
	public Integer findPriceByName(String bookName) ;
	public Integer findQuantityByName(String bookName) ;
	public Integer findQuantityByID(Integer bookID);
	public Integer findPriceByID(Integer bookID);
	public void saveState();
	public void getState();
}
