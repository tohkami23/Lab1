import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class DbData {
    public static ArrayList<String[]> readFile(String fname) {
	ArrayList<String[]> data = new ArrayList<String[]>();
	try {
	    File fobj = new File(fname);
	    Scanner scan = new Scanner(fobj);
	    while (scan.hasNextLine()) {
		String line = scan.nextLine();
		String[] itemList = line.split("[,]", 0);
		if (itemList.length != 8) continue;
		for (int i = 0; i < itemList.length; i++) itemList[i] = itemList[i].trim();
		data.add(itemList);
	    }
	    scan.close();
	}
	catch (Exception e) {
	    System.out.println("<<< " + e.getMessage());
	}
	return data;
    }
}
