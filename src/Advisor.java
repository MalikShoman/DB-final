
public class Advisor extends employee{

	private String Name;
	private String pass ;
	private double Salary;
	private String Email;
	private String phone_number;
	private String university_degree;
	
	
	public Advisor(int employee_id, String name, String pass, double salary, String email, String phone_number,
			String university_degree) {
		super(employee_id);
		Name = name;
		this.pass = pass;
		Salary = salary;
		Email = email;
		this.phone_number = phone_number;
		this.university_degree = university_degree;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getUniversity_degree() {
		return university_degree;
	}
	public void setUniversity_degree(String unversty_degree) {
		this.university_degree = unversty_degree;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Advisor [" + (Name != null ? "Name=" + Name + ", " : "") + (pass != null ? "pass=" + pass + ", " : "")
				+ "Salary=" + Salary + ", " + (Email != null ? "Email=" + Email + ", " : "")
				+ (phone_number != null ? "phone_number=" + phone_number + ", " : "") + "university_degree="
				+ university_degree + "]";
	}
	
	
}
