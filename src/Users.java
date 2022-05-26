
public class Users {
public String user_name;
public String phone;
public String pass;
public String email;
public Users(String user_name, String phone, String pass, String email) {
	super();
	this.user_name = user_name;
	this.phone = phone;
	this.pass = pass;
	this.email = email;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return "Users [user_name=" + user_name + ", phone=" + phone + ", pass=" + pass + ", email=" + email + "]";
}


}
