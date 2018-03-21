package databasePackage;

import javafx.beans.property.SimpleStringProperty;

public class testTable {
	
	private final SimpleStringProperty r1;
	private final SimpleStringProperty r2;

	
	public testTable(String s1, String s2) {
		this.r1 = new SimpleStringProperty(s1);
		this.r2 = new SimpleStringProperty(s2);
	}
	
	
	public String getR1() {
		return r1.get();
	}
	
	public void setR1(String v) {
		r1.set(v);
	}
	
	public String getR2() {
		return r2.get();
	}
	
	public void setR2(String v) {
		r2.set(v);
	}

}
