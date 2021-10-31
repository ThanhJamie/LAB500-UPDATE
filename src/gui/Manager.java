/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.InjectionList;
import dao.StudentList;
import dao.VaccineList;
import util.MyValidation;

/**
 *
 * @author Admin
 */
public class Manager {
    public static void main(String[] args) {
        int option = 0;
        boolean inputAgain = false;
        String filenameStu = "Student.dat";
        String filenameVac = "Vaccine.dat";
        String filenameInj = "Injection.dat";
        StudentList listStu = new StudentList();
        VaccineList listVac = new VaccineList();
        InjectionList listInj = new InjectionList();
        listStu.loadFileStudent(filenameStu);
        listVac.loadFileVaccine(filenameVac);
        do {            
            listInj.loadFileInj(filenameInj, listStu);
            System.out.println("Covid-19 Vaccine Management - FPT University @2021 by<SE150380 - Dang Chi Thanh>");
            System.out.println("---------------------------------");
            System.out.println("Select the options below: ");
            System.out.println("1. Show information all students have been injected. ");
            System.out.println("2. Add student's vaccine injection information. ");
            System.out.println("3. Updating information of students' vaccine injection. ");
            System.out.println("4. Delete student vaccine injection information. ");
            System.out.println("5. Search for injection information. ");
            System.out.println("6. Store data to file. ");
            System.out.println("7. Information Encryption. ");
            System.out.println("8. Others- Quit. ");
            System.out.println("---------------------------------");
            option = MyValidation.inputIntRage("Your choice: ", option, 1, 8, inputAgain);
            switch(option){
                case 1:
                    listInj.sortByInjId();
                    listInj.printInjection();
                    break;
                case 2:
                    listInj.loadFileInj(filenameInj, listStu);
                    listInj.addInjection(listStu, listVac, filenameInj);
                    break;
                case 3:
                    listInj.updateInjection(filenameInj);
                    listInj.saveFileInj(filenameInj);
                    break;
                case 4:
                    listInj.loadFileInj(filenameInj, listStu);
                    listInj.removeInjection(listStu, filenameInj);
                    listInj.saveFileInj(filenameInj);
                    break;
                case 5:
                    int option2 = 0;
                    do {                        
                        System.out.println(">>> SEARCH INJECTION <<<");
                        System.out.println("1. Search by student ID. ");
                        System.out.println("2. Search by student name.");
                        System.out.println("3. Exit. ");
                        System.out.println("-------------------------- ");
                        option2 = MyValidation.inputIntRage("Your choice: ", option, 1, 3, inputAgain);
                        switch(option2){
                            case 1:
                                listInj.searchByIdStu(listStu);
                                break;
                            case 2:
                                listInj.searchByStuName(listStu);
                                break;
                        }
                    } while (option2 > 0 && option2 < 3);
                    break;
                case 6:
                    listInj.saveFileInj(filenameInj);
                    System.out.println(">>> Save successful.");
                    break;
                case 7:
                    listInj.saveFileInjMD5();
                    break;
            }
        } while (option > 0 && option < 8);
        if (option == 8){
            System.out.println("Good bye!");
        }
    }
}
