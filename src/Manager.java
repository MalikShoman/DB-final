
public class Manager extends employee {
private String pass ;
private String Name;
private double Salary;
private String Email;
private String phone_number;
private String university_degree;


public Manager(int employee_id, String pass, String name, double salary, String email, String phone_number,
		String unversty_degree) {
	super(employee_id);
	this.pass = pass;
	Name = name;
	Salary = salary;
	Email = email;
	this.phone_number = phone_number;
	this.university_degree = unversty_degree;
}






public String getPass() {
	return pass;
}






public void setPass(String pass) {
	this.pass = pass;
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






@Override
public String toString() {
	return "Manger [" + (pass != null ? "pass=" + pass + ", " : "") + (Name != null ? "Name=" + Name + ", " : "")
			+ "Salary=" + Salary + ", " + (Email != null ? "Email=" + Email + ", " : "")
			+ (phone_number != null ? "phone_number=" + phone_number + ", " : "")
			+ (university_degree != null ? "unversty_degree=" + university_degree : "") + "]";
}








}