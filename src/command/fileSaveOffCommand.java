package command;

public class fileSaveOffCommand implements command{
	  //reference to the light
	  saveFile file;
	  public fileSaveOffCommand(saveFile file){
	    this.file = file;
	  }
	  public void execute(){
		  file.saveFileOff();
	  }
}
