package memento;

import inventory.Inventory;

public class Memento {
		   private Inventory state;

		   public Memento(Inventory state){
		      this.state = state;
		   }

		   public Inventory getState(){
		      return state;
		   }	
		}
