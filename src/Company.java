
public class Company {
	private String SupportEmail ;
	private String CompanyAdress ;
	private String CompanyName ;
	private int   CompanyNumber ;
	
	public Company(String supportEmail, String companyAdress, String companyName, int companyNumber) {
		SupportEmail = supportEmail;
		CompanyAdress = companyAdress;
		CompanyName = companyName;
		CompanyNumber = companyNumber;
	}

	public String getSupportEmail() {
		return SupportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		SupportEmail = supportEmail;
	}

	public String getCompanyAdress() {
		return CompanyAdress;
	}

	public void setCompanyAdress(String companyAdress) {
		CompanyAdress = companyAdress;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public int getCompanyNumber() {
		return CompanyNumber;
	}

	public void setCompanyNumber(int companyNumber) {
		CompanyNumber = companyNumber;
	}
	
	
	

}
