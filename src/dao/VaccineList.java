/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Vaccine;
import java.util.ArrayList;
import util.MyValidation;

/**
 *
 * @author Admin
 */
public class VaccineList {
    ArrayList<Vaccine> list;

    public VaccineList() {
        list = new ArrayList<>();
    }

    public ArrayList<Vaccine> getList() {
        return list;
    }

    public void setList(ArrayList<Vaccine> list) {
        this.list = list;
    }
    
    public boolean addVaccine(Vaccine vac){
        return list.add(vac);
    }
    
    public void printVaccine(){
        System.out.println(">>> LIST VACCINE <<<");
        System.out.println("| ++++++ ID ++++++ | ++++++++ NAME +++++++++ |");
        for (Vaccine vac : list) {
            System.out.println(vac);
        }
    }
    
    public Vaccine searchVaccine(String idVac){
        for (Vaccine vac : list) {
            if (vac.getIdVac().equals(idVac)){
                return vac;
            }
        }
        return null;
    }
    
    public boolean isIdExist(String idVac){
        return searchVaccine(idVac) != null;
    }
    
    public void saveFileVaccine(String filename){
        MyValidation.writeFileVaccine(filename, list);
    }
    
    public void loadFileVaccine(String filename){
        list = MyValidation.readFileVaccine(filename);
    }
}
