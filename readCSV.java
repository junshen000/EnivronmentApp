import java.io.*;
import java.util.*;


public class readCSV{
	static HashMap<Integer, countryList> countryMap = new HashMap<Integer, countryList>(); 
	
	public readCSV () {
		
		String line = "";
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader("country_list.csv"));
			br.readLine();//������һ��
			
			while((line = br.readLine()) != null) {
				
				countryList countryList = new countryList();
				
				String[] values = line.split(",");//�Զ���Ϊ׼�ָ�ÿһ�����ݣ�������8��array
				
				//��values�е�ÿһ������set��countryList�е��ض����ݣ�ȫ�̡���ơ���ݣ�
				countryList.setCountryCode(Integer.parseInt(values[0]));//��ΪĬ�ϵ�values��String��ʽ ����ת��ΪInt
				countryList.setFullName(values[1]);//values1�����ǹ���ȫ����
				countryList.setAbbName(values[2]);//���
				countryList.setCountryComments(values[3]);
				countryList.setIso2(values[4]);//���ּ��
				countryList.setIso3(values[5]);//���ּ��
				countryList.setStartYear(values[6]);
				countryList.setEndYear(values[7]);//��ʼ���
				
				countryMap.put(countryList.getCountryCode(), countryList);//������input��Map��
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new readCSV();
		System.out.println(countryMap);
	}
	

 
}