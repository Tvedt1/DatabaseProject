package databasePackage;

import javafx.beans.property.SimpleStringProperty;

public class Table {
	
	private final SimpleStringProperty rID;
	private final SimpleStringProperty rDate;
	private final SimpleStringProperty rTime;
	private final SimpleStringProperty rDur;
	private final SimpleStringProperty rEx;
	private final SimpleStringProperty rShape;
	private final SimpleStringProperty rNote;
	
	
	public Table(String sID, String sDate, String sTime, String sDur, String sEx, String sShape, String sNote) {
		this.rID = new SimpleStringProperty(sID);
		this.rDate = new SimpleStringProperty(sDate);
		this.rTime = new SimpleStringProperty(sTime);
		this.rDur = new SimpleStringProperty(sDur);
		this.rEx = new SimpleStringProperty(sEx);
		this.rShape = new SimpleStringProperty(sShape);
		this.rNote = new SimpleStringProperty(sNote);
	}
	
	
	public String getRID() {
		return rID.get();
	}


	public void setrID(String v) {
		rID.set(v);
	}


	public String getRDate() {
		return rDate.get();
	}


	public void setrDate(String v) {
		rDate.set(v);
	}


	public String getRTime() {
		return rTime.get();
	}


	public void setrTime(String v) {
		this.rTime.set(v);
	}

	
	public String getRDur() {
		return rDur.get();
	}


	public void setrDur(String v) {
		rDur.set(v);
	}


	public String getREx() {
		return rEx.get();
	}


	public void setrEx(String v) {
		rEx.set(v);
	}


	public String getRShape() {
		return rShape.get();
	}


	public void setrShape(String v) {
		rShape.set(v);
	}
	
	
	public String getRNote() {
		return rNote.get();
	}


	public void setrNote(String v) {
		rNote.set(v);
	}

	
//	public String toString() {
//        return String.format("%s %s %s %s %s %s %s", rID, rDate, rTime, rDur, rEx, rShape, rNote);
//    }


}
