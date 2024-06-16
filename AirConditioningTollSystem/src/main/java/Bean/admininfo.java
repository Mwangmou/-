package Bean;

public class admininfo {

	
		
		private int AdminID;
		private int AdminAccount;
		private int AdminPassword;
		
		public int getAdminID() {
			return AdminID;
		}
		public void setAdminID(int adminID) {
			AdminID = adminID;
		}
		public int getAdminAccount() {
			return AdminAccount;
		}
		public void setAdminAccount(int adminAccount) {
			AdminAccount = adminAccount;
		}
		public int getAdminPassword() {
			return AdminPassword;
		}
		public void setAdminPassword(int adminPassword) {
			AdminPassword = adminPassword;
		}
		
		
		public admininfo() {
			
		}
		public admininfo(int AdminID,int AdminAccount,int AdminPassword) {
			super();
			this.AdminID=AdminID;
			this.AdminAccount=AdminAccount;
			this.AdminPassword=AdminPassword;
			
		}
		
		
		
		
		@Override
		public String toString() {
			return "admininfo [AdminID=" + AdminID + ", AdminAccount=" + AdminAccount + ", AdminPassword="
					+ AdminPassword + "]";
		}
		
	}

