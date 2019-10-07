package memento;

import inventory.Inventory;

public class Originator {
	   private Inventory state;

	   public void setState(Inventory state){
	      this.state = state; 
	   }

	   public Inventory getState(){
	      return state; 
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state); 
	   }

	   public void getStateFromMemento(Memento memento){
	      state = memento.getState();
	   }
	}