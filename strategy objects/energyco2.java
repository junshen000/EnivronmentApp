public class energyco2 implements strategy {
public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getCO2 p = new getCO2();
		getEnergy p1 = new getEnergy();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("co2")){
			reciver.type = "co2";
			reciver.data_recieved = p.getCO2(country, year1, year2);
		}else if (name.contains("energy")) {
			reciver.type = "Energy use";
			reciver.data_recieved = p1.getEnergy(country, year1, year2);
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	public static boolean test_energyco2() {
	Data test =new Data();
	energyco2 pop = new energyco2();
	selection s = new selection();
	s.year1 = 2000;
	s.year2 = 2001;
	s.country = "can";
	
		try {
			test =pop.read_data(s, "energy");
			
		} catch (nameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!test.data_recieved.isEmpty() && test.type.equals("Energy use")) {
			System.out.println("co2energy test passed\n");
			return true;
		}
		else {
			System.out.println("co2energy test failed");
			return false;
		}
		}
	
	public static void main(String[] args) {
		test_energyco2();
	}
	}