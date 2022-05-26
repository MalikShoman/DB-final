
public class Manger extends employee {
	private String pass ;
private String Name;
private double Salary;
private String Email;
private String phone_number;
private double unversty_degree;


public Manger(int employee_id, String name, String pass, double salary, String email, String phone_number,
		double unversty_degree) {
	super(employee_id);
	Name = name;
	this.pass = pass;
	Salary = salary;
	Email = email;
	this.phone_number = phone_number;
	this.unversty_degree = unversty_degree;
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

public double getUnversty_degree() {
	return unversty_degree;
}

public void setUnversty_degree(double unversty_degree) {
	this.unversty_degree = unversty_degree;
}




}
