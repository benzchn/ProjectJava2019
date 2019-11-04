package memento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CareTaker {
	  
	 private Object object;
	 private String fileName = "InventoryStock.txt";
	 byte[] buf = new byte[1024];
	 int bytesRead;
	 private String temporaryFileName = "CartInventoryStock.txt";
	 private File tempFile = new File(temporaryFileName);
	 private File file = new File(fileName);
	   public void serialzeMemento(Memento state){
		   try
		      {
		         FileOutputStream temporaryFileOut = new FileOutputStream(temporaryFileName);
		         ObjectOutputStream out = new ObjectOutputStream(temporaryFileOut);
		         out.writeObject(state);         
		         out.close();
		         temporaryFileOut.close();
		         tempFile.renameTo(file);
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
	   }
	   public Memento deserialseMemento(){	 
		   try
		      {
		         FileInputStream fileIn = new FileInputStream(fileName);
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         object = in.readObject();
		         in.close();
		         fileIn.close();
		         return (Memento)object;
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return null;
		         
		      }catch(ClassNotFoundException c)
		      {
		          System.out.println("class not found");
		          c.printStackTrace();
		          return null; 
		       }
	   }
	}