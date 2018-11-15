package myMath;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class plotGraph {
	
	public static void main(String[] args) throws IOException { 
		Polynom p=new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
		ArrayList<Double> x=new ArrayList<Double>();
		ArrayList<Double> y=new ArrayList<Double>();
		ArrayList<Double> xMax=new ArrayList<Double>();
		ArrayList<Double> yMax=new ArrayList<Double>();
		ArrayList<Double> xMin=new ArrayList<Double>();
		ArrayList<Double> yMin=new ArrayList<Double>();
		double start=-2;
		double end=6;
		double eps=0.0001;
		while(start<=end) {
			x.add(start);
			y.add(p.f(start));
			start+=eps;
			if(p.f(start-eps)>p.f(start) && p.f(start+eps)>p.f(start)) {
				xMin.add(start);
				yMin.add(p.f(start));
				x.add(start);
				y.add(p.f(start)+0.3);
				x.add(start);
				y.add(p.f(start)-0.3);

			}
			else if(p.f(start-eps)<p.f(start) && p.f(start+eps)<p.f(start)) {
				xMax.add(start);
				yMax.add(p.f(start));
				x.add(start);
				y.add(p.f(start)+0.3);
				x.add(start);
				y.add(p.f(start)-0.3);

			}
		}
		
		
 		double[] xData = new double[x.size()] ;
		double[] yData = new double[y.size()] ;
		for(int i=0;i<xData.length;i++) {
			xData[i]=x.get(i);
			yData[i]=y.get(i);
		}
		String ExtremePoints="";
		DecimalFormat shortFormat=new DecimalFormat("#####.###");
		for(int i=0;i<xMax.size();i++) {
			if(i==0) {
				ExtremePoints=ExtremePoints+"Maximum Points: \n";
			}
			ExtremePoints=ExtremePoints + "(" +shortFormat.format(xMax.get(i)) +" , " +shortFormat.format(yMax.get(i)) +"),\n";
		}
		for(int i=0;i<xMin.size();i++) {
			if(i==0) {
				ExtremePoints=ExtremePoints+"Minimum Points: \n";
			}
			ExtremePoints=ExtremePoints + "(" +shortFormat.format(xMin.get(i)) +" , " +shortFormat.format(yMin.get(i)) +"),\n";
		}
		DecimalFormat shortFormatForArea=new DecimalFormat("#####.######");
		String area= shortFormatForArea.format(p.area(-0.941, 4.831, 0.01))+"";
		// Create Chart
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)"+"\n"+ExtremePoints + "The area under the X axis:\n " + area, xData, yData);
		
		// Show it
		new SwingWrapper(chart).displayChart();

		// Save it
		BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);

		// or save it in high-res
		BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
	}
}