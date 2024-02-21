
public class pm25Forest implements strategy {
public result calculate(selection selection_b) throws nameException {
	result output = new result();
	output.set_result1(read_data(selection_b, "pm2.5"));
	output.set_result2(read_data(selection_b, "forest"));
	output.setYear1(selection_b.getYearStart());
	output.setYear2(selection_b.getYearEnd());
	return output;
	}

	
	public Data read_data(selection selection_b, String name) throws nameException {
		getPM25 p = new getPM25();
		getForest p1 = new getForest();
		String country = selection_b.getCountry();
		int year1 = selection_b.getYearStart();
		int year2 = selection_b.getYearEnd();		
		Data reciver = new Data();
		if(name.contains("pm")){
			reciver.type = "pm2.5";
			reciver.set_data_recieved(p.getPM25(country, year1, year2));
			reciver.set_unit("micrograms per cubic meter");
		}else if (name.contains("f")) {
			reciver.type = "Forest area";
			reciver.set_data_recieved(p1.getForest(country, year1, year2));
			reciver.set_unit("% of land area");
		}else {
			throw new nameException("wrong name for the name for data obj ");
		}
		return reciver;
		
	}
	
	/*public static void main(String[] args) {
		test_pmForest();
	}*/
}