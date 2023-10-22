package boundary;

import adt.SetListInterface;
import entity.Assignment;
import entity.AssignmentTeam;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Yap Ying Sin
 */
public class AssTeamSubSysUI {
    
    static Scanner scan = new Scanner(System.in);
    
    /* To display menus */
    
    public static void displaySubMenu() {
        
        
    }
    
    public static void displayReportSubMenu() {
        
        
    }
    
    public static void displayAmendmentSubMenu() {
        
        
    }
    
    public static void displayFilterSubMenu() {
        
        
    }
    
    /* To display TASK header from main */
    
    public static void displayCreateTeamTitle() {
        System.out.println("+================================+");
        System.out.println("|  TASK: Create assignment team  |");
        System.out.println("+================================+");
    }
    
    public static void displayRemoveTeamTitle() {
        System.out.println("+================================+");
        System.out.println("|  TASK: Remove assignment team  |");
        System.out.println("+================================+");
    }
    
    public static void displayAmendTeamTitle() {
        System.out.println("+=======================================+");
        System.out.println("|  TASK: Amend assignment team details  |");
        System.out.println("+=======================================+");
    }
    
    public static void displayAddStudTitle() {
        System.out.println("+========================================+");
        System.out.println("|  TASK: Add student to assignment team  |");
        System.out.println("+========================================+");
    }
    
    public static void displayRemoveStudTitle() {
        System.out.println("+=============================================+");
        System.out.println("|  TASK: Remove student from assignment team  |");
        System.out.println("+=============================================+");
    }
    
    public static void displayFilterTeamTitle() {
        System.out.println("+=================================+");
        System.out.println("|  TASK: Filter assignment teams  |");
        System.out.println("+=================================+");
    }
    
    public static void displayListTeamTitle() {
        System.out.println("+===================================+");
        System.out.println("|  TASK: List all assignment teams  |");
        System.out.println("+===================================+");
    }
    
    public static void displayListTeamStudTitle() {
        System.out.println("+================================================+");
        System.out.println("|  TASK: List students under an assignment team  |");
        System.out.println("+================================================+");
    }
    
    public static void displayGenReportTitle() {
        System.out.println("+==========================+");
        System.out.println("|  TASK: Generate reports  |");
        System.out.println("+==========================+");
    }
    
    /* To display TASK header from subsys */
    
    public static void displayAmendTeamName() {
        System.out.println("+=========================+");
        System.out.println("|  TASK: Amend Team Name  |");
        System.out.println("+=========================+");
    }
    
    public static void displayAmendMinCap() {
        System.out.println("+=============================+");
        System.out.println("|  TASK: Amend Min. Capacity  |");
        System.out.println("+=============================+");
    }
    
    public static void displayAmendMaxCap() {
        System.out.println("+=============================+");
        System.out.println("|  TASK: Amend Max. Capacity  |");
        System.out.println("+=============================+");
    }
    
    public static void displayFilterByTeamID() {
        System.out.println("+===========================+");
        System.out.println("|  TASK: Filter by Team ID  |");
        System.out.println("+===========================+");
    }
    
    public static void displayFilterByTeamName() {
        System.out.println("+=============================+");
        System.out.println("|  TASK: Filter by Team Name  |");
        System.out.println("+=============================+");
    }
    
    public static void displayFilterByTutGrp() {
        System.out.println("+==================================+");
        System.out.println("|  TASK: Filter by Tutorial Group  |");
        System.out.println("+==================================+");
    }
    
    public static void displayGenOutStudReport() {
        System.out.println("+============================================+");
        System.out.println("|  TASK: Generate Outstanding Student Report |");
        System.out.println("+============================================+");
    }
    
    public static void displayGenAssStatusReport() {
        System.out.println("+=====================================================+");
        System.out.println("|  TASK: Generate Assignment Status & Progress Report |");
        System.out.println("+=====================================================+");
    }
    
    /* To display PASSED-IN SET-LISTS */
    
    public static void displayProgList(SetListInterface<Programme> progList) {
        
        System.out.println("+===================================================================================================================================+");
        System.out.println("|  PROGRAMMES                                                                                                                       |");
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|  %-3s  %-8s  %-50s  %-60s  |\n", "No.", "Prog. ID", "Programme Name", "Faculty");
        System.out.printf("|  %-3s  %-8s  %-50s  %-60s  |\n", "---", "--------", "--------------", "-------");
        
        for (int i = 1; i <= progList.getNumberOfEntries(); i++) {
            Programme progEntry = progList.getEntry(i);
            
            System.out.printf("|  %2d.  %-8s  %-50s  %-60s  |\n", i, progEntry.getProgID(), progEntry.getProgName(), progEntry.getFaculty());
        }
        
        System.out.println("+-----------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|  <" + String.format("%2d", progList.getNumberOfEntries()) + " total entries>                                                                                                               |");
        System.out.println("+===================================================================================================================================+");
    }
    
    public static void displayTutGrpList(SetListInterface<TutorialGroup> tutGrpList) {
        
        System.out.println("+===========================+");
        System.out.println("|  TUTORIAL GROUPS          |");
        System.out.println("+---------------------------+");
        System.out.printf("|  %-3s  %-8s  %-8s  |\n", "No.", "Group ID", "Capacity");
        System.out.printf("|  %-3s  %-8s  %-8s  |\n", "---", "--------", "--------");
        
        for (int i = 1; i <= tutGrpList.getNumberOfEntries(); i++) {
            TutorialGroup tutGrpEntry = tutGrpList.getEntry(i);
            
            System.out.printf("|  %2d.  %-8s  %4d      |\n", i, tutGrpEntry.getGrpID(), tutGrpEntry.getCapacity());
        }
        
        System.out.println("+---------------------------+");
        System.out.printf("|  <" + String.format("%2d", tutGrpList.getNumberOfEntries()) + " total entries>       |\n");
        System.out.println("+===========================+");
    }
    
    
    public static void displayAssTeamList(SetListInterface<AssignmentTeam> assTeamList) {
        
        System.out.println("+===============================================================================================+");
        System.out.println("|  ASSIGNMENT TEAMS                                                                             |");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|  %-3s  %-7s  %-10s  %-13s  %-13s  %-15s  %-18s  |\n", "No.", "Team ID", "Team Name", "Min. Capacity", "Max. Capacity", "No. of Students", "No. of Assignments");
        System.out.printf("|  %-3s  %-7s  %-10s  %-13s  %-13s  %-15s  %-18s  |\n", "---", "-------", "---------", "-------------", "-------------", "---------------", "------------------");
        
        for (int i = 1; i <= assTeamList.getNumberOfEntries(); i++) {
            AssignmentTeam assTeamEntry = assTeamList.getEntry(i);
            
            System.out.printf("|  %2d.  %-7s  %-10s  %13s  %13s  %15s  %18s  |\n", i, assTeamEntry.getTeamID(), assTeamEntry.getTeamName(),  assTeamEntry.getMinCapacity(), assTeamEntry.getMaxCapacity(), assTeamEntry.getStudList().getNumberOfEntries(), assTeamEntry.getAssList().getNumberOfEntries());
        }
        
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.printf("|  <" + String.format("%2d", assTeamList.getNumberOfEntries()) + " total entries>                                                                           |\n");
        System.out.println("+===============================================================================================+");
    }
    
    public static void displayStudList(SetListInterface<Student> studList) {
        
        System.out.println("+=======================================================================================================+");
        System.out.println("|  STUDENTS                                                                                             |");
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("|  %-3s  %-10s  %-40s  %-40s  |\n", "No.", "Student ID", "Student Name", "Student Email");
        System.out.printf("|  %-3s  %-10s  %-40s  %-40s  |\n", "---", "----------", "------------", "-------------");
        
        for (int i = 1; i <= studList.getNumberOfEntries(); i++) {
            Student studEntry = studList.getEntry(i);
            
            System.out.printf("|  %2d.  %-10s  %-40s  %-40s  |\n", i, studEntry.getID(), studEntry.getName(), studEntry.getEmail());
        }
        
        System.out.println("+-------------------------------------------------------------------------------------------------------+");
        System.out.println("|  <" + String.format("%2d", studList.getNumberOfEntries()) + " total entries>                                                                                   |");
        System.out.println("+=======================================================================================================+");
    }
    
    public static void displayAssignmentList(SetListInterface<Assignment> assList) {
        
        System.out.println("+==========================================================================================================+");
        System.out.println("|  ASSIGNMENTS                                                                                             |");
        System.out.println("+----------------------------------------------------------------------------------------------------------+");
        System.out.printf("|  %-3s  %-13s  %-30s  %-50s  |\n", "No.", "Assignment ID", "Assignment Title", "Course");
        System.out.printf("|  %-3s  %-13s  %-30s  %-50s  |\n", "---", "-------------", "----------------", "------");
        
        for (int i = 1; i <= assList.getNumberOfEntries(); i++) {
            Assignment assEntry = assList.getEntry(i);
            
            System.out.printf("|  %2d.  %-13s  %-30s  %-50s  |\n", i, assEntry.getAssID(), assEntry.getAssTitle(), assEntry.getCourse().getCourseID() + " " + assEntry.getCourse().getCourseName());
        }
        
        System.out.println("+----------------------------------------------------------------------------------------------------------+");
        System.out.println("|  <" + String.format("%2d", assList.getNumberOfEntries()) + " total entries>                                                                                      |");
        System.out.println("+==========================================================================================================+");
    }
    
    public static void displayAssProgList(SetListInterface<Assignment> assList) {
        
        System.out.println("+=================================================================================================================================+");
        System.out.println("|  ASSIGNMENTS                                                                                                                    |");
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|  %-3s  %-13s  %-30s  %-10s  %-11s  %-11s  %-11s  %-10s  %-10s  |\n", "No.", "Assignment ID", "Assignment Title", "Course", "Start Date", "Due Date", "Submit Date", "Progress", "Status");
        System.out.printf("|  %-3s  %-13s  %-30s  %-10s  %-11s  %-11s  %-11s  %-10s  %-10s  |\n", "---", "-------------", "----------------", "------", "----------", "--------", "-----------", "--------", "------");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        for (int i = 1; i <= assList.getNumberOfEntries(); i++) {
            
            Assignment assEntry = assList.getEntry(i);
            String submitDate;
            
            if (assEntry.getSubmitDate() == null) {
                submitDate = "N/A";
            }
            else {
                submitDate = dtf.format(assList.getEntry(i).getSubmitDate());
            }
            System.out.printf("|  %-3s  %-13s  %-30s  %-10s  %-11s  %-11s  %-11s  %-10s  %-10s  |\n", i, assEntry.getAssID(), assEntry.getAssTitle(), assEntry.getCourse().getCourseID(), dtf.format(assEntry.getStartDate()), dtf.format(assEntry.getDueDate()), submitDate, assEntry.getProgress(), assEntry.getStatus());
        }
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------+");
        System.out.println("|  <" + String.format("%2d", assList.getNumberOfEntries()) + " total entries>                                                                                                             |");
        System.out.println("+=================================================================================================================================+");
    }
    
    /* To display INFO dialog box */
    
    public static void displayFilterByCourseInfoBox(Course selectedCourse) {
        System.out.println("+--------------------------------+");
        System.out.printf("|  INFO: Results under %-8s  |\n", selectedCourse.getCourseID());
        System.out.println("+--------------------------------+");
    }
    
    public static void displayFilterByProgInfoBox(Programme selectedProg) {
        System.out.println("+---------------------------+");
        System.out.printf("|  INFO: Results under %-3s  |\n", selectedProg.getProgID());
        System.out.println("+---------------------------+");
    }
    
    public static void displayFilterByTutGrpInfoBox(TutorialGroup selectedTutGrp) {
        System.out.println("+-----------------------------+");
        System.out.printf("|  INFO: Results under %-5s  |\n", selectedTutGrp.getGrpID());
        System.out.println("+-----------------------------+");
    }
    
    public static void displayFilterByAssTeamInfoBox(AssignmentTeam selectedAssTeam) {
        System.out.println("+-----------------------------+");
        System.out.printf("|  INFO: Results under %-5s  |\n", selectedAssTeam.getTeamID());
        System.out.println("+-----------------------------+");
    }
    
    public static void displayChangesSuccess() {
        System.out.println("+----------------------------------------------+");
        System.out.println("|  INFO: Changes have been successfully made.  |");
        System.out.println("+----------------------------------------------+");
    }
    
    public static void displayChangesNotSuccess() {
        System.out.println("+--------------------------------------------+");
        System.out.println("|  INFO: No changes were successfully made.  |");
        System.out.println("+--------------------------------------------+");
    }
    
    /* To display Action Messages */
    
    public static void displayActionToReturnPrevScr() {
        System.out.println("**Enter '0' to return to previous screen.");
    }
    
    public static void pressEnterToCont() {
        System.out.print("\nPress enter to continue...");
        scan.nextLine();
        System.out.println();
    }
    
    /* For display assignment team details */
    
    public static void displayAssTeamDetails(AssignmentTeam assTeam) {
        System.out.println("+===============================================================================+");
        System.out.println("|  ASSIGNMENT TEAM DETAILS                                                      |");
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.printf("|  Team ID      : %-60s  |\n", assTeam.getTeamID());
        System.out.printf("|  Team Name    : %-60s  |\n", assTeam.getTeamName());
        System.out.printf("|  Min. Capacity: %-60s  |\n", assTeam.getMinCapacity());
        System.out.printf("|  Max. Capacity: %-60s  |\n", assTeam.getMaxCapacity());
        System.out.println("+===============================================================================+");
    }
    
    public static void clearScreen() throws AWTException {
        Robot rob = new Robot();
        try {
            rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
            rob.keyPress(KeyEvent.VK_L); // press "L"
            rob.keyRelease(KeyEvent.VK_L); // unpress "L"
            rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
            Thread.sleep(100); // add delay in milisecond, if not there will automatically stop after clear
        } 
        catch (InterruptedException e) {
        }
    }
    
}
