
public class RentalCompany {
	
	private String email ;
	private String co_address ;
	private String co_name ;
	private String   phone_num ;
	public RentalCompany(String co_address, String co_name, String phone_num,String email) {
		this.co_address = co_address;
		this.co_name = co_name;
		this.phone_num = phone_num;
		this.email = email;

	}
	public RentalCompany(String co_address) {
		this.co_address = co_address;
	

	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCo_address() {
		return co_address;
	}
	public void setCo_address(String co_address) {
		this.co_address = co_address;
	}
	public String getCo_name() {
		return co_name;
	}
	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	@Override
	public String toString() {
		return   co_address ;
	}
	
	
	

}
