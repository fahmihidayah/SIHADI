package com.example.model;

import java.util.ArrayList;

public class Conclution {
	private String nameStr;
	private ArrayList<Premis> listPremis = new ArrayList<Premis>();
	private long restultMatrix;

	public Conclution(String nameStr) {
		this.nameStr = nameStr;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public ArrayList<Premis> getListPremis() {
		return listPremis;
	}

	public long getRestultMatrix() {
		return restultMatrix;
	}

	public void setRestultMatrix(long l) {
		this.restultMatrix = l;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameStr == null) ? 0 : nameStr.hashCode());
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
		Conclution other = (Conclution) obj;
		if (nameStr == null) {
			if (other.nameStr != null)
				return false;
		} else if (!nameStr.equals(other.nameStr))
			return false;
		return true;
	}

}
