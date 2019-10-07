package inventory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import command.command;
import command.fileSaveOnCommand;
import command.saveFile;
import command.saveFileControl;
import memento.CareTaker;
import memento.Originator;
import serial.addBook;

public class Inventory {

	Originator originator = new Originator();
    CareTaker careTaker = new CareTaker();
    
    saveFileControl savefilecontrol = new saveFileControl();
    saveFile savefile = new saveFile();
    
    ArrayList<addBook> addbook = new ArrayList<addBook>();
    ArrayList<addBook> getAddbook = new ArrayList<addBook>();
    
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	int menuNum,bookPrice,bookQuantity,exit=0,searchBookbyNum;
	String bookName = "",searchBookbyName = "";
	String filename = "inventoryStock.txt";
	int n=0,i;
	public Inventory() {
		
	}
	
	
	public void connectToDB() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test?serverTimezone=PST8PDT","root","");
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void addBook(String name, int price, int quantity) throws Exception {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from addBook where name = " + '"' + name + '"');
		String bookNameOld = "";
		int quantityOld = 0;
		while (resultSet.next()) {
			bookNameOld = resultSet.getString("name");
			quantityOld = resultSet.getInt("quantity");
		}
		if(bookNameOld.equals(name)) {
			try {
				System.out.println("----Book available in inventory !!----");
			} catch (Exception e) {
				throw e;
			}
		}else {
		try {
			statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO addBook(id,name,price,quantity) VALUES (NULL,'" + name + "','" + price
					+ "','" + quantity + "')");
		} catch (Exception e) {
			throw e;
		}
		}
	}
	
	public void increaseQuantityBook(String name, int quantity) throws Exception {
		statement = connect.createStatement();
		resultSet = statement.executeQuery("select * from addBook where name = " + '"' + name + '"');
		String bookNameOld = "";
		int quantityOld = 0;
		while (resultSet.next()) {
			bookNameOld = resultSet.getString("name");
			quantityOld = resultSet.getInt("quantity");
		}
		if(bookNameOld.equals(name)) {
			try {
				int quantityNew = quantityOld + quantity;
				statement = connect.createStatement();
				statement.execute("UPDATE addBook SET quantity = "+ '"' + quantityNew + '"'+ "where name = " + '"' + name + '"');
			} catch (Exception e) {
				throw e;
			}
		}else {
		}
	}
	
	public void changePriceBook(String name, int newprice) throws Exception {
		try {
				statement = connect.createStatement();
				statement.execute("UPDATE addBook SET price = "+ '"' + newprice + '"'+ "where name = " + '"' + name + '"');
			} catch (Exception e) {
				throw e;
			}
		}
	
	
	public void searchByName(String bookname) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from addBook where name = " + '"' + bookname + '"');
			System.out.println("\n..........Result for search by Name..........");
			System.out.print("Book ID\t|\tBook Name\t|\tPrice\t|\tQuantity\n");
			if(!resultSet.next()) {
				System.out.println("\nNot Found! By name is " + bookname);
		}else {
			while (resultSet.next()) {
				int Id = resultSet.getInt("id");
				String bookName = resultSet.getString("name");
				int price = resultSet.getInt("price");
				int quantity = resultSet.getInt("quantity");
				System.out.println(String.format("%d\t\t%s\t\t%d\t\t%d", Id, bookName, price, quantity));
			}
		}
		} catch (Exception e) {
			throw e;
		}
	}

	public void searchById(int bookid) throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from addBook where id = " + bookid);
			System.out.println("\n..........Result for search by ID..........");
			System.out.print("Book ID\t|\tBook Name\t|\tPrice\t|\tQuantity\n");
			if(!resultSet.next()) {
					System.out.println("\nNot Found! By ID of " + bookid);
			}else {
			while (resultSet.next()) {
				int Id = resultSet.getInt("id");
				String bookName = resultSet.getString("name");
				int price = resultSet.getInt("price");
				int quantity = resultSet.getInt("quantity");
				System.out.println(String.format("%d\t\t%s\t\t%d\t\t%d", Id, bookName, price, quantity));
			}
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void showBook() throws Exception {
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from addBook");
			System.out.print("Book ID\t|\tBook Name\t|\tPrice\t|\tQuantity\n");
			
			while (resultSet.next()) {
				int Id = resultSet.getInt("id");
				String bookName = resultSet.getString("name");
				int price = resultSet.getInt("price");
				int quantity = resultSet.getInt("quantity");
				System.out.println(String.format("%d\t\t%s\t\t%d\t\t%d", Id, bookName, price, quantity));
				addbook.add(new addBook(Id,bookName,price,quantity));
				n = n+1;
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void saveToMemento(Inventory inventory) {
		originator.setState(inventory);
	    careTaker.add(originator.saveStateToMemento());
	    
	      System.out.println(".......!! Save !!.......");

	}
	
	public void saveInventorytoFile() {
		 try
	      {    
	          //Saving of object in a file 
	          FileOutputStream file = new FileOutputStream(filename); 
	          ObjectOutputStream out = new ObjectOutputStream(file); 
	            
	          out.writeObject(addbook);
	            
	          out.close();
	          file.close(); 
	            
	          System.out.println("Object has been serialized"); 

	      } 
	      catch(IOException ex) 
	      { 
	          System.out.println("IOException is caught"); 
	      } 

	}
	
	public Inventory getMementoRestore() {
		originator.getStateFromMemento(careTaker.get(0));
	    return originator.getState();
	}
	
	public void getInventoryFromFile() {
		getAddbook =null;
		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			getAddbook = (ArrayList) in.readObject();

			in.close();
			file.close();

			System.out.print("Book ID\t|\tBook Name\t|\tPrice\t|\tQuantity\n");
			for (i = 0; i < getAddbook.size(); i++) {
				System.out.println(getAddbook.get(i).id + "\t\t" + getAddbook.get(i).name + "\t\t" + getAddbook.get(i).price
						+ "\t\t" + getAddbook.get(i).quantity);
			}
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}
	}
	
	public void Save() {
		 command saveFileOn = new fileSaveOnCommand(savefile);
		savefilecontrol.setCommand(saveFileOn);
		savefilecontrol.pressButton();
	}
	// You need to close the resultSet
		public void close() {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
}
