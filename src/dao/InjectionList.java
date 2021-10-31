/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Injection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import util.MD5;
import util.MyValidation;

/**
 *
 * @author Admin
 */
public class InjectionList {

    ArrayList<Injection> list;

    public StudentList stuList;
    public VaccineList vacList;

    public InjectionList() {
        list = new ArrayList<>();
    }

    public ArrayList<Injection> getList() {
        return list;
    }

    public void setList(ArrayList<Injection> list) {
        this.list = list;
    }

    public InjectionList(StudentList stuList, VaccineList vacList) {
        this.stuList = stuList;
        this.vacList = vacList;
    }

    public void addInjection(StudentList stuList, VaccineList vacList, String filename) {
//        StudentList listStu = new StudentList();
//        VaccineList listVac = new VaccineList();
        String idInj = null;
        String firstPlace = null;
        String secondPlace = null;
        LocalDate firstDate = null;
        LocalDate secondDate = null;
        String idStu = null;
        String idVac = null;
        String choice = "";
        boolean inputAgain = false;
        boolean isTrue = false;
        do {
            System.out.println(">>> ADD NEW INJECTION.");
            do {
                idInj = MyValidation.inputStrPat("Input injection ID (IN***): ", "IN\\d{3}");
                if (isIdExist(idInj)) {
                    System.err.println(">>> Injection ID existed!!!");
                }
            } while (isIdExist(idInj));
            do {
                stuList.printStudent();
                idStu = MyValidation.getString("Input student ID: ", "").trim().toUpperCase();
                isTrue = stuList.isIdDuplicate(idStu);
                if (!isTrue) {
                    System.err.println("The student ID must be existed");
                    continue;
                }
                isTrue = isStudentIDDuplicate(idStu);
                if (isTrue) {
                    System.err.println("The student id already have in injection list");
                } else {
                    break;
                }
            } while (true);
            vacList.printVaccine();
            idVac = MyValidation.inputVacId("Input Vaccine ID: ", vacList);
            firstDate = MyValidation.inputFirstDate("Input 1st injection date (yyyy-mm-dd): ");
            firstPlace = MyValidation.inputStr("Input 1st injection place: ");
            secondDate = MyValidation.inputSecondDate("Input 2nd injection date (yyyy-mm-dd), can be blank: ", firstDate);
            if (secondDate != null) {
                secondPlace = MyValidation.inputStr("Input 2nd injection place: ");
            }
            list.add(new Injection(idInj, firstPlace, secondPlace, firstDate, secondDate, idStu, idVac));
            stuList.searchStudent(idStu).injNum++;
            System.out.println(">>> Add successful.");
            choice = MyValidation.inputYesNo("Do you want to add another injection (Y/N): ", choice, inputAgain);
        } while (choice.equals("Y"));
        this.saveFileInj(filename);
    }

    public void printInjection() {
        if (list.isEmpty()) {
            System.err.println(">>> List Injection is empty!!!");
            System.out.println();
        } else {
            System.out.println(">>> LIST INJECTION <<<");
            System.out.println("| -- ID INJECTION -- | --- STUDENT ID --- | --- VACCINE ID --- | ---- FIRST PLACE ---- | ---- FIRST DATE ---- | ----- SECOND PLACE ----- | ---- SECOND DATE ---- |");
            for (Injection inj : list) {
                System.out.println(inj);
            }
            System.out.println();
        }
    }

    public void sortByInjId() {
        Collections.sort(list);
    }

    private int findID(String idInj) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIdInj().equals(idInj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean searchByStuId(String idStu) {
        for (Injection inj : list) {
            if (inj.getIdStu().equals(inj)) {
                return true;
            }
        }
        return false;
    }

    public Injection searchInjById(String idInj) {
        for (Injection inj : list) {
            if (inj.getIdInj().equals(idInj)) {
                return inj;
            }
        }
        return null;
    }

    public boolean isIdExist(String idInj) {
        return searchInjById(idInj) != null;
    }

    public void searchByIdStu(StudentList stuList) {
        boolean testcase = false;
        int exist = -1;
        if (list.isEmpty()) {
            System.out.println(">>> List Injection is empty!!!");
            System.out.println();
        }
        else {
            stuList.printStudent();
            String idStuToSeach = MyValidation.inputStuIdToSearch("Input student ID to search: ", stuList);
            idStuToSeach = idStuToSeach.toLowerCase();
            
            for (Injection inj : list) {
                if (inj.getIdStu().toLowerCase().equals(idStuToSeach)) {
                    System.out.println(">>> INFORMATION OF INJECTION <<<");
                    System.out.println("| -- ID INJECTION -- | --- STUDENT ID --- | --- VACCINE ID --- | ---- FIRST PLACE ---- | ---- FIRST DATE ---- | ----- SECOND PLACE ----- | ---- SECOND DATE ---- |");
                    System.out.println(inj);
                    exist = 1;
                }else{
                    break;
                }
                
            }
        }
    }

    
    public void searchByStuName(StudentList stuList) {
        if (list.isEmpty()) {
            System.out.println(">>> List Injection is empty!!!");
        }
        else {
            stuList.printStudent();
            String stuName = MyValidation.inputStr("Input student's name to search: ");
            stuName = stuName.toLowerCase();
            int exist = -1;
            for (Injection inj : list) {
                if (stuList.searchStudent(inj.getIdStu()).getName().toLowerCase().equalsIgnoreCase(stuName)) {
                    System.out.println(">>> INFORMATION OF INJECTION <<<");
                    System.out.println("| -- ID INJECTION -- | --- STUDENT ID --- | --- VACCINE ID --- | ---- FIRST PLACE ---- | ---- FIRST DATE ---- | ----- SECOND PLACE ----- | ---- SECOND DATE ---- |");
                    System.out.println(inj);
                    exist = 1;
                }
            }
            System.out.println();
            if (exist == -1) {
                System.err.println("The injection not exist!");
            }
        } 
    }

    public void updateInjection(String filename) {
        if (list.isEmpty()) {
            System.out.println(">>> List Injection is empty!!!");
            System.out.println();
        } else {
            String idToUpdate = MyValidation.inputStrPat("Input injection id to update (IN***): ", "IN\\d{3}");
            int pos = findID(idToUpdate);
            if (pos < 0) {
                System.err.println(">>> Injection ID does not exist. Update fail! ");
                System.out.println();
            } else {
                if (list.get(pos).getSecondDate() != null) {
                    System.err.println(">>> GREEN: This student has had 2 injection.");
                    System.out.println();
                } else if (list.get(pos).getSecondDate() == null) {
                    LocalDate secondDate = MyValidation.inputSecondDateToUp("Input 2nd injection date (yyyy-mm-dd): ", list.get(pos).getFirstDate());
                    if (secondDate != null) {
                        String secondPlace = MyValidation.inputStr("Input 2nd injection place: ");
                        list.get(pos).setSecondDate(secondDate);
                        list.get(pos).setSecondPlace(secondPlace);
                        System.err.println(">>> GREEN: Student has completed 2 injections!");
                        System.out.println();
                        System.out.println(">>> INJECTION " + idToUpdate + " AFTER UPDATE <<<");
                        System.out.println("| ++ ID INJECTION ++ | +++ STUDENT ID +++ | +++ VACCINE ID +++ | ++++ FIRST PLACE +++++ | ++++ FIRST DATE ++++ | ++++ SECOND PLACE +++++ | ++++ SECOND DATE ++++ |");
                        for (Injection inj : list) {
                            if (inj.getIdInj().contains(idToUpdate)) {
                                System.out.println(inj);
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
        this.saveFileInj(filename);
    }

    public void removeInjection(StudentList stuList, String filename) {
        if (list.isEmpty()) {
            System.out.println(">>> List Injection is empty!!!");
            System.out.println();
        } else {
            String idToRemove = MyValidation.inputStrPat("Input injection id to update (IN***): ", "IN\\d{3}");
            int pos = findID(idToRemove);
            if (pos < 0) {
                System.err.println(">>> Injection ID does not exist. Remove fail! ");
                System.out.println();
            } else {
                boolean inputAgain = false;
                String choice = "";
                choice = MyValidation.inputYesNo("Do you want to remove this injection? (Y/N): ", choice, inputAgain);
                if (choice.equals("Y")) {
                    list.remove(pos);
                    System.err.println(">>> " + idToRemove + " removed successfull.");
                    System.out.println();
                } else {
                    System.err.println("Remove fail! Please try again!");
                    System.out.println();
                }
            }
//            stuList.searchStudent(list.get(pos).getIdInj()).injNum--;
        }
        this.saveFileInj(filename);

    }

    public void saveFileInjMD5() {
        MD5 md = new MD5();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("Injection_MD5.dat");
            for (Injection inj : list) {
                pw.write(md.MD5(inj));
                pw.write("\n");
            }
            System.err.println("Save successfull.");
        } catch (FileNotFoundException | NoSuchAlgorithmException e) {

        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void saveFileInj(String filename) {
        try {
            File f = new File(filename);
            FileWriter fw = new FileWriter(f);
            for (Injection injection : list) {
                fw.write(injection.getIdInj() + "," + injection.getIdStu() + "," + injection.getIdVac() + "," + injection.getFirstPlace() + "," + injection.getFirstDate() + "," + injection.getSecondPlace() + "," + injection.getSecondDate());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadFileInj(String filename, StudentList stuList) {
        try {
            File f = new File(filename);
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String str = myReader.nextLine();
                String idInj = str.split(",")[0];
                String idStu = str.split(",")[1];
                String idVac = str.split(",")[2];
                String firstPlace = str.split(",")[3];
                LocalDate firstDate = LocalDate.parse(str.split(",")[4], DateTimeFormatter.ISO_LOCAL_DATE);
                String secondPlace = str.split(",")[5].equals("null") ? null : str.split(",")[5];
                LocalDate secondDate = str.split(",")[6].equals("null") ? null : LocalDate.parse(str.split(",")[6], DateTimeFormatter.ISO_LOCAL_DATE);
                if (stuList.isIdExist(idStu) && !this.isIdExist(idInj) && !this.searchByStuId(idStu)) {
                    Injection inj = new Injection(idInj, firstPlace, secondPlace, firstDate, secondDate, idStu, idVac);
                    list.add(inj);
                    stuList.searchStudent(idStu).injNum++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public boolean isInjectionIdDuplicate(String ID) {
        for (Injection injection : list) {
            if (injection.idInj.equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStudentIDDuplicate(String studentID) {
        for (Injection injection : list) {
            if (injection.idStu.equals(studentID)) {
                return true;
            }
        }
        return false;
    }
}
