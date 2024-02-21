
import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.Value;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class MainUI extends JFrame {
	private static MainUI instance;
	private static JPanel west;
	private static String country,analysisType,view;
	private static int year1,year2;
	private JComboBox<String> fromList,toList,countriesList,methodsList,viewsList;
	private result res;
	
	private readCSV csv;
	private HashMap<String, countryList> countryMap;
	private JScrollPane report;
	private ChartPanel lChart,bChart,pChart,sChart;
	boolean scatter=false,pie=false,line=false,bar=false;
	
	public static MainUI getInstance() {
		if(instance == null) {
			instance = new MainUI();
			
		}
		return instance;
	}
	/*
	 * Perform ui
	 */
	public MainUI() {
		super("Environment & Health analyzer");
		//set all needed button and information in to the frame
		csv = new readCSV();
		countryMap = csv.getHashMap();
		
		
		JLabel chooseCountryLabel = new JLabel("choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		Vector<String> years = new Vector<String>();
		
		//read all value and out all countries full name in to countriesNames
		
		for(countryList value: countryMap.values()) {
			countriesNames.add(value.getFullName());
		}
		countriesNames.sort(null);
		country = countriesNames.firstElement();
		countriesList = new JComboBox<String>(countriesNames);
		countriesList.addActionListener(new ActionListener() {		//add selected country to country
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				country = countriesList.getSelectedItem().toString();
			}
			
		});
		
		
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");

		//add all needed years in to the frame
		for (int i = 2020; i >= 2000; i--) {
			years.add("" + i);
		}
		year1 = 2020;
		year2 = 2020;
		fromList = new JComboBox<String>(years);
		toList = new JComboBox<String>(years);
		fromList.addActionListener(new ActionListener() {		//add selected year to year1
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				year1 = Integer.parseInt(fromList.getSelectedItem().toString());
				
			}
			
		});
		toList.addActionListener(new ActionListener() {		//add selected year to year2
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				year2 = Integer.parseInt(toList.getSelectedItem().toString());
			}
			
		});
		
		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);
		
		JButton recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");
		view = "Pie Chart";
		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		viewsList = new JComboBox<String>(viewsNames);
		viewsList.addActionListener(new ActionListener() {		//add selected view to view
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				view = viewsList.getSelectedItem().toString();
				

			}
			
		});
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");
		analysisType = "Forest Area";
		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Forest Area");
		methodsNames.add("Pm2.5 vs Forest Area");
		methodsNames.add("Forest Area vs GDP");
		methodsNames.add("Co2 vs Energy");
		methodsNames.add("Co2 vs Pm2.5");
		methodsNames.add("Co2 vs GDP");
		methodsNames.add("Co2 vs Forest Area");
		methodsNames.add("Population vs Pm2.5");
		
		

		methodsList = new JComboBox<String>(methodsNames);
		methodsList.addActionListener(new ActionListener() {		//add selected method to analysisType
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(Integer.parseInt(fromList.getSelectedItem().toString()));	//for test
				analysisType = methodsList.getSelectedItem().toString();
				
			}
			
		});

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);
		
		JPanel east = new JPanel();

		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		
		
		
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		//a frame with no graphs is created
		
		//get action from recalculate button
		recalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean display = true;
				String IsoCode = countryMap.get(country).getIso3();
				if(IsoCode.equals("N/A")) {
					JOptionPane.showMessageDialog(null, "country not avilable");
				}
				else {
					int yearS = Integer.parseInt(countryMap.get(country).getStartYear());
					int yearE;
					if(!(countryMap.get(country).getEndYear().equals("Now"))) {
						yearE = Integer.parseInt(countryMap.get(country).getEndYear());
					}
					else {
						yearE = 2021;
					}
					
					/*if(year1<yearS) {
						year1 = yearS;
					}*/
					if(year1>year2) {
						JOptionPane.showMessageDialog(null, "wrong year selection");
					}
					selection sec = new selection(IsoCode,year1,year2,analysisType);//get result using selected data
					Strategy_factory fac = new Strategy_factory();
					try {
						Data data;
						strategy st = fac.factoryMethod(sec);
						res = st.calculate(sec);
						if(analysisType.equals("Forest Area")) {
							if(res.get_result1().get_data_recieved().containsKey(1)) {
								display = false;
								JOptionPane.showMessageDialog(null, "data not avilable");

							}
						}
						else {
							if(res.get_result1().get_data_recieved().containsKey(1)&&res.get_result2().get_data_recieved().containsKey(1)) {
								display = false;
								JOptionPane.showMessageDialog(null, "data not avilable");
								
							}
							else if(res.get_result1().get_data_recieved().containsKey(1)) {
								res.set_result1(res.get_result2());
								
							}
							else if(res.get_result2().get_data_recieved().containsKey(1)) {
								res.set_result2(null);
							}
							
						}
						
						
					} catch (nameException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "unable to featch data");
					}
					if(display == true) {		//if no exception and all needed data is collected, add the report to frame
						west.removeAll();
						getContentPane().add(west, BorderLayout.WEST);		
						getContentPane().validate();
						getContentPane().repaint();
						createReport rep = new createReport(res);
						report = rep.getPanel();
						west.add(report);
						getContentPane().add(west, BorderLayout.WEST);		
						getContentPane().validate();
						getContentPane().repaint();
						scatter=false;
						pie=false;
						line=false;
						bar=false;
					}
					
				
					
					
				}
				
			}
		});
		//perform action on add button
		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * check the input view type, then check if graph exists, if not add the graph to panel
				 */
				if(view.equals("Report")) {		
					boolean exist = false;
					Component[] components = west.getComponents();
					Component component = null;
					
					for(int i =0;i<components.length;i++) {
						component = components[i];
						if(component instanceof  JScrollPane) {
							exist = true;
							break;
						}
					}
					if(exist==false) {
						createReport rep = new createReport(res);	
						report = rep.getPanel();
						
						if(report==null) {
							JOptionPane.showMessageDialog(null, "data is not avilable");
						}
						else {
							west.add(report);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
						}
						
						
					}
				}
				else if(view.equals("Scatter Chart")) {
					
					if(scatter==false) {
						scatter = true;
						createScatterPlotChart sca = new createScatterPlotChart(res);
						sChart = sca.get_panel();
						
						if(sChart==null) {
							JOptionPane.showMessageDialog(null, "data is not avilable");
						}
						else {
							west.add(sChart);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
						}
					}
				}
				else if(view.equals("Bar Chart")) {
					
					if(bar==false) {
						bar = true;
						creatBarChart ba = new creatBarChart(res);
						bChart = ba.get_panel();
						
						if(bChart==null) {
							JOptionPane.showMessageDialog(null, "data is not avilable");
						}
						else {
							west.add(bChart);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
						}
					}
				}
				else if(view.equals("Line Chart")) {	
					
					if(line==false) {
						line = true;
						createLineChart li = new createLineChart(res);
						lChart = li.get_panel();
						if(lChart==null) {
							JOptionPane.showMessageDialog(null, "data is not avilable");
						}
						else {
							west.add(lChart);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
						}
					}
				}
				else {	//pie chart can only be performed by Forest Area
					
					if(analysisType.equals("Forest Area")==false) {
						JOptionPane.showMessageDialog(null, "chart not avilable");
					}
					
					else if(pie==false&&(analysisType.equals("Forest Area")==true)) {
						pie = true;
						createPieChart pi = new createPieChart(res);
						pChart = pi.get_panel();
						
						if(pChart==null) {
							JOptionPane.showMessageDialog(null, "data is not avilable");
						}
						else {
							west.add(pChart);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
						}
					}
					
				}
				

			}
		});//end of addView
		
		/*
		 * check the selected view, then check if graph exists, in exists remove the graph 
		 */
		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(view.equals("Report")) {
					boolean exist = false;
					Component[] components = west.getComponents();
					Component component = null;
					
					for(int i =0;i<components.length;i++) {
						component = components[i];
						if(component instanceof  JScrollPane) {
							exist = true;
							west.remove(component);
							getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
							getContentPane().validate();
							getContentPane().repaint();
							
							break;
						}
					}
					if(exist==false) {
						JOptionPane.showMessageDialog(null, "no such chart exists");
						
					}
				}
				else if(view.equals("Scatter Chart")) {
					
					if(scatter==true) {
						scatter = false;
						west.remove(sChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else if(view.equals("Bar Chart")) {
					
					if(bar==true) {
						bar = false;
						west.remove(bChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
						
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else if(view.equals("Line Chart")) {	
					
					if(line==true) {
						line = false;
						west.remove(lChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
				}
				else {	
					
					if(pie==true) {
						pie = false;
						west.remove(pChart);
						getContentPane().add(west, BorderLayout.WEST);		//the next three line is to update west to JFrame
						getContentPane().validate();
						getContentPane().repaint();
					}
					else {
						JOptionPane.showMessageDialog(null, "no such chart exists");
					}
					
				}
				

			}
			
		});
		
		
		
	}

}