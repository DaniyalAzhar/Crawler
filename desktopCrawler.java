import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class desktopCrawler{
 
  private String fileName;
  private List<String> result = new ArrayList<String>();
 
  public String getFileName(){
	return fileName;
  }
 
  public void setFileName(String file_Name){
	this.fileName = file_Name;
  }
 
  public List<String> getResult(){
	return result;
  }
 
  public void searchDirectory(File directory, String fileName){
		setFileName(fileName);
		if(directory.isDirectory())//If directory exists.
		    search(directory); //go to search file function.
		else
		    System.out.println(directory.getAbsoluteFile() + " : directory does not exist.");
}
	 
	  private void search(File file){
		  System.out.println("Searching directory ... " + file.getAbsoluteFile());	
		    if(file.canRead()){           //is there permission to read directory.
		    	for(File temp : file.listFiles()){
		    		if (temp.isDirectory())   //if there are further directories in this directory.
		    			search(temp);   //search files inside
		    		else
		    			if(getFileName().equals(temp.getName().toLowerCase())) //if not directory, then file. So does fileName match.
		    					result.add(temp.getAbsoluteFile().toString()); //add to result List.
		    	}
		    }
		    else
		    	System.out.println(file.getAbsoluteFile() + "Permission Denied"); //if permission is not granted.
	  }
  public static void main(String[] args){
 
	desktopCrawler fileSearch = new desktopCrawler();
	String directory,file;
	Scanner in = new Scanner(System.in);
    System.out.println("Enter diretory path(Sample= 'C:/Users/daniyal/Desktop').");
    directory = in.nextLine();
    System.out.println("Enter file that you want to search(Sample= 'file.txt').");
    file = in.nextLine();
    
	fileSearch.searchDirectory(new File(directory), file);
 
	int count = fileSearch.getResult().size();
	int i=0;
	if(count ==0)
	    System.out.println("\nNo result found!");
	else{
	    System.out.println("\nFound " + count + " result!\n");
	    for (String matched : fileSearch.getResult()){
	    	i++;
	    	System.out.println(i + ") " + matched);
	    }
	}
  }
}