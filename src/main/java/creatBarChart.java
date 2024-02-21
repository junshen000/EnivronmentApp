import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

//create a barchart 
public class creatBarChart{
	ChartPanel chartPanel;
	boolean checker = true;
 public creatBarChart(result result){
	 
	
	if(result.get_result2()==null) {//one analysis chosen
		
		String analysis_name;
		 int count1=0;
		DefaultCategoryDataset series1 = new DefaultCategoryDataset(); 
		HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
		int year_start = result.getYear1();
		int year_end = result.getYear2();
		for(int i=year_start; i<year_end+1; i++) {	//add data into series
			if(data_container1.get(i) == -1) {
				count1++;
			}
			else {
				String a =String.valueOf(i);
				series1.addValue(data_container1.get(i),result.get_result1().getType() , a);
			}
			
		}//add data to the dataset1
		if(count1==year_end +1 - year_start) {	//if all data does not exists
			checker = false;
		}
		analysis_name =result.get_result1().getType();
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		plot.setDataset(series1);
		plot.setRenderer(barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));
		plot.setRangeAxis(new NumberAxis(result.get_result1().get_unit()));
		plot.mapDatasetToRangeAxis(0, 0);
		JFreeChart barChart = new JFreeChart(analysis_name,
			    new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
	}
	else {//two analysis chosen
		
		int count1=0;
		 int count2=0;
		 String analysis_name;
	  
	  /*JFreeChart barChart = ChartFactory.createBarChart("CO2 vs Forest", "Year", 
	    "Data", createDataset(), PlotOrientation.VERTICAL, true, true, true); */
	   
		DefaultCategoryDataset series1 = new DefaultCategoryDataset(); 
		DefaultCategoryDataset series2 = new  DefaultCategoryDataset();
		HashMap<Integer,Double> data_container1 = result.get_result1().get_data_recieved();
		HashMap<Integer,Double> data_container2 = result.get_result2().get_data_recieved();
		int year_start = result.getYear1();
		int year_end = result.getYear2();
		
		for(int i=year_start; i<year_end+1; i++) {
			if(data_container1.get(i) == -1) {
				count1++;
			}
			else {
				String a =String.valueOf(i);
				series1.addValue(data_container1.get(i),result.get_result1().getType() , a);
			}
			
		}//add data to the dataset1
		for(int i=year_start; i<year_end+1; i++) {
			if(data_container2.get(i) == -1) {
				count2++;
			}
			else {
				String a =String.valueOf(i);
				series2.addValue(data_container2.get(i),result.get_result2().getType() , a);
			}
			
		}//add data to the dataset2
		if(count1==year_end +1 - year_start && count2==year_end +1 - year_start) {
			checker = false;
		}
		analysis_name =result.get_result1().getType()+" vs " +result.get_result2().getType();
		  CategoryPlot plot = new CategoryPlot();
		  BarRenderer barrenderer1 = new BarRenderer();
		  BarRenderer barrenderer2 = new BarRenderer();
		  
		  plot.setDataset(0, series1);
		  plot.setRenderer(0, barrenderer1);
		  CategoryAxis domainAxis = new CategoryAxis("Year");
		  plot.setDomainAxis(domainAxis);
		  plot.setRangeAxis(new NumberAxis(""));
		  plot.setDataset(1, series2);
		  plot.setRenderer(1, barrenderer2);
		  plot.setRangeAxis(0, new NumberAxis(result.get_result1().get_unit()));
		  plot.setRangeAxis(1, new NumberAxis(result.get_result2().get_unit()));
		
		  plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		  plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		
		  JFreeChart barChart = new JFreeChart(analysis_name,
		    new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		  chartPanel = new ChartPanel( barChart );    
		  chartPanel.setPreferredSize(new Dimension(400, 300));
		  chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		  chartPanel.setBackground(Color.white);
		 }
		}
 	public ChartPanel get_panel() {
		if(checker == false) {
			return null;
		}
		else {
			return this.chartPanel;
		}
		
	}
 }

//}