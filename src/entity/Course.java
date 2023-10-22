package entity;

import adt.LinkedList;
import adt.ListInterface;
import client.ProgrammeSubSystem;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class Course {
    
    private String courseID;
    private String courseName;
    private String eduLevel;
    private int creditHours;
    private ListInterface<Programme> progList = new LinkedList();


    public Course() {
        
    }
    public Course(String courseID, String courseName, String eduLevel, int creditHours) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.eduLevel = eduLevel;
        this.creditHours = creditHours;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    public void setProgList(ListInterface<Programme> progList) {
        this.progList = progList;
    }

    public String getCourseID() {
        return courseID;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getEduLevel() {
        return eduLevel;
    }
    public int getCreditHours() {
        return creditHours;
    }
    public ListInterface<Programme> getProgList() {
        return progList;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Course) {
            Course otherCourse = (Course) o;
            if (this.courseID == otherCourse.courseID) {
                return true;
            }
            else {
                return this.courseID.compareTo(otherCourse.courseID) == 0;
            }
        }
        return false;
    }

}
