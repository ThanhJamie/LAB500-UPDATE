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
        
        listStu.addStudent(new Student("SE140001", "Tran Phu Son"));
        listStu.addStudent(new Student("SE140002", "Tran Gia Hao"));
        listStu.addStudent(new Student("SE140003", "Van Thi My Anh"));
        listStu.addStudent(new Student("SE150004", "Lam Van Phai"));
        listStu.addStudent(new Student("SE150005", "Le Tuyet Nhung"));
        listStu.addStudent(new Student("SE150006", "Ngo Minh Hung"));
        listStu.addStudent(new Student("SE150007", "Nguyen Tan Dat"));
        listStu.addStudent(new Student("SE160008", "Phung Thi Thuy Trang"));
        listStu.addStudent(new Student("SE160009", "Nguyen Gia Trung Kien"));
        listStu.addStudent(new Student("SE160010", "Phuong Thi Thu Dung"));
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
