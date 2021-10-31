/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class Vaccine implements Comparable<Vaccine>{
    private String idVac;
    private String name;

    public Vaccine() {
        idVac = "";
        name = "";
    }

    public Vaccine(String idVac, String name) {
        this.idVac = idVac;
        this.name = name;
    }

    public String getIdVac() {
        return idVac;
    }

    public void setIdVac(String idVac) {
        this.idVac = idVac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("     %-21s %-20s", idVac, name);
    }

    @Override
    public int compareTo(Vaccine o) {
        return getIdVac().compareTo(o.getIdVac());
    }
}
