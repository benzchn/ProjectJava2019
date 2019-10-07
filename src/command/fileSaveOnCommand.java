package command;

import inventory.Inventory;
import memento.CareTaker;
import memento.Originator;

public class fileSaveOnCommand implements command{
	  //reference to the light
	  saveFile file;
	  //Originator originator = new Originator();
	 // CareTaker careTaker = new CareTaker();
	  Inventory iv = null;
	  public fileSaveOnCommand(saveFile file){
	    this.file = file;
	  }
	  public void execute(){
		  file.saveFileOn();
	  }
}
