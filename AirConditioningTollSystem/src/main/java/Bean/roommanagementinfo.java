package Bean;

public class roommanagementinfo {
	private int ID;
	private int LouDong;
	private int UnitNumber;
	private int RoomNumber;
	private double Balance;
//	private String Address;
//	private String Major;
//	private String ClassID;
	@Override
	public String toString() {
		return "StudentInfo [ID=" + ID + ", LouDong=" + LouDong + ", UnitNumber=" + UnitNumber + ", RoomNumber=" + RoomNumber
				+ ", Balance=" + Balance +  "]";
	}
	public roommanagementinfo() {
		
	}
	public roommanagementinfo(int ID,int LouDong,int UnitNumber,int RoomNumber,double Balance) {
		super();
		this.ID=ID;
		this.LouDong=LouDong;
		this.UnitNumber=UnitNumber;
		this.RoomNumber=RoomNumber;
		this.Balance=Balance;
//		this.Address=Address;
//		this.Major=Major;
//		this.ClassID=ClassID;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getLouDong() {
		return LouDong;
	}
	public void setLouDong(int louDong) {
		LouDong = louDong;
	}
	public int getUnitNumber() {
		return UnitNumber;
	}
	public void setUnitNumber(int unitNumber) {
		UnitNumber = unitNumber;
	}
	public int getRoomNumber() {
		return RoomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		RoomNumber = roomNumber;
	}
	public double getBalance() {
		return Balance;
	}
	public void setBalance(double balance) {
		Balance = balance;
	}
	
}
