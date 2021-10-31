/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.InjectionList;
import dao.StudentList;
import dao.VaccineList;

/**
 *
 * @author Admin
 */
public class TestReadFile {
    public static void main(String[] args) {
        StudentList listStu = new StudentList();
        String filenameStu = "Student.dat";
        VaccineList listVac = new VaccineList();
        String filenameVac = "Vaccine.dat";
        InjectionList listInj = new InjectionList();
        String filenameInj = "Injection.dat";
        listStu.loadFileStudent(filenameStu);
        listStu.printStudent();
        listVac.loadFileVaccine(filenameVac);
        listVac.printVaccine();
        listInj.loadFileInj(filenameInj, listStu);
        listInj.printInjection();
    }
}
