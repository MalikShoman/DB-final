
public class Reservation {
	int car_idS ;
	int car_idR;
	String Rent ;
	String Sell ;
	
	

	public Reservation(int car_idS, String sell) {
		super();
		this.car_idS = car_idS;
		Sell = sell;
	}
	
	
	

	public Reservation(String rent,int car_idR) {
		super();
		this.car_idR = car_idR;
		Rent = rent;
	}




	public int getCar_idS() {
		return car_idS;
	}

	public void setCar_idS(int car_idS) {
		this.car_idS = car_idS;
	}

	public int getCar_idR() {
		return car_idR;
	}

	public void setCar_idR(int car_idR) {
		this.car_idR = car_idR;
	}

	
	
	

}
