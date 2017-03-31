import java.util.Comparator;

public class CompareByX implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		Point point1=(Point) o1;
		Point point2=(Point) o2;
		if ((point1.getX())<(point2.getX())){
			return -1;
		}
		else if ((point1.getX())>(point2.getX())){
			return 1;
		}
		else return 0;
	}
}
