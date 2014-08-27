
package com.example.beans;

import com.example.model.ComparatorConclution;
import com.example.model.Conclution;
import com.example.model.Premis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * ineferentor adalah kelas untuk menghandle struktur data graf antara konklusi dan premis
 * method dasar tambah kurang hapus
 * method ai count hasil
 * @author fahmi
 */

public class Inferentor {
    private ArrayList<Conclution> listConclutions = new ArrayList<Conclution>();
    private ArrayList<Premis> listPremis = new ArrayList<Premis>();
    
    /**
     * untuk menambahkan premis yang ada dalam listPremis ke dalam conclution yang ada pada list conclution
     * @param indexConclution
     * @param indexPremis
     * @return boolan sukses atau tidak
     */
    
    public boolean addPremisToConclution(int indexConclution, int indexPremis){
        boolean successInsert = true;
        try{
            listConclutions.get(indexConclution).getListPremis().add(listPremis.get(indexPremis));
            listPremis.get(indexPremis).getListConclution().add(listConclutions.get(indexConclution));
        }
        catch(IndexOutOfBoundsException ex){
            successInsert = false;
        }
        return successInsert;
    }
    
    public void addPremisToConclution(Conclution conclution, Premis premis){
        int indexPremis = addItemToList(listPremis, premis);
        int indexConclution = addItemToList(listConclutions, conclution);
        addPremisToConclution(indexConclution, indexPremis);
    }
    
    private int addItemToList(ArrayList list, Object item){
        int index = list.indexOf(item);
        if(index == -1){
            index = list.size();
            list.add(item);
        }
        return index;
    }
    
    public void addConclution(Conclution conclution){
        if(!listConclutions.contains(conclution)){
            listConclutions.add(conclution);
        }
    }
    
    /**
     * menghapus premis dari conclution
     * @param indexConclution
     * @param indexPremis
     * @return 
     */
    
    public boolean removePremisFromConclution(int indexConclution, int indexPremis){
        boolean successDelete = true;
        try{
            Conclution conclution = listConclutions.get(indexConclution);
            Premis deletedPremis = conclution.getListPremis().remove(indexPremis);
            deletedPremis.getListConclution().remove(conclution);
            
            if(deletedPremis.getListConclution().isEmpty()){
                listPremis.remove(deletedPremis);
            } 
        }
        catch(IndexOutOfBoundsException ex){
            successDelete = false;
        }
        return successDelete;
    }
    
    public boolean removePremisFromConclution(Conclution conclution, Premis premis){
        boolean successDelete = true;
        try{
            int indexConclution = listConclutions.indexOf(conclution);
            int indexPremis = listConclutions.get(indexConclution).getListPremis().indexOf(premis);
            successDelete = removePremisFromConclution(indexConclution, indexPremis);
        }
        catch (Exception ex){
            successDelete = false;
        }
        return successDelete;
    }
    
    /**
     * menghapus data conclution, sekaligus menghapus premis jika tidak ada lagi conclution yang mengacu pada dia
     * @param indexConclution 
     */
    
    public void removeConclution(int indexConclution){
        try{
            Conclution deletedConclution = listConclutions.remove(indexConclution);
            ArrayList<Premis> listDeletedPremis = deletedConclution.getListPremis();
            
            for(int i = 0; i < listDeletedPremis.size(); i++){
                removePremisFromConclution(indexConclution, i);
            }
        }
        catch(Exception ex){
            System.out.println("exception when delete");
        }
    }
    
    public void removeConclution(Conclution conclution){
        try{
            int indexConclution = listConclutions.indexOf(conclution);
            if(indexConclution != -1){
                removeConclution(indexConclution);
            }
        }
        catch (Exception ex){
            System.out.println("exception when delete");
        }
    }

    public void countResult(){
        for (Conclution conclution : listConclutions) {
            countMatrixConclution(conclution);
        }
    }
    
    public void countMatrixConclution(Conclution conclution){
        ArrayList<Premis> listPremis = conclution.getListPremis();
        int matrix = 0;
        for (Premis premis : listPremis) {
            matrix = (premis.isValue()? matrix+1 : matrix);
        }
        double resultMatrix =  100 * matrix / listPremis.size();
        conclution.setRestultMatrix(Math.round(resultMatrix));
    }
    
    public ArrayList<Conclution> getSortedResult(){
        ArrayList<Conclution> listResultConclutions = new ArrayList<Conclution>();
        for (Conclution conclution : this.listConclutions) {
            listResultConclutions.add(conclution);
        }
        Collections.sort(listResultConclutions, new ComparatorConclution());
        return listResultConclutions;
    } 
    
    public void resetKnowledgeBase(){
    	for (Conclution conclution : this.listConclutions) {
			conclution.setRestultMatrix(0);
			ArrayList<Premis> listPremis = conclution.getListPremis();
		}
    	for (Premis premis : listPremis) {
    		premis.setValue(false);
		}
    }
    
    /*--------------------------------------------------------------------------
     * get method
     */
    
    public ArrayList<Conclution> getListConclutions() {
        return listConclutions;
    }

    public ArrayList<Premis> getListPremis() {
        return listPremis;
    }
    
}
