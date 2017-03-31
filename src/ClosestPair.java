import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ClosestPair {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fnames={"10points.txt","100points.txt","1000points.txt"};
		
		
		for (String fname: fnames){
			
			ArrayList<Point> points=new ArrayList<Point>();
			ArrayList<Point> pointsByY=new ArrayList<Point>();
			List<Point> pointsByX= new ArrayList<Point>();
			
			Point[] pair=new Point[2];
			double distance;
			
			try {
				FileRead fileRead = new FileRead(fname);
				points=fileRead.scan();
		    	}
		    catch (IOException e) {
		      System.out.println("ERROR: file opening error for " + fname);
		      e.printStackTrace();
		      System.exit(0); // terminate the program
		    }
			
			Collections.sort(points, new CompareByX());
			//System.out.println(points);
			
			
			for (Point point: points){
				pointsByY.add(point);
			}
			Collections.sort(pointsByY, new CompareByY());
			//System.out.println(pointsByY);

			pair=closestPair(points,pointsByY);
			distance=pair[0].dis(pair[1]);
			String nameOut=fname.substring(0, fname.length()-10);
			System.out.println(nameOut+" points test file\n");
			System.out.println("The minimum distance is:");
			System.out.println(distance+": "+pair[0]+"<--->"+pair[1]+"\n\n");
		}
	}
	
	public static Point[] closestPair(ArrayList<Point> points, ArrayList<Point> pointsByY){
		
		Point[] pair=new Point[2];
		
		if (points.size()<=3){
			double min=Integer.MAX_VALUE;
			for (int i=0;i<points.size();i++){
				for (int j=i+1;j<points.size();j++){
					if ((points.get(i).dis(points.get(j)))<min){
						pair[0]=points.get(i);
						pair[1]=points.get(j);
						min=points.get(i).dis(points.get(j));
					}
				}
			}
			return pair;
		} 
		
		else {
			int middle=points.size()/2;
			//double M=(points.get(middle).getX()+points.get(middle+1).getX())/2;
			double M=points.get(middle).getX();
			ArrayList<Point> pointsLeft=new ArrayList<Point>();
			ArrayList<Point> pointsRight=new ArrayList<Point>();
			ArrayList<Point> pointsMiddle=new ArrayList<Point>();
			Point[] pairLeft=new Point[2];
			Point[] pairRight=new Point[2];
			Point[] pairMiddle=new Point[2];
			for (Point point: points){
				if(point.getX()<M){
					pointsLeft.add(point);
				}else {
					pointsRight.add(point);
				}
			}
			pairLeft=closestPair(pointsLeft, pointsByY);
			pairRight=closestPair(pointsRight, pointsByY);
			double distanceLeft=pairLeft[0].dis(pairLeft[1]);
			double distanceRight=pairRight[0].dis(pairRight[1]);
			double min;
			double minMiddle=Integer.MAX_VALUE;
			double distance;
			if (distanceLeft<=distanceRight){
				min=distanceLeft;
				pair=pairLeft;
			}
			else {
				min=distanceRight;
				pair=pairRight;
			}
			for (Point point: pointsByY){
				if ((points.contains(point))&&((point.getX())>=(M-min))&&((point.getX())<=(M+min))){
					pointsMiddle.add(point);
				}
			}
			
			if (pointsMiddle.size()<8){
				for (int i=0; i<pointsMiddle.size();i++){
					for (int j=i+1;j<pointsMiddle.size();j++){
						distance=(pointsMiddle.get(i)).dis(pointsMiddle.get(j));
						if (distance<minMiddle){
							minMiddle=distance;
							pairMiddle[0]=pointsMiddle.get(i);
							pairMiddle[1]=pointsMiddle.get(j);
						}
					}
				}
			}
			else {
				for (int i=0;i<((pointsMiddle.size())-7);i++){
					for (int j=i+1;j<i+8;j++){
						distance=(pointsMiddle.get(i)).dis(pointsMiddle.get(j));
						if (distance<minMiddle){
							minMiddle=distance;
							pairMiddle[0]=pointsMiddle.get(i);
							pairMiddle[1]=pointsMiddle.get(j);
						}
					}
				}
				for (int i=pointsMiddle.size()-7; i<pointsMiddle.size();i++){
					for (int j=i+1;j<pointsMiddle.size();j++){
						distance=(pointsMiddle.get(i)).dis(pointsMiddle.get(j));
						if (distance<minMiddle){
							minMiddle=distance;
							pairMiddle[0]=pointsMiddle.get(i);
							pairMiddle[1]=pointsMiddle.get(j);
						}
					}
				}
			}
			if (minMiddle<min){
				pair=pairMiddle;
			}
			
			return pair;
		}
		
				
	}

}
