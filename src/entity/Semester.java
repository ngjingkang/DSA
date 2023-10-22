/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.HashInterface;
import adt.HashMap;

/**
 *
 * @author sujing
 */
public class Semester {
    private int year;
    private int semNo;
    private HashInterface<String, Tutor> tutorSet; //to add tutor to sem

    public Semester(int year, int semNo) {
        this.year = year;
        this.semNo = semNo;
        //this.tutorSet = new HashMap<>(); //to add tutor to sem
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemNo() {
        return semNo;
    }

    public void setSemNo(int semNo) {
        this.semNo = semNo;
    }

    public HashInterface getTutorSet() {
        return tutorSet;
    }

    public void setTutorSet(HashInterface tutorSet) {
        this.tutorSet = tutorSet;
    }
    
//    public void addTutorSem(Tutor tutor){ //to add tutor to sem
//        tutorSet.add(tutor.getId(), tutor);
//    }
//    
//    public HashInterface<String, Tutor> getTutorSem(){  //to add tutor to sem
//        return tutorSet;
//    }

    @Override
    public String toString() {
        return "Semester{" + "year=" + year + ", semNo=" + semNo + '}';
    
    }
}
