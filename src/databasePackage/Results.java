package databasePackage;

import javafx.beans.property.SimpleStringProperty;

public class Results {
	
	private final SimpleStringProperty rDate;
	private final SimpleStringProperty rRes;
	
	public Results(String sDate, String sRes) {
		this.rDate = new SimpleStringProperty(sDate);
		this.rRes = new SimpleStringProperty(sRes);
	}
	
	
	public String getRDate() {
		return rDate.get();
	}
	
	
	public void setrDate(String v) {
		rDate.set(v);
	}
	
	public String getRRes() {
		return rDate.get();
	}
	
	
	public void setrRes(String v) {
		rRes.set(v);
	}


}
