/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.StudentList;
import dao.VaccineList;
import dto.Student;
import dto.Vaccine;

/**
 *
 * @author Admin
 */
public class WriteFile {
    public static void main(String[] args) {
        StudentList listStu = new StudentList();
        String filenameStu = "Student.dat";
        VaccineList listVac = new VaccineList();
        String filenameVac = "Vaccine.dat";
        
        listStu.addStudent(new Student("AI01", "Nguyen Van A"));
        listStu.addStudent(new Student("AI02", "Nguyen Van B"));
        listStu.addStudent(new Student("AI03", "Nguyen Van C"));
        listStu.addStudent(new Student("AI04", "Nguyen Van D"));
        listStu.addStudent(new Student("AI05", "Nguyen Van E"));
        listStu.addStudent(new Student("AI06", "Nguyen Van F"));
        listStu.addStudent(new Student("AI07", "Nguyen Van G"));
        listStu.addStudent(new Student("AI08", "Nguyen Van H"));
        listStu.addStudent(new Student("AI09", "Nguyen Van T"));
        
        
        listStu.printStudent();
        listStu.saveFileStudent(filenameStu);
        
        listVac.addVaccine(new Vaccine("COVID-V001", "AstraZeneca"));
        listVac.addVaccine(new Vaccine("COVID-V002", "SPUTNIK V"));
        listVac.addVaccine(new Vaccine("COVID-V003", "Vero Cell"));
        listVac.addVaccine(new Vaccine("COVID-V004", "Pfizer"));
        listVac.addVaccine(new Vaccine("COVID-V005", "Moderna"));
        listVac.printVaccine();
        listVac.saveFileVaccine(filenameVac);
    }
}
