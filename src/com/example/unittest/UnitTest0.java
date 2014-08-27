/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.unittest;

import com.example.beans.Inferentor;
import com.example.model.Conclution;
import com.example.model.Premis;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fahmi
 */
public class UnitTest0 {
    public static void main(String args[]){
        Inferentor inferentor = new Inferentor();
        inferentor.addConclution(new Conclution("con - 0"));
        inferentor.addPremisToConclution(new Conclution("con - 0"), new Premis("prem - 0", false));
        inferentor.addPremisToConclution(new Conclution("con - 0"), new Premis("prem - 1", false));
        inferentor.addPremisToConclution(new Conclution("con - 0"), new Premis("prem - 2", false));
        inferentor.addConclution(new Conclution("con - 1"));
        inferentor.addPremisToConclution(new Conclution("con - 1"), new Premis("prem - 1", false));
        inferentor.addPremisToConclution(new Conclution("con - 1"), new Premis("prem - 2", false));
        inferentor.addPremisToConclution(new Conclution("con - 1"), new Premis("prem - 3", false));
        inferentor.addConclution(new Conclution("con - 2"));
        inferentor.addPremisToConclution(new Conclution("con - 2"), new Premis("prem - 3", false));
        inferentor.addPremisToConclution(new Conclution("con - 2"), new Premis("prem - 4", false));
        inferentor.addPremisToConclution(new Conclution("con - 3"), new Premis("prem - 5", false));
        System.out.println("jumlah cons" + inferentor.getListConclutions().size());
        System.out.println("jumlah prem" + inferentor.getListPremis().size());
        
        inferentor.getListPremis().get(5).setValue(true);
       // inferentor.getListPremis().get(3).setValue(true);
       // inferentor.getListPremis().get(4).setValue(true);
        
        inferentor.countResult();
        ArrayList<Conclution> conclutions = inferentor.getSortedResult();
        for (Conclution conclution : conclutions) {
            System.out.println(conclution.getNameStr());
        }
    }
    
    public static List<Premis> createListPremis(){
        List<Premis> listPrem = new ArrayList<Premis>();
        for(int i = 0 ; i < 30; i ++){
            listPrem.add(new Premis("prem - " + i, false));
        }
        return listPrem;
    }
}
