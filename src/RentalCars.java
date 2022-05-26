
public class RentalCars    {
	 String  car_name ;
     String Insurance ;
     boolean Delivery_availability   = false ;
     long Millage ;
     int car_year ; 
     String car_type ;
     int commision ;
     int car_id  ; //forigen key ;
     int price_day ;
	public RentalCars(String car_name, String insurance, boolean delivery_availability, long millage, int car_year,
			String car_type, int commision, int car_id, int price_day) {
		this.car_name = car_name;
		Insurance = insurance;
		Delivery_availability = delivery_availability;
		Millage = millage;
		this.car_year = car_year;
		this.car_type = car_type;
		this.commision = commision;
		this.car_id = car_id;
		this.price_day = price_day;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getInsurance() {
		return Insurance;
	}
	public void setInsurance(String insurance) {
		Insurance = insurance;
	}
	public boolean isDelivery_availability() {
		return Delivery_availability;
	}
	public void setDelivery_availability(boolean delivery_availability) {
		Delivery_availability = delivery_availability;
	}
	public long getMillage() {
		return Millage;
	}
	public void setMillage(long millage) {
		Millage = millage;
	}
	public int getCar_year() {
		return car_year;
	}
	public void setCar_year(int car_year) {
		this.car_year = car_year;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public int getCommision() {
		return commision;
	}
	public void setCommision(int commision) {
		this.commision = commision;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public int getPrice_day() {
		return price_day;
	}
	public void setPrice_day(int price_day) {
		this.price_day = price_day;
	}
     
	
	
      
      
      
      
 
}
