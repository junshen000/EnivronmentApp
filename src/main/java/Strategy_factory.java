
public class Strategy_factory {
       // the function of a strategy factory : deciding which strategy to use
	  //factory design pattern 
	
	public strategy factoryMethod (selection selection) throws nameException {	
	      if(selection.getAnalysis().equalsIgnoreCase("Population vs Pm2.5")){
	         return new poppm25();
	         
	      }else if(selection.getAnalysis().equalsIgnoreCase("Co2 vs Forest Area")){
	         return new co2Forest() ;
	         
	      } else if(selection.getAnalysis().equalsIgnoreCase("pop vs gdp")){
	         return new popgdp();
	      }
	      else if(selection.getAnalysis().equalsIgnoreCase("Co2 vs GDP")){
		         return new co2gdp();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("Co2 vs Pm2.5")){
		         return new co2pm25();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("Co2 vs Energy")){
		         return new energyco2();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("Forest Area vs GDP")){
		         return new Forestgdp();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("Pm2.5 vs Forest Area")){
		         return new pm25Forest();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("pop vs pm2.5")){
		         return new poppm25();
		  }
	      else if(selection.getAnalysis().equalsIgnoreCase("Forest Area")){
		         return new forestArea();
		  }
	      
	      else {
	    	  throw new nameException("wrong name for the analysis, cannnot decide which strategy to use");
	      }

	   }
	
	/*public static void main(String[] args) {
		test_factory();
	}*/
	
	}

