import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {
String file;
	
	
	public FileRead(String file) {
		this.file = file;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public ArrayList<Point> scan() throws IOException{
		Scanner scan;
		ArrayList<Point> points=new ArrayList<Point>();
		
		scan = new Scanner(new File(file));
		while (scan.hasNextLine()){
			String s=scan.nextLine();
			String[] splits=s.split(" ");
			Point point=new Point(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]));
			points.add(point);
		}
		return points;
	}
}
