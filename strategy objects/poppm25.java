
public class poppm25 implements strategy {
	
	@Override
	public result calculate(selection selection_a) {
		
		return null;
	}

	@Override
	public Data read_data(selection selection_b, String name) throws nameException {
		getPop p = new getPop();
		getPM25 p1 = new getPM25();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("pop")){
			reciver.type = "pop";
			reciver.data_recieved = p.getPop(country, year1, year2);
		}else if(name.contains("pm")){
			reciver.type = "pm2.5";
			reciver.data_recieved = p1.getPM25(country, year1, year2);
		}
		else {
			throw new nameException("wrong name for the data obj");
		}
		return reciver;
		
	}
	/*public static void main(String[] args) {
		Data test =new Data();
		poppm25 pop = new poppm25();
		selection s = new selection();
		s.year1 = 2000;
		s.year2 = 2001;
		s.country = "can";
		
			try {
				test =pop.read_data(s, "pop");
				
			} catch (nameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(test.type);
			System.out.println(test.data_recieved.get(2000));
		
	}*/
	
}
