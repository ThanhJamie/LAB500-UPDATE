/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Student;
import java.util.ArrayList;
import util.MyValidation;

/**
 *
 * @author Admin
 */
public class StudentList {
    ArrayList<Student> list;

    public StudentList() {
        list = new ArrayList<>();
    }

    public ArrayList<Student> getList() {
        return list;
    }

    public void setList(ArrayList<Student> list) {
        this.list = list;
    }
    
    public boolean addStudent(Student stu){
        return list.add(stu);
    }
    
    public void printStudent(){
        System.out.println(">>> LIST STUDENT <<<");
        System.out.println("| +++++ ID +++++ | ++++++++ NAME +++++++++ |");
        for (Student stu : list) {
            System.out.println(stu);
        }
    }
    
    public Student searchStudent(String idStu){
        for (Student stu : list) {
            if (stu.getIdStu().equals(idStu)){
                return stu;
            }
        }
        return null;
    }
    
    public boolean isIdDuplicate(String ID) {
        for (Student student : list) {
            if (student.idStu.equals(ID)) {
                return true;
            }
        }
        return false;
    }
    public boolean isIdExist(String idStu){
        return searchStudent(idStu) != null;
    }
    
    public void saveFileStudent(String filename){
        MyValidation.writeFileStudent(filename, list);
    }
    
    public void loadFileStudent(String filename){
        list = MyValidation.readFileStudent(filename);
    }
}
