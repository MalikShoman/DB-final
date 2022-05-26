
public class SellCars {
	 String  CarName ;
     String Insurance ;
     boolean Delivery_availavility   = false ;
     long millage ;
     int CarYear ; 
     String CarType ;
     int commision ;
     int CarId  ; //forigen key ;
     int price ;
     
	public SellCars(String carName, String insurance, boolean delivery_availavility, long millage, int carYear,
			String carType, int commision, int carId, int price) {
		CarName = carName;
		Insurance = insurance;
		Delivery_availavility = delivery_availavility;
		this.millage = millage;
		CarYear = carYear;
		CarType = carType;
		this.commision = commision;
		CarId = carId;
		this.price = price;
	}

	public String getCarName() {
		return CarName;
	}

	public void setCarName(String carName) {
		CarName = carName;
	}

	public String getInsurance() {
		return Insurance;
	}

	public void setInsurance(String insurance) {
		Insurance = insurance;
	}

	public boolean isDelivery_availavility() {
		return Delivery_availavility;
	}

	public void setDelivery_availavility(boolean delivery_availavility) {
		Delivery_availavility = delivery_availavility;
	}

	public long getMillage() {
		return millage;
	}

	public void setMillage(long millage) {
		this.millage = millage;
	}

	public int getCarYear() {
		return CarYear;
	}

	public void setCarYear(int carYear) {
		CarYear = carYear;
	}

	public String getCarType() {
		return CarType;
	}

	public void setCarType(String carType) {
		CarType = carType;
	}

	public int getCommision() {
		return commision;
	}

	public void setCommision(int commision) {
		this.commision = commision;
	}

	public int getCarId() {
		return CarId;
	}

	public void setCarId(int carId) {
		CarId = carId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SellCars [CarName=" + CarName + ", Insurance=" + Insurance + ", Delivery_availavility="
				+ Delivery_availavility + ", millage=" + millage + ", CarYear=" + CarYear + ", CarType=" + CarType
				+ ", commision=" + commision + ", CarId=" + CarId + ", price=" + price + "]";
	}
	
	
	

}
