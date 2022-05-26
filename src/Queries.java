
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class Queries {
	private static String dbURL;
	private static String dbUsername = "root";
	private static String dbPassword = "";
	private static String URL = "127.0.0.1";
	private static String port = "3306";
	private static String dbName = "test3";
	private static Connection con;

	public static void connectDB() throws ClassNotFoundException, SQLException {

		dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
		Properties p = new Properties();
		p.setProperty("user", dbUsername);
		p.setProperty("password", dbPassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection(dbURL, p);

	}

	public static void ExecuteStatement(String SQL) throws SQLException {

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(SQL);
			stmt.close();

		} catch (SQLException s) {

		}

	}

	public static Users getUserForSignIn(String password, String user_name)
			throws ClassNotFoundException, SQLException {
		connectDB();

		String SQL = "select * from  Useres s where s.user_name='" + user_name + "' and s.pass='" + password + "';";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Users s = null;

		while (rs.next()) {

			s = new Users(user_name, rs.getString(2), password, rs.getString(4));

		}
		rs.close();
		stmt.close();

		con.close();
		return s;

	}

	public static ObservableList<Manager> getManger() throws ClassNotFoundException, SQLException {
		ObservableList<Manager> Manager_List = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		SQL = "select * from ManagerM";
		Statement stmt = con.createStatement();
		ResultSet r = stmt.executeQuery(SQL);
		while (r.next()) {

			Manager emp = new Manager(r.getInt(7), r.getString(3), r.getString(1), r.getDouble(2), r.getString(5),
					r.getString(4), r.getString(6));

			Manager_List.add(emp);
		}
		r.close();
		stmt.close();
		con.close();
		return Manager_List;
	}

	public static void insertUser(Users user) {
		try {
			connectDB();

			ExecuteStatement("Insert into Useres (user_name,phone,pass,email) values('" + user.getUser_name() + "','"
					+ user.getPhone() + "','" + user.getPass() + "','" + user.getEmail() + "');");

			con.close();

		} catch (ClassNotFoundException | SQLException e) {
		}

	}

	public static Users getUserForCheck(String user_name) throws ClassNotFoundException, SQLException {
		connectDB();

		String SQL = "select * from  Useres s where s.user_name='" + user_name + "';";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		Users s = null;

		while (rs.next()) {

			s = new Users(user_name, rs.getString(2), rs.getString(3), rs.getString(4));

		}
		rs.close();
		stmt.close();

		con.close();
		return s;

	}

	

	
	public static ObservableList<RentalCompany> getRentalCompany() throws ClassNotFoundException, SQLException {
		ObservableList<RentalCompany> tmpOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		// SQL = "select co_address from Rental";
		SQL = "select * from Rental";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {


			RentalCompany tmp = new RentalCompany(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

			tmpOb.add(tmp);

		}

		rs.close();
		stmt.close();

		con.close();
		return tmpOb;

	}

	public static ObservableList<SellCompany> getSellCompany() throws ClassNotFoundException, SQLException {
		ObservableList<SellCompany> tmpOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		// SQL = "select co_address from Rental";
		SQL = "select * from Sell";

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {


			SellCompany tmp = new SellCompany(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

			tmpOb.add(tmp);

		}

		
		rs.close();
		stmt.close();

		con.close();
		return tmpOb;

	}

	public static ObservableList<SellCompany> get_Specific_SellCompany() throws ClassNotFoundException, SQLException {
		ObservableList<SellCompany> tmpOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select co_address from Sell";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			SellCompany sell = new SellCompany(rs.getString(1));
			tmpOb.add(sell);
		}

		rs.close();
		stmt.close();

		con.close();
		return tmpOb;

	}

	public static ObservableList<RentalCompany> get_Specific_RentalCompany()
			throws ClassNotFoundException, SQLException {
		ObservableList<RentalCompany> tmpOb = FXCollections.observableArrayList();
		String SQL;

		connectDB();

		SQL = "select co_address from Rental";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			RentalCompany rent = new RentalCompany(rs.getString(1));
			tmpOb.add(rent);
		}

		rs.close();
		stmt.close();

		con.close();
		return tmpOb;

	}

	public static void insertData(SellCompany rc) throws ClassNotFoundException, SQLException {

		try {
			
			connectDB();
			ExecuteStatement("Insert into Sell (co_address, co_name,phone_num, email) " + "values(" + "'"
					+ rc.getCo_address() + "'" + ",'" + rc.getCo_name() + "'," + rc.getPhone_num() + "," + "'"
					+ rc.getEmail() + "'" + ")");
			con.close();
			// System.out.println("Connection closed" + data.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void updateNameSell(String co_address, String name) {

		try {
			System.out.println("update  Sell set co_name = '" + "'" + name + "'" + "' where co_address = " + "'"
					+ co_address + "'");
			connectDB();
			ExecuteStatement(
					"update  Sell set co_name = '" + name + "' where co_address = " + "'" + co_address + "'" + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updateEmaillSell(String co_address, String email) {

		try {
			System.out.println("update  Sell set email = '" + "'" + email + "'" + "' where co_address = " + "'"
					+ co_address + "'");
			connectDB();
			ExecuteStatement(
					"update  Sell set email = '" + email + "' where co_address = " + "'" + co_address + "'" + ";");

			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updatePhoneSell(String co_address, int phone_num) {

		try {
			System.out.println(
					"update  Sell set phone_num = " + phone_num + " where co_address = " + "'" + co_address + "'");
			connectDB();
			ExecuteStatement("update  Sell set phone_num = " + phone_num + " where co_address = " + "'" + co_address
					+ "'" + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static void insertDataRentComp(RentalCompany rc) throws ClassNotFoundException, SQLException {

		try {
			System.out.println("Insert into Rental (co_address, co_name,phone_num, email) " + "values(" + "'"
					+ rc.getCo_address() + "'" + ",'" + rc.getCo_name() + "'," + rc.getPhone_num() + "," + "'"
					+ rc.getEmail() + "'" + ")");

			connectDB();
			ExecuteStatement("Insert into Rental (co_address, co_name,phone_num, email) " + "values(" + "'"
					+ rc.getCo_address() + "'" + ",'" + rc.getCo_name() + "','" + rc.getPhone_num() + "'," + "'"
					+ rc.getEmail() + "'" + ")");
			con.close();
			// System.out.println("Connection closed" + data.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void insertDataSellComp(SellCompany rc) throws ClassNotFoundException, SQLException {

		try {

			connectDB();
			ExecuteStatement("Insert into Sell (co_address, co_name,phone_num, email) " + "values(" + "'"
					+ rc.getCo_address() + "'" + ",'" + rc.getCo_name() + "','" + rc.getPhone_num() + "'," + "'"
					+ rc.getEmail() + "'" + ")");
			con.close();
			// System.out.println("Connection closed" + data.size());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

public static void insertDataManager(Manager rc)   throws ClassNotFoundException, SQLException {
		
		try {
				
				connectDB();
				ExecuteStatement(" Insert into Employees(emp_id) values("+rc.employee_id+")");
				ExecuteStatement("Insert into ManagerM (Mng_name, pass,Salary,phone_num, email,Uneversity_degree,mng_id) values(" +"'"+rc.getName()+ "'"+ ",'"+rc.getPass()+"',"+ rc.getSalary() +","+ "'"+ rc.getPhone_number()+ "','"+rc.getEmail()+ "','"+rc.getUniversity_degree()+"',"+rc.getEmployee_id()+");");
				con.close();
				//System.out.println("Connection closed" + data.size());
				
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}
public static void insertDataAdviser(Advisor rc)   throws ClassNotFoundException, SQLException {
	
	try {
			
			connectDB();
			ExecuteStatement(" Insert into Employees(emp_id) values("+rc.employee_id+")");
			ExecuteStatement("Insert into Advisor (advs_name, pass,Salary,phone_num, email,Uneversity_degree,advs_id) values(" +"'"+rc.getName()+ "'"+ ",'"+ rc.getPass()+"',"+ rc.getSalary() +","+ "'"+ rc.getPhone_number()+ "','"+rc.getEmail()+ "','"+rc.getUniversity_degree()+"',"+rc.getEmployee_id()+");");
			con.close();
			//System.out.println("Connection closed" + data.size());
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
public static void insertDataAccountant(Accountant rc)   throws ClassNotFoundException, SQLException {
	
	try {
			
			connectDB();
			ExecuteStatement(" Insert into Employees(emp_id) values("+rc.employee_id+")");
			ExecuteStatement("Insert into Accountant (Acc_name, pass,Salary,phone_num, email,Uneversity_degree,acc_id) values(" +"'"+rc.getName()+ "'"+ ",'"+ rc.getPass()+"',"+ rc.getSalary() +","+ "'"+ rc.getPhone_number()+ "','"+rc.getEmail()+ "','"+rc.getUniversity_degree()+"',"+rc.getEmployee_id()+");");
			con.close();
			//System.out.println("Connection closed" + data.size());
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}



	public static void updateNameRent(String co_address, String name) {

		try {
			System.out.println("update  Rental set co_name = '" + "'" + name + "'" + "' where co_address = " + "'"
					+ co_address + "'");
			connectDB();
			ExecuteStatement(
					"update  Rental set co_name = '" + name + "' where co_address = " + "'" + co_address + "'" + ";");
			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updateEmailRent(String co_address, String email) {

		try {
			System.out.println("update  Rental set email = '" + "'" + email + "'" + "' where co_address = " + "'"
					+ co_address + "'");
			connectDB();
			ExecuteStatement(
					"update  Rental set email = '" + email + "' where co_address = " + "'" + co_address + "'" + ";");

			con.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void updatePhoneRent(String co_address, String phone_num) {

		try {
					connectDB();
			ExecuteStatement("update  Rental set phone_num = '" + phone_num + "' where co_address = " + "'" + co_address
					+ "'" + ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	//----------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	public static void updatePhoneManager(int id, String phone_num) {

		try {
					connectDB();
			ExecuteStatement("update  ManagerM set phone_num = '" + phone_num + "' where mng_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmailManager(int id, String email) {

		try {
					connectDB();
			ExecuteStatement("update  ManagerM set email = '" + email + "' where mng_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSalaryManager(int id, double salary) {

		try {
					connectDB();
			ExecuteStatement("update  ManagerM set Salary = " + salary + " where mng_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateNameManager(int id, String name) {

		try {
					connectDB();
			ExecuteStatement("update  ManagerM set Mng_name = '" + name + "' where mng_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateUDManager(int id, String UD) {

		try {
					connectDB();
			ExecuteStatement("update  ManagerM set Uneversity_degree = '" + UD + "' where mng_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//----------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	public static void updatePhoneAdvisor(int id, String phone_num) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set phone_num = '" + phone_num + "' where advs_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmailAdvisor(int id, String email) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set email = '" + email + "' where advs_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSalaryAdvisor(int id, double salary) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set Salary = " + salary + " where advs_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateNameAdvisor(int id, String name) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set advs_name = '" + name + "' where advs_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateUDAdvisor(int id, String UD) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set Uneversity_degree = '" + UD + "' where advs_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//----------------------------------------------------------------
	
	
	
	public static void updatePhoneAccountant(int id, String phone_num) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set phone_num = '" + phone_num + "' where acc_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateEmailAccountant(int id, String email) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set email = '" + email + "' where acc_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSalaryAccountant(int id, double salary) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set Salary = " + salary + " where acc_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateNameAccountant(int id, String name) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set Acc_name = '" + name + "' where acc_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateUDAccountant(int id, String UD) {

		try {
					connectDB();
			ExecuteStatement("update  Advisor set Uneversity_degree = '" + UD + "' where acc_id = " + id+ ";");
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//---------------------------------------------------------------------------------------
	
	
	
	
	
	public static ObservableList<RentalCars> getRentalCars() throws ClassNotFoundException, SQLException {
		ObservableList<RentalCars> Rcars = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		// SQL = "select * from CarRH where co_address = " + "'" + see + "'";
		SQL = "select * from CarRH ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			RentalCars cars = new RentalCars(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),
					rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			Rcars.add(cars);
		}
		rs.close();
		stmt.close();
		con.close();
		return Rcars;
	}

	public static ObservableList<SellCars> getSellCars() throws ClassNotFoundException, SQLException {
		ObservableList<SellCars> Rcars = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		// SQL = "select * from CarSH where co_address = " + "'" + see + "'";
		SQL = "select * from CarSH";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			SellCars cars = new SellCars(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),
					rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			Rcars.add(cars);
		}
		rs.close();
		stmt.close();
		con.close();
		return Rcars;
	}
	
	
	
	
	
	
	
	public static ArrayList<SellCars> getSellCars1() throws ClassNotFoundException, SQLException {
		ArrayList<SellCars> Rcars =new ArrayList<>();
		String SQL;
		connectDB();
		// SQL = "select * from CarSH where co_address = " + "'" + see + "'";
		SQL = "select * from CarSH";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			SellCars cars = new SellCars(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),
					rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			Rcars.add(cars);
		}
		rs.close();
		stmt.close();
		con.close();
		return Rcars;
	}
	
	
	public static ArrayList<RentalCars> getRentalCars1() throws ClassNotFoundException, SQLException {
		ArrayList<RentalCars> Rcars = new ArrayList<>();
		String SQL;
		connectDB();
		// SQL = "select * from CarRH where co_address = " + "'" + see + "'";
		SQL = "select * from CarRH ";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			RentalCars cars = new RentalCars(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getLong(4),
					rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			Rcars.add(cars);
		}
		rs.close();
		stmt.close();
		con.close();
		return Rcars;
	}
	
	public static ObservableList<Advisor> getAdvisor() throws ClassNotFoundException, SQLException {
		ObservableList<Advisor> Advisor_List = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		SQL = "select * from Advisor";
		Statement stmt = con.createStatement();
		ResultSet r = stmt.executeQuery(SQL);
		while (r.next()) {
			String phone = "" + r.getInt(4);
			// double degree = Double.parseDouble(r.getString(6));

			Advisor emp = new Advisor(r.getInt(7), r.getString(3), r.getString(1), r.getDouble(2), r.getString(5),
					r.getString(4), r.getString(6));

			Advisor_List.add(emp);
		}
		r.close();
		stmt.close();
		con.close();
		return Advisor_List;
	}
	
	public static ObservableList<Accountant> getAccountant() throws ClassNotFoundException, SQLException {
		ObservableList<Accountant> Accountant_List = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		SQL = "select * from Accountant";
		Statement stmt = con.createStatement();
		ResultSet r = stmt.executeQuery(SQL);
		while (r.next()) {

			Accountant emp = new Accountant(r.getInt(7), r.getString(3), r.getString(1), r.getDouble(2), r.getString(5),
					r.getString(4), r.getString(6));

			Accountant_List.add(emp);
		}
		r.close();
		stmt.close();
		con.close();
		return Accountant_List;
	}
	
	public static ObservableList<Users> getUsers() throws ClassNotFoundException, SQLException {
		ObservableList<Users> Users_List = FXCollections.observableArrayList();
		String SQL;
		connectDB();
		SQL = "select * from Useres";
		Statement stmt = con.createStatement();
		ResultSet r = stmt.executeQuery(SQL);
		while (r.next()) {
			
			Users emp = new Users(r.getString(1), r.getString(2), r.getString(3), r.getString(4));

			Users_List.add(emp);
		}
		r.close();
		stmt.close();
		con.close();
		return Users_List;
	}
	
	

	
		public static void insertDataSell(SellCars rc1) throws ClassNotFoundException, SQLException {

			try {
				connectDB();
				ExecuteStatement("Insert into Reservation (Rent_Sell,car_idS) " + "values(" + "'" + rc1.getCarName() + "',"   
						+ rc1.getCarId()  + ")");
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		

		public static void insertDataRent(RentalCars rc1) throws ClassNotFoundException, SQLException {

			try {
				connectDB();
				ExecuteStatement("Insert into Reservation (Rent_Sell,car_idR) " + "values(" + "'" + rc1.getCar_name() + "',"
						+ rc1.getCar_id()  + ")");
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	
	
		public static Manager managerlogin( String id, String pass) 
				throws ClassNotFoundException, SQLException {
				connectDB();
				int manageid= Integer.parseInt(id.trim());
				
				
		System.out.println(manageid+" "+pass);
				String SQL = "select * from  ManagerM s where s.pass='" + pass + "' and s.mng_id=" + manageid + ";";

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				Manager s = null;

				while (rs.next()) {

					s = new Manager(rs.getInt(7),rs.getString(3),rs.getString(1),rs.getDouble(2),rs.getString(5),rs.getString(4),rs.getString(6));

				}
				rs.close();
				stmt.close();

				con.close();
				return s;
			}
			public static Advisor Advisorlogin( String id, String pass) 
					throws ClassNotFoundException, SQLException {
					connectDB();
					int manageid= Integer.parseInt(id.trim());
					
					
			System.out.println(manageid+" "+pass);
					String SQL = "select * from  Advisor s where s.pass='" + pass + "' and s.advs_id=" + manageid + ";";

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);
					Advisor s = null;

					while (rs.next()) {
					
						s = new Advisor(rs.getInt(7),rs.getString(1),rs.getString(3),rs.getDouble(2),rs.getString(5),rs.getString(4),rs.getString(6));

					}
					rs.close();
					stmt.close();

					con.close();
					return s;
				}
			public static Accountant Accountantlogin( String id, String pass) 
					throws ClassNotFoundException, SQLException {
					connectDB();
					int manageid= Integer.parseInt(id.trim());
					
					
			System.out.println(manageid+" "+pass);
					String SQL = "select * from Accountant s where s.pass='" + pass + "' and s.acc_id=" + manageid + ";";

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);
					Accountant s = null;

					while (rs.next()) {
					
						s = new Accountant(rs.getInt(7),rs.getString(1),rs.getString(3),rs.getDouble(2),rs.getString(5),rs.getString(4),rs.getString(6));

					}
					rs.close();
					stmt.close();

					con.close();
					return s;
				}
	
			public static Reservation CheckReservRent(RentalCars node)
					throws ClassNotFoundException, SQLException {
				connectDB();

				String SQL = "select * from  Reservation s where s.Rent_Sell='" +node.car_name+ "' and s.car_idR=" +node.car_id+ ";";

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				Reservation s = null;

				while (rs.next()) {

					s = new Reservation(node.car_name, node.car_id);

				}
				rs.close();
				stmt.close();

				con.close();
				return s;

			}
	
			public static Reservation CheckReservSell(SellCars node)
					throws ClassNotFoundException, SQLException {
				connectDB();

				String SQL = "select * from  Reservation s where s.Rent_Sell='" +node.CarName+ "' and s.car_idS=" +node.CarId+ ";";

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				Reservation s = null;

				while (rs.next()) {

					s = new Reservation(node.CarId,node.CarName);

				}
				rs.close();
				stmt.close();

				con.close();
				return s;

			}
			public static ObservableList<RentalCompanyCarID>  get_ReservationRental() throws ClassNotFoundException , SQLException{
				ObservableList<RentalCompanyCarID> cars = FXCollections.observableArrayList();
				String SQL;

				connectDB();

				SQL = "select co_address, car_id , car_name from CarRH  R , Reservation s where R.car_id = s.car_idR  ;"  ;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);

				while (rs.next()) {
					RentalCompanyCarID coaddres = new RentalCompanyCarID(rs.getString(1),rs.getInt(2),rs.getString(3));
					cars.add(coaddres);
				}

				rs.close();
				stmt.close();

				con.close();
				return cars ;

			
			}
		  
		  	public static ObservableList<SellCompanyCarID>  get_ReservationSell() throws ClassNotFoundException , SQLException{
				ObservableList<SellCompanyCarID> cars = FXCollections.observableArrayList();
				String SQL;

				connectDB();

				SQL = "select co_address, car_id , car_name from CarSH  R , Reservation s where R.car_id = s.car_idS  ;"  ;
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);

				while (rs.next()) {
					SellCompanyCarID coaddres = new SellCompanyCarID(rs.getString(1),rs.getInt(2),rs.getString(3));
					cars.add(coaddres);
				}

				rs.close();
				stmt.close();

				con.close();
				return cars ;

			
			}
			
		  	
		  	
		  	
		  	public static void insertData(Manager rc)   throws ClassNotFoundException, SQLException {
				
				try {
						System.out.println("Insert into ManagerM (Employee_id, name,phone_num, email) "
								+ "values(" +"'"+rc.getEmployee_id()+ "'"+ ",'"+rc.getName()+"',"+ rc.getPhone_number() +","+ "'"+ rc.getEmail()+ "'"+ ")");
						
						connectDB();
						ExecuteStatement("Insert into Manger (Employee_id, name,phone_num, email) "
								+ "values(" +"'"+rc.getEmployee_id()+ "'"+ ",'"+rc.getName()+"',"+ rc.getPhone_number() +","+ "'"+ rc.getEmail()+ "'"+ ")");
						con.close();
						//System.out.println("Connection closed" + data.size());
						
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
				}
		  	
		  	
		  	
		  	public static int getCommisionRent() throws ClassNotFoundException, SQLException{
		  		String SQL;
		  		
					
					connectDB();
					SQL ="select sum(commision*price_day) as commision from CarRH s , Reservation r where  s.car_id = r.car_idR ;";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);

					int tmp =0;
					
					while (rs.next()) {
						tmp=rs.getInt(1);
					}

					rs.close();
					stmt.close();

					con.close();
					return tmp ;
		  		
		  		
		  		
		  	}
		  	
		  	
		  	
		  	public static int getCommisionSell() throws ClassNotFoundException, SQLException{
		  		String SQL;
		  		
					
					connectDB();
					SQL ="select sum(commision*price) as commision from CarSH s , Reservation r where  s.car_id = r.car_idS ;";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);

					int tmp =0;
					
					while (rs.next()) {
						tmp=rs.getInt(1);
					}

					rs.close();
					stmt.close();

					con.close();
					return tmp ;
		  		
		  		
		  		
		  	}
		  	
		  	
		  	
		  	
		  	
			
			

}
