
package com.example.model;

import java.util.Comparator;

/*
 * sort dari besar ke kecil
 */
public class ComparatorConclution implements Comparator<Conclution>{

    @Override
    public int compare(Conclution o1, Conclution o2) {
        if(o1.getRestultMatrix() > o2.getRestultMatrix()){
            return -1;
        }
        return 1;
    }
    
}
