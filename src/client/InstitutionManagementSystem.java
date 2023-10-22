package client;

import adt.ListInterface;
import adt.QueueInterface;
import entity.Assignment;
import entity.AssignmentTeam;
import entity.Course;
import entity.Programme;
import entity.Semester;
import entity.Student;
import entity.Tutor;
import entity.TutorialGroup;
import java.awt.AWTException;
import java.util.Scanner;
import Utility.TempDatabase;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class InstitutionManagementSystem {
    
 public static void main(String[] args) throws AWTException {
        ListInterface<Programme> progMasterList = TempDatabase.initializeProgrammes();
        QueueInterface<TutorialGroup> tutGrpListGroup = TempDatabase.initializeTutGrps_QueueInterface();
        Student studListStudent = TempDatabase.initializeStudents_QueueInterface();
        QueueInterface<Course> courseMasterList = TempDatabase.initializeCourses();
        ListInterface<Student> studMasterList = TempDatabase.initializeStudents();
        ListInterface<AssignmentTeam> assTeamMasterList = TempDatabase.initializeAssTeams();
        ListInterface<Assignment> assMasterList = TempDatabase.initializeAssignments();
        int choice;
        do{
            choice = Menu();
            switch (choice) {
                case 0:
                    System.out.println("\nExited System\n");
                    break;
                    
                case 1:
                    ProgrammeSubSystem.programmeModule(progMasterList,tutGrpListGroup,courseMasterList);
                    
                    break;
                case 2:
                    TutorialGroupSubSystem.TutorialGroupModule(tutGrpListGroup, studListStudent);
                    break;
                // ...
                case 3:
                    CourseSubSystem.courseModule(courseMasterList, progMasterList);
                    break;
                case 4:
                    TutorManagement.tutorManagement();
                    break;
                case 5:
                    AssignmentTeamSubSystem.assignmentTeamModule(courseMasterList, progMasterList, tutGrpListGroup, studMasterList, assTeamMasterList, assMasterList);
                    break;


                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
   }
        }while(choice !=0);
        
    }
    public static int Menu(){

                         
     System.out.print("|  2. Tutorial Group SubSystem                |");
                         

        System.out.print("\nEnter Number: ");
        Scanner scanner = new Scanner(System.in);
        int entered = scanner.nextInt();
        
        return entered;
        
    }

    
}
