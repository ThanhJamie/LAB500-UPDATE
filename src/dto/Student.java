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
public class Student implements Comparable<Student>{
    public int injNum = 0;
    public String idStu;
    private String name;

    public Student() {
        idStu = "";
        name = "";
    }

    public Student(String idStu, String name) {
        this.idStu = idStu;
        this.name = name;
    }

    public int getInjNum() {
        return injNum;
    }

    public void setInjNum(int injNum) {
        this.injNum = injNum;
    }

    public String getIdStu() {
        return idStu;
    }

    public void setIdStu(String idStu) {
        this.idStu = idStu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("     %-18s %-20s", idStu, name);
    }

    @Override
    public int compareTo(Student o) {
        return getIdStu().compareTo(o.getIdStu());
    }
}
