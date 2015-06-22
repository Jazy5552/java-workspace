package jazy.programming2.garage;


public class garage {

	final String gname;
	final float miles = 75;
	final float gallons = 30;
	int cars = 0;
	
	public garage(String name){
		gname = name;
		System.out.printf("%nGarage: %s has been created", gname);
	}
	
	public void addCars(int amount){
		String polarity = "added";
		if (amount<0) polarity = "removed";
		
		cars += amount;
		if (cars>100) System.out.printf("%n%d cars were not added becuase %s is full!",cars-100,gname);
		else if (cars<0) {
			System.out.printf("%nThere is negative cars in %s???%nSet to 0", gname);
			cars=0;
		}
		else System.out.printf("%n%d cars were %s", cars, polarity);
	}
	
	public void showOccupancy(){
		if (cars<=25) System.out.printf("%n%s occupancy: below minimum",gname);
		else if ((cars>25)&&(cars<100)) System.out.printf("%n%s occupancy: normal load",gname);
		else if (cars>=100) {
			System.out.printf("%n%s occupancy: FULL",gname);
		}
	}
	
	public void showGallons(){
		if (cars>100) System.out.printf("%nTotal gallons use by cars in %s: %.1f",gname,(100*gallons));
		else System.out.printf("%nTotal gallons use by cars in %s: %.1f",gname,(cars*gallons));
	}
	
	public String getName(){
		return gname;
	}
	
	public int getCars(){
		return cars;
	}
	
	public void showMiles(){
		if (cars>100) System.out.printf("%nAvergae number of all miles for all cars in %s: %.1f",gname,(100*miles));
		else System.out.printf("%nAvergae number of miles for all cars in %s: %.1f",gname,(cars*miles));
	}

}
