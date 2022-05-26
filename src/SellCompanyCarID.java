
public class SellCompanyCarID {
	String co_address;
	int id;
	String carName;
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SellCompanyCarID(String co_address, int car_idS,String carName) {
		this.co_address = co_address;
		this.id = car_idS;
		this.carName = carName;
		
	}

	public String getCo_address() {
		return co_address;
	}

	public void setCo_address(String co_address) {
		this.co_address = co_address;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}


	

}
