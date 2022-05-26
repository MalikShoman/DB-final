
public class RentalCompanyCarID {
	String co_address;
	int car_idR;
	String carName;

	public RentalCompanyCarID(String co_address, int car_idR,String carName) {
		this.co_address = co_address;
		this.car_idR = car_idR;
		this.carName = carName;
	}

	public String getCo_address() {
		return co_address;
	}

	public void setCo_address(String co_address) {
		this.co_address = co_address;
	}

	public int getCar_idR() {
		return car_idR;
	}

	public void setCar_idR(int car_id) {
		this.car_idR = car_id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
	

}
