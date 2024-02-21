
public class Forestgdp implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getForest p = new getForest();
		getGDP p1 = new getGDP();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("forest")){
			reciver.type = "Forest area";
			reciver.data_recieved = p.getForest(country, year1, year2);
		}else if (name.contains("gdp")) {
			reciver.type = "gdp";
			reciver.data_recieved = p1.getGDP(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean Forestgdp() {
	Data test =new Data();
	Forestgdp pop = new Forestgdp();
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
			System.out.println("Forestgdp test passed\n");
			return true;
		}
		else {
			System.out.println("Forestgdp test failed");
			return false;
		}
		}
	
	/*public static void main(String[] args) {
		test_co2gdp();
	}*/
	}