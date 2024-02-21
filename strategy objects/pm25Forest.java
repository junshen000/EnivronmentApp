
public class pm25Forest implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getPM25 p = new getPM25();
		getForest p1 = new getForest();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("pm")){
			reciver.type = "pm2.5";
			reciver.data_recieved = p.getPM25(country, year1, year2);
		}else if (name.contains("forest")) {
			reciver.type = "Forest area";
			reciver.data_recieved = p1.getForest(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean test_pmForest() {
	Data test =new Data();
	pm25Forest pop = new pm25Forest();
	selection s = new selection();
	s.year1 = 2000;
	s.year2 = 2001;
	s.country = "can";
	
		try {
			test =pop.read_data(s, "forest");
			
		} catch (nameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!test.data_recieved.isEmpty() && test.type.equals("Forest area")) {
			System.out.println("pm2.5Forest test passed");
			return true;
		}
		else {
			System.out.println("pm2.5Forest test failed");
			return false;
		}
		}
	/*public static void main(String[] args) {
		test_pmForest();
	}*/
}