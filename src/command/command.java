package command;

import inventory.Inventory;

public abstract class Command {
	
	public abstract void execute(Inventory newInvent);
}
