
public class co2Forestgdp implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getCO2 p = new getCO2();
		getForest p1 = new getForest();
		getGDP p2 = new getGDP();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("co2")){
			reciver.type = "co2";
			reciver.data_recieved = p.getCO2(country, year1, year2);
		}else if (name.contains("forest")) {
			reciver.type = "Forest area";
			reciver.data_recieved = p1.getForest(country, year1, year2);
		}else if (name.contains("gdp")) {
			reciver.type = "gdp";
			reciver.data_recieved = p2.getGDP(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean test_co2Forestgdp() {
	Data test =new Data();
	co2Forestgdp pop = new co2Forestgdp();
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
			System.out.println("co2Forestgdp test passed\n");
			return true;
		}
		else {
			System.out.println("co2Forestgdp test failed");
			return false;
		}
		}
	public static void main(String[] args) {
		test_co2Forestgdp();
	}
}
