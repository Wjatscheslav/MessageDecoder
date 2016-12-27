package com.viacheslav.khpi.decoder.entity;

public class Sequence {

	String item;
	int frequency;

	public Sequence(String item, int frequency) {
		super();
		this.item = item;
		this.frequency = frequency;
	}

	public Sequence() {
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void incFrequency() {
		this.frequency++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return item + " - " + frequency + " ";
	}
	
	

}
