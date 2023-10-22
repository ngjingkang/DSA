package entity;

import adt.LinkedQueue;
import adt.ListInterface;
import adt.QueueInterface;

import Utility.TempDatabase;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class TutorialGroup{

    private String grpID;
    private int capacity;
    private QueueInterface<Student> studList = new LinkedQueue();
    private QueueInterface<AssignmentTeam> assTeamList = new LinkedQueue();

    

    public TutorialGroup() {
        this.grpID = grpID;
        this.capacity = capacity;
        
    }
    public TutorialGroup(String grpID, int capacity) {
        this.grpID = grpID;
        this.capacity = capacity;
        
    }

    

    public void setStudList(QueueInterface<Student> studList) {
        this.studList = studList;
    }

    public void setGrpID(String grpID) {
        this.grpID = grpID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public void setAssTeamList(QueueInterface<AssignmentTeam> assTeamList) {
        this.assTeamList = assTeamList;
    }

    public String getGrpID() {
        return grpID;
    }

    public int getCapacity() {
        return capacity;
    }

    public QueueInterface<Student> getStudList() {
        return studList;
    }
    
    public QueueInterface<AssignmentTeam> getAssTeamList() {
        return assTeamList;
    }

    @Override
    public String toString() {
        return  getGrpID() + "\t\t" + getCapacity();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TutorialGroup) {
            TutorialGroup otherTutGrp = (TutorialGroup) o;
            if (this.grpID == otherTutGrp.grpID) {
                return true;
            }
            else {
                return this.grpID.compareTo(otherTutGrp.grpID) == 0;
            }
        }
        return false;
    }
    
}
