
package com.example.model;

import java.util.ArrayList;

public class Premis {
    private String nameStr;
    private ArrayList<Conclution> listConclution = new ArrayList<Conclution>();
    private boolean value;

    public Premis(String nameStr, boolean value) {
        this.nameStr = nameStr;
        this.value = value;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public ArrayList<Conclution> getListConclution() {
        return listConclution;
    }

    public void setListConclution(ArrayList<Conclution> listConclution) {
        this.listConclution = listConclution;
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
		Premis other = (Premis) obj;
		if (nameStr == null) {
			if (other.nameStr != null)
				return false;
		} else if (!nameStr.equals(other.nameStr))
			return false;
		return true;
	}

  
    
}
