public class co2pm25 implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getCO2 p = new getCO2();
		getPM25 p1 = new getPM25();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("co2")){
			reciver.type = "co2";
			reciver.data_recieved = p.getCO2(country, year1, year2);
		}else if (name.contains("pm")) {
			reciver.type = "pm2.5";
			reciver.data_recieved = p1.getPM25(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean test_co2pm25() {
	Data test =new Data();
	co2pm25 pop = new co2pm25();
	selection s = new selection();
	s.year1 = 2000;
	s.year2 = 2001;
	s.country = "can";
	
		try {
			test =pop.read_data(s, "co2");
			
		} catch (nameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!test.data_recieved.isEmpty() && test.type.equals("co2")) {
			System.out.println("co2pm2.5 test passed\n");
			return true;
		}
		else {
			System.out.println("co2pm2.5 test failed");
			return false;
		}
		}
	
	/*public static void main(String[] args) {
		test_co2pm25();
	}*/
	}