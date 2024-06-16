package Bean;

public class StudentInfo {
	private int StudentID;
	private String Name;
	private String Password;
	private String Sex;
	private int Age;
	private String Address;
	private String Major;
	private String ClassID;
	@Override
	public String toString() {
		return "StudentInfo [StudentID=" + StudentID + ", Name=" + Name + ", Password=" + Password + ", Sex=" + Sex
				+ ", Age=" + Age + ", Address=" + Address + ", Major=" + Major + ", ClassID=" + ClassID + "]";
	}
	public StudentInfo() {
		
	}
	public StudentInfo(int StudentID,String Name,String Password,String Sex,int Age,String Address,String Major,String ClassID) {
		super();
		this.StudentID=StudentID;
		this.Name=Name;
		this.Password=Password;
		this.Sex=Sex;
		this.Age=Age;
		this.Address=Address;
		this.Major=Major;
		this.ClassID=ClassID;
	}
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getMajor() {
		return Major;
	}
	public void setMajor(String major) {
		Major = major;
	}
	public void setstuClass(String ClassID) {
		ClassID = ClassID;
	}
	public String getClassID() {
		return ClassID;
	}
	
	
}
