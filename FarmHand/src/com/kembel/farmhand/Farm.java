package com.kembel.farmhand;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;

public class Farm implements Serializable {
	
	private ArrayList<State> rows;
	private String name;
	
	public Farm() {
		rows = new ArrayList<State>();
		name = "";
	}
	
	public void insert(int rowNumber, State state) {
		if( rowNumber < 1){
			return;
		}
		// replaces if already exists
		if (contains(rowNumber)) {
			rows.set(rowNumber - 1, state);
		} else {
			while(rows.size() != rowNumber -1) {
				rows.add(State.NOT_SPECIFIED);
			}
			rows.add(state);
		}
	}
	
	public State getState(int rowNumber) {
		if (rows.size() == 0) {
			return State.NOT_SPECIFIED;
		}
		
		return rows.get(rowNumber - 1);
	}
	
	public boolean contains(int rowNumber) {
		if (rowNumber <= rows.size()) {
			return true;
		} else {
			return false;
		}
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		
	}
	
	public int getFirstRow() {
		for (int i = 0; i < rows.size(); i++) {
			if (rows.get(i) != State.NOT_SPECIFIED) {
				return i +1;
			}
		}
		
		return 0;
	}
	
	public int getLastRow() {
		for (int i = rows.size() -1; i > 0; i--) {
			if (rows.get(i) != State.NOT_SPECIFIED) {
				return i +1;
			}
		}
		
		return 0;
	}
	
	public int getSize() {
		return getLastRow() - getFirstRow() + 1;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
