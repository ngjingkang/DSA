package entity;

import java.time.LocalDate;

/**
 *
 * @author Yap Ying Sin
 */
public class Assignment {
    
    private String assID;
    private String assTitle;
    private Course course;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate submitDate;
    private String progress;  // Pending / Submitted
    private String status;  // Pending / Completed / Late / Overdue
    
    private static int assIDCount = 1000;

    public Assignment(String assTitle, Course course, LocalDate startDate, LocalDate dueDate, LocalDate submitDate) {
        this.assID = "A" + (assIDCount + 1);
        this.assTitle = assTitle;
        this.course = course;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.submitDate = submitDate;
        progress = "N/A";
        status = "N/A";
        
        assIDCount++;
    }

    public void setAssTitle(String assTitle) {
        this.assTitle = assTitle;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
        updateStatus();
    }

    public String getAssID() {
        return assID;
    }
    public String getAssTitle() {
        return assTitle;
    }
    public Course getCourse() {
        return course;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getSubmitDate() {
        return submitDate;
    }
    public String getProgress() {
        updateStatus();
        return progress;
    }
    public String getStatus() {
        updateStatus();
        return status;
    }
    public static int getAssIDCount() {
        return assIDCount;
    }
    
    @Override
    public String toString() {
        
        String outputStr = "Assignment ID: " + assID +
                           "\nAssignment Title: " + assTitle +
                           "\nCourse: " + course.getCourseID() + " " + course.getCourseName();
        
        return outputStr;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Assignment) {
            Assignment otherAss = (Assignment) o;
            if (this.assID == otherAss.assID) {
                return true;
            }
            else {
                return this.assID.compareTo(otherAss.assID) == 0;
            }
        }
        return false;
    }
    
    public void updateStatus() {
        
        if (submitDate != null) {
            if (submitDate.isBefore(dueDate) || submitDate.isEqual(dueDate)) {
                progress = "Submitted";
                status = "Completed";
            }
            else if (submitDate.isAfter(dueDate)) {
                progress = "Submitted";
                status = "Late";
            }
        }
        else {
            progress = "Pending";
            
            if (LocalDate.now().isBefore(startDate)) {
                status = "Pending";
            }
            else if (LocalDate.now().isBefore(dueDate) || LocalDate.now().isEqual(dueDate)) {
                status = "Ongoing";
            }
            else if (LocalDate.now().isAfter(dueDate)) {
                status = "Overdue";
            }
        }

    }
    
}
