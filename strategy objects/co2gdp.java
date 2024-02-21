public class co2gdp implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getCO2 p = new getCO2();
		getGDP p1 = new getGDP();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("co2")){
			reciver.type = "co2";
			reciver.data_recieved = p.getCO2(country, year1, year2);
		}else if (name.contains("gdp")) {
			reciver.type = "gdp";
			reciver.data_recieved = p1.getGDP(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean test_co2gdp() {
	Data test =new Data();
	co2gdp pop = new co2gdp();
	selection s = new selection();
	s.year1 = 2000;
	s.year2 = 2001;
	s.country = "can";
	
		try {
			test =pop.read_data(s, "gdp");
			
		} catch (nameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!test.data_recieved.isEmpty() && test.type.equals("gdp")) {
			System.out.println("co2gdp test passed\n");
			return true;
		}
		else {
			System.out.println("co2gdp test failed");
			return false;
		}
		}
	
	/*public static void main(String[] args) {
		test_co2gdp();
	}*/
	}