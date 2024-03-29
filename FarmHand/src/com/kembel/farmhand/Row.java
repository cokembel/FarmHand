package com.kembel.farmhand;

public class Row implements Comparable{
	
	private int number;
	private State state;
	
	public Row(int number, State state) {
		this.number = number;
		this.state = state;
	}
	
	public int getNumber() {
		return number;
	}
	
	public State getState() {
		return state;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public boolean equals(Object object) {
		if (!(object instanceof Row)) {
			return false;
		}
		
		Row row = (Row) object;
		
		if (this.number == row.getNumber()) {
			return true;
		}
		
		return false;
	}

	public int compareTo(Object object) {
		if (!(object instanceof Row)) {
			// 
		}
		
		Row row = (Row) object;
		
		if (this.number < row.getNumber()) {
			return -1;
		} else if (this.number == row.getNumber() ) {
			return 0;
		} else {
			return 1;
		}
	}
}
