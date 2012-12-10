package com.kembel.farmhand;

import java.util.ArrayList;

public class Farm {
	
	private ArrayList<State> rows;
	
	public Farm() {
		rows = new ArrayList<State>();
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
