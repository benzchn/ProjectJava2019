package command;

public class saveFileControl {
	private command command;
	  public void setCommand(command command){
	    this.command = command;
	  }
	  public void pressButton(){
	    command.execute();
	  }
}
