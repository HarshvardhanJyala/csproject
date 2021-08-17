package com.h2.example.h2db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGFILEENTITY")
public class LogFileEntity {

	// database specific

	@Id
	@GeneratedValue
	private long SNUM;

	private String EVENT_ID;
	private long EVENT_DURATION;
	private String TYPES;
	private String HOST;
	private boolean ALERT;

	public LogFileEntity() {
	}

	public LogFileEntity(boolean aLERT, long eVENT_DURATION, String eVENT_ID, String hOST, String tYPES, long sNUM) {

		SNUM = sNUM;
		EVENT_ID = eVENT_ID;
		EVENT_DURATION = eVENT_DURATION;
		TYPES = tYPES;
		HOST = hOST;
		ALERT = aLERT;
	}

	public long getSNUM() {
		return SNUM;
	}

	public void setSNUM(long sNUM) {
		SNUM = sNUM;
	}

	public void setTYPES(String tYPES) {
		TYPES = tYPES;
	}

	public String getEVENT_ID() {
		return EVENT_ID;
	}

	public void setEVENT_ID(String eVENT_ID) {
		EVENT_ID = eVENT_ID;
	}

	public long getEVENT_DURATION() {
		return EVENT_DURATION;
	}

	public void setEVENT_DURATION(long eVENT_DURATION) {
		EVENT_DURATION = eVENT_DURATION;
	}

	public String getTYPES() {
		return TYPES;
	}

	public String getHOST() {
		return HOST;
	}

	public void setHOST(String hOST) {
		HOST = hOST;
	}

	public boolean isALERT() {
		return ALERT;
	}

	public void setALERT(boolean aLERT) {
		ALERT = aLERT;
	}

	@Override
	public String toString() {
		return "LogFileEntity [SNUM=" + SNUM + ", EVENT_ID=" + EVENT_ID + ", EVENT_DURATION=" + EVENT_DURATION
				+ ", TYPES=" + TYPES + ", HOST=" + HOST + ", ALERT=" + ALERT + "]";
	}

}
