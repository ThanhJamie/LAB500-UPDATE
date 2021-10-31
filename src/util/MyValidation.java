/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.InjectionList;
import dao.StudentList;
import dao.VaccineList;
import dto.Student;
import dto.Vaccine;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;
import dto.Injection;

/**
 *
 * @author Admin
 */
public class MyValidation {

    public static int inputIntRage(String userInput, int number, int min, int max, boolean inputAgain) {
        String input = "";
        while (true) {
            System.out.print(userInput);
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine().toUpperCase();
            if (inputAgain == true) {
                if (input.equals("")) {
                    return -1;
                }
            }
            try {
                int result = Integer.parseInt(input);
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a number in rage [" + min + ", " + max + "]. Try again!");
            }
        }
    }

    public static String inputYesNo(String userInput, String choice, boolean inputAgain) {
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(userInput);
                choice = sc.nextLine();
                if (!choice.equals("Y") && !choice.equals("N")) {
                    throw new Exception();
                }
                inputAgain = false;
            } catch (Exception e) {
                System.err.println("Enter 'Y' or 'N' only!");
                inputAgain = true;
            }
        } while (inputAgain);
        System.out.println();
        return choice;
    }

    public static String inputStrPat(String userInput, String pattern) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim();
            if (!input.matches(pattern)) {
                System.err.println("Wrong format! Please input again!");
            } else if (input.length() < 1) {
                System.err.println("Do not leave blank! Please input again!");
            }
        } while (!input.matches(pattern));
        return input;
    }

    public static String inputStr(String userInput) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            sc = new Scanner(System.in);
            input = sc.nextLine().trim();
            if (input.length() < 1) {
                System.err.println("Do not leave blank! Please input again!");
            }
        } while (input.length() < 1);
        return input;
    }

//    public static String inputStuId(String userInput, StudentList stuList){
//        String input;
//        Scanner sc = new Scanner(System.in);
//        do {            
//            System.out.print(userInput);
//            sc = new Scanner(System.in);
//            input = sc.nextLine().trim().toUpperCase();
//            if (!stuList.isIdExist(input)){
//                System.err.println(">>> Student is not exist!!!");
//            }
//            else if (stuList.searchStudent(input).getInjNum() >= 1){
//                System.err.println(">>> This student has had 2 injection or is waiting for a second injection.");
//            }
//        } while (!stuList.isIdExist(input) || stuList.searchStudent(input).getInjNum() >= 1);
//        return input;
//    }
    public static String inputInjIdToSearch(String userInput, InjectionList injList) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim().toUpperCase();
            if (!injList.isIdExist(input)) {
                System.err.println(">>> Injection is not exist!!!");
            }
        } while (!injList.isIdExist(input));
        return input;
    }

    public static String inputStuIdToSearch(String userInput, StudentList stuList) {
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.print(userInput);
        input = sc.nextLine().trim().toUpperCase();
        if (!stuList.isIdExist(input)) {
            System.err.println(">>> Student is not exist!!!");
        }
        return input;
    }

    public static String inputVacId(String userInput, VaccineList vacList) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim();
            if (!vacList.isIdExist(input)) {
                System.err.println(">>> Vaccine is not exist!!!");
            }
        } while (!vacList.isIdExist(input));
        return input;
    }

    public static LocalDate inputFirstDate(String userInput) {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim();
            if (!isValidDate(input)) {
                System.err.println(">>> Invalid Date.");
            }
        } while (!isValidDate(input));
        return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalDate inputSecondDate(String userInput, LocalDate firstDate) {
        String input;
        LocalDate secondDate = null;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim();
            if (input.length() > 0) {
                if (!isValidDate(input)) {
                    System.err.println(">>> Invalid Date.");
                } else {
                    secondDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                    if (secondDate.isAfter(firstDate.plusWeeks(12)) || secondDate.isBefore(firstDate.plusWeeks(4))) {
                        System.err.println(">>> The second injection must be given 4 to 12 weeks after the first injection!");
                    }
                }
            }
        } while (input.length() > 0 && (!isValidDate(input) || secondDate.isAfter(firstDate.plusWeeks(12)) || secondDate.isBefore(firstDate.plusWeeks(4))));
        return secondDate;
    }

    public static LocalDate inputSecondDateToUp(String userInput, LocalDate firstDate) {
        String input;
        LocalDate secondDate = null;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(userInput);
            input = sc.nextLine().trim();
            if (!isValidDate(input)) {
                System.err.println(">>> Invalid Date.");
            } else {
                secondDate = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                if (secondDate.isAfter(firstDate.plusWeeks(12)) || secondDate.isBefore(firstDate.plusWeeks(4))) {
                    System.err.println(">>> The second injection must be given 4 to 12 weeks after the first injection!");
                }
            }
        } while (!isValidDate(input) || secondDate.isAfter(firstDate.plusWeeks(12)) || secondDate.isBefore(firstDate.plusWeeks(4)));
        return secondDate;
    }

    private static boolean isValidDate(String date) {
        boolean valid;
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT));
            valid = true;
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }

    public static ArrayList<Student> readFileStudent(String filename) {
        FileReader fis = null;
        BufferedReader dis = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            fis = new FileReader(filename);
            dis = new BufferedReader(fis);
            while (dis.ready()) {
                String str = dis.readLine();
                String id = str.split(",")[0];
                String name = str.split(",")[1];
                Student stu = new Student(id, name);
                list.add(stu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

//    public static void performOutput(String filename, InjectionList injList){
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            FileOutputStream fout = new FileOutputStream(filename);
//            DigestOutputStream out = new DigestOutputStream(fout, md);
//                byte[] b = 
//             
//        } catch (Exception e) {
//        }
//    }
    public static boolean writeFileStudent(String filename, ArrayList<Student> list) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
            for (Student student : list) {
                pw.println(student.getIdStu() + "," + student.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static ArrayList<Vaccine> readFileVaccine(String filename) {
        FileReader fis = null;
        BufferedReader dis = null;
        ArrayList<Vaccine> list = new ArrayList<>();
        try {
            fis = new FileReader(filename);
            dis = new BufferedReader(fis);
            while (dis.ready()) {
                String str = dis.readLine();
                String id = str.split(",")[0];
                String name = str.split(",")[1];
                Vaccine vac = new Vaccine(id, name);
                list.add(vac);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean writeFileVaccine(String filename, ArrayList<Vaccine> list) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
            for (Vaccine vaccine : list) {
                pw.println(vaccine.getIdVac() + "," + vaccine.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String getString(String msg, String pattern) {
        Scanner sc = new Scanner(System.in);
        String result = "";
        do {
            System.out.print(msg);
            result = sc.nextLine().trim();
        } while (result.matches(pattern) || result.equals("") || result == null);

        return result;
    }
}
