package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ShiftType implements Serializable{
	private String name;
	private float duration;
	public ShiftType(String name, float duration) {
		if(duration<0) {
			throw new NumberFormatException("The duration can not be negative");
		}
		else {
			this.name = name;
			this.duration = duration;
		}
	}
	public String getName() {
		return name;
	}
	public float getDuration() {
		return duration;
	}
	
}
