
public class Point {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	
	public Point(int xIn, int yIn){
		x=xIn;
		y=yIn;
	}
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret="("+x+", "+y+")";
		return ret;
	}
	
	/**
	 * 
	 * @param point
	 * @return double
	 */
	public double dis(Point point){
		return Math.sqrt(Math.pow((x-point.getX()),2)+Math.pow((y-point.getY()),2));
	}
}
