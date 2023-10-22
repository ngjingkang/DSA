package entity;

import adt.LinkedQueue;
import adt.ListInterface;
import adt.QueueInterface;
import client.ProgrammeSubSystem;
import static client.ProgrammeSubSystem.addProgramme;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class Programme {
    
    private String progID;
    private String progName;
    private String faculty;
    private String certificate;
    private int duration;
    private int totalCreditHours;
    private double fee;
    private QueueInterface<TutorialGroup> tutGrpList = new LinkedQueue();

    public Programme() {
        
    }
    public Programme(String progID, String progName, String faculty, String certificate, int duration, int totalCreditHours, double fee) {
        this.progID = progID;
        this.progName = progName;
        this.faculty = faculty;
        this.certificate = certificate;
        this.duration = duration;
        this.totalCreditHours = totalCreditHours;
        this.fee = fee;
    }
    public void setTutGrpList(QueueInterface<TutorialGroup> tutGrpList) {
        this.tutGrpList = tutGrpList;
    }

    public void setProgID(String progID) {
        this.progID = progID;
    }
    public void setProgName(String progName) {
        this.progName = progName;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setTotalCreditHours(int totalCreditHours) {
        this.totalCreditHours = totalCreditHours;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getProgID() {
        return progID;
    }
    public String getProgName() {
        return progName;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getCertificate() {
        return certificate;
    }
    public int getDuration() {
        return duration;
    }
    public int getTotalCreditHours() {
        return totalCreditHours;
    }
    public double getFee() {
        return fee;
    }
    public QueueInterface<TutorialGroup> getTutGrpList() {
        return tutGrpList;
    }
    public void addTutorialGroup(TutorialGroup toBeAdded){
        tutGrpList.enqueue(toBeAdded);
    }
    public void addGroupToProgramme(TutorialGroup tutorialGroup){
        tutGrpList.enqueue(tutorialGroup);
    }
    @Override
    public String toString(){
        return("Programme ID            : " + getProgID()+"\nProgramme Name             : "+ getProgName()+"\nProgramme Faculty          : "+ getFaculty()+"\nProgramme Certificate      : "+ getCertificate()+"\nProgramme Duration         : "+ getDuration()+" Years"+"\nProgramme TotalCreditHours : "+ getTotalCreditHours()+" Hours"+"\nProgramme Fee              : RM "+ getFee());

    }
     @Override
    public boolean equals(Object o) {
        if (o instanceof Programme) {
            Programme otherProg = (Programme) o;
            if (this.progID == otherProg.progID) {
                return true;
            }
            else {
                return this.progID.compareTo(otherProg.progID) == 0;
            }
        }
        return false;
    }
    
    
}
