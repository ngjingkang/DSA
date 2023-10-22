package entity;

import adt.ArraySetList;
import adt.SetListInterface;

/**
 *
 * @author Yap Ying Sin
 */
public class AssignmentTeam {
    
    private String teamID;
    private String teamName;
    private int minCapacity;
    private int maxCapacity;
    private SetListInterface<Student> studList;
    private SetListInterface<Assignment> assList;
    
    private static int teamIDCount = 1000;

    public AssignmentTeam() {
        
    }
    public AssignmentTeam(String teamName, int minCapacity, int maxCapacity) {
        this.teamID = "T" + (teamIDCount + 1);
        this.teamName = teamName;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        studList = new ArraySetList<>();
        assList = new ArraySetList<>();
        
        teamIDCount++;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    public void setStudList(SetListInterface<Student> studList) {
        this.studList = studList;
    }
    public void setAssList(SetListInterface<Assignment> assList) {
        this.assList = assList;
    }

    public String getTeamID() {
        return teamID;
    }
    public String getTeamName() {
        return teamName;
    }
    public int getMinCapacity() {
        return minCapacity;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public SetListInterface<Student> getStudList() {
        return studList;
    }
    public SetListInterface<Assignment> getAssList() {
        return assList;
    }
    public static int getTeamIDCount() {
        return teamIDCount;
    }
    
    @Override
    public String toString() {
        
        String outputStr = "Team ID: " + teamID +
                           "\nTeam Name: " + teamName +
                           "\nMin. Capacity: " + minCapacity +
                           "\nMax. Capacity: " + maxCapacity +
                           "\nNo. of Assignments: " + assList.getNumberOfEntries() +
                           "\nNo. of Students: " + studList.getNumberOfEntries();
        
        return outputStr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof AssignmentTeam) {
            AssignmentTeam otherTeam = (AssignmentTeam) o;
            if (this.teamID == otherTeam.teamID) {
                return true;
            }
            else {
                return this.teamID.compareTo(otherTeam.teamID) == 0;
            }
        }
        return false;
    }
    
}
