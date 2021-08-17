package com.h2.example.h2db.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServerLogsPojo {

	@Id
	private String ID;
	private String STATE;
	private Timestamp TIMESTAMP;
	private String TYPE;
	private String HOST;

	public ServerLogsPojo() {
	}

	public ServerLogsPojo(String iD, String sTATE, Timestamp tIMESTAMP, String tYPE, String hOST) {
		ID = iD;
		STATE = sTATE;
		TIMESTAMP = tIMESTAMP;
		TYPE = tYPE;
		HOST = hOST;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public Timestamp getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Timestamp tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getHOST() {
		return HOST;
	}

	public void setHOST(String hOST) {
		HOST = hOST;
	}

	@Override
	public String toString() {
		return "ServerLogsPojo [ID=" + ID + ", STATE=" + STATE + ", TIMESTAMP=" + TIMESTAMP + ", TYPE=" + TYPE
				+ ", HOST=" + HOST + "]";
	}

}
