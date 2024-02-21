import java.io.*;

import java.util.*;
import com.opencsv.*;


//read csv file and put them into a hash map
public class readCSV{
	private HashMap<String, countryList> countryMap = new HashMap<String, countryList>(); 
	
	public readCSV () {
		List<List<String>> record = new ArrayList<List<String>>();

		String line = "";
		
		try {
			CSVReader csvReader = new CSVReader(new FileReader("country_list.csv"));
			String[] values = null;
			values = csvReader.readNext();
			while((values = csvReader.readNext())!=null) {
				countryList countryList = new countryList();
				record.add(Arrays.asList(values));
				countryList.setCountryCode(Integer.parseInt(values[0]));
				countryList.setFullName(values[1]);
				countryList.setAbbName(values[2]);
				countryList.setCountryComments(values[3]);
				countryList.setIso2(values[4]);
				countryList.setIso3(values[5]);
				countryList.setStartYear(values[6]);
				countryList.setEndYear(values[7]);
				countryMap.put(countryList.getFullName(), countryList);
			}
			
			
				/*
				countryList countryList = new countryList();
				String country = "";
				
				System.out.println(values);
				int value;
				//将values中的每一组数据set成countryList中的特定内容（全程、简称、年份）
				countryList.setCountryCode(Integer.parseInt(values[0]));//因为默认的values是String形式 所以转换为Int
				
				countryList.setFullName(values[1]);
				countryList.setAbbName(values[2]);//简称
				countryList.setCountryComments(values[3]);
				countryList.setIso2(values[4]);//二字简称
				countryList.setIso3(values[5]);//三字简称
				countryList.setStartYear(values[6]);
				countryList.setEndYear(values[7]);//起始年份
				
				countryMap.put(countryList.getFullName(), countryList);//将内容input到Map中
				
			}*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public HashMap<String, countryList> getHashMap() {
		// TODO Auto-generated method stub
		return countryMap;
	}

	
	/*public static void main(String[] args) {
		new readCSV();
		System.out.println(countryMap);
	}*/
	

 
}