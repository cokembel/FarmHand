package com.kembel.farmhand;

import java.util.ArrayList;

public class Farm {
	
	private ArrayList<State> rows;
	
	public Farm() {
		rows = new ArrayList<State>();
	}
	
    // precondition: rowNumber must be no greater than size +1
	// so that the indices of rows arraylist will match with
	// row numbers (with a difference of 1 for the offset)
	public void insert(int rowNumber, State state) {
		// replaces if already exists
		if (contains(rowNumber)) {
			rows.set(rowNumber - 1, state);
		} else {
			rows.add(state);
		}
	}
	
	public State getState(int rowNumber) {
		return rows.get(rowNumber - 1);
	}
	
	public boolean contains(int rowNumber) {
		if (rowNumber <= rows.size()) {
			return true;
		} else {
			return false;
		}
	}
}
