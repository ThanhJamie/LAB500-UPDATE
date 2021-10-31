/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Injection implements Comparable<Injection>{
    public String idInj;
    public String firstPlace;
    private String secondPlace;
    public LocalDate firstDate;
    public LocalDate secondDate;
    public String idStu;
    private String idVac;

    public Injection() {
    }

    public Injection(String idInj, String firstPlace, String secondPlace, LocalDate firstDate, LocalDate secondDate, String idStu, String idVac) {
        this.idInj = idInj;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.idStu = idStu;
        this.idVac = idVac;
    }

    public String getIdInj() {
        return idInj;
    }

    public void setIdInj(String idInj) {
        this.idInj = idInj;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public LocalDate getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(LocalDate secondDate) {
        this.secondDate = secondDate;
    }

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getIdVac() {
        return idVac;
    }

    public void setIdVac(String idVac) {
        this.idVac = idVac;
    }

    @Override
    public String toString() {
        return String.format("         %-18s %-19s %-19s %-26s %-22s %-25s %-25s",idInj,idStu,idVac,firstPlace,firstDate,secondPlace,secondDate);
    }

    @Override
    public int compareTo(Injection o) {
        return getIdInj().compareTo(o.getIdInj());
    }
}
